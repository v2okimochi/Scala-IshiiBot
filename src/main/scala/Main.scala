import slack.models.{BotMessage, Message, MessageReplied, ReplyMessage}

import scala.concurrent.Future

object Main extends SetBot {
  var ishiiList: List[IshiiState] = List(IshiiState.apply())

  def main(args: Array[String]): Unit = {
    client.onEvent {
      //チャンネルに書き込まれた＆スレッド返信ではない
      case message: Message =>
        if (message.text == "ish status") {
          client.sendMessage(message.channel, showStatus(ishiiList.last),
            message.thread_ts)
        }
        if (selectCommand(message.text).isDefined)
          startByMessage(message, selectCommand(message.text).get,
            message.thread_ts)
      case _ => //ignore
    }
  }

  def showStatus(ishii: IshiiState): String = {
    val txt = s":ishi: のHPは のこり${ishii.hitPoint}よ。"
    ishii.hitPoint match {
      case hp if hp < 10 =>
        txt + ":ishi: は もうダメだわ…… つよく いきてね。"
      case hp if hp < 50 =>
        txt + "なんてこと してくれたのよ！ もう あとがないじゃない。"
      case hp if hp < 80 =>
        txt + "ちょっと ゆだんしないでよ！ ちゃんと きんちょうかんを もちなさい。"
      case _ => txt + "べつに あんたのために がんばるんじゃ ないんだからね。"
    }
  }

  def selectCommand(text: String): Option[String] = {
    text match {
      case text if text.contains("すから") |
        text.contains("スカラ") |
        text.contains("すくると") |
        text.contains("スクルト") |
        text.toLowerCase == "ish scala" => Some(Abilities.Scala.id)
      case "ish guard" => Some(Abilities.Guard.id)
      case _ => None
    }
  }

  def startByMessage(message: Message, command: String,
                     thread_ts: Option[String]): Future[Long] = {
    val userName: String = client.apiClient
      .getUserInfo(message.user)
      .profile
      .get.real_name.getOrElse("名無しさん").toString

    ishiiList = ishiiList.init :+ Turn.start(ishiiList.last
      .copy(user = userName, command = command, log = Nil))

    val ishii = ishiiList.last
    //    println(s"turn: ${ishii.turn}\n " +
    //      s"スカラターン: ${ishii.scalaTurn}\n " +
    //      s"HP: ${ishii.hitPoint}\n " +
    //      s"MP: ${ishii.magicPower}\n " +
    //      s"守備力: ${ishii.defence}")
    client.sendMessage(message.channel, ishiiList.last.log.mkString,
      thread_ts = thread_ts)
  }
}

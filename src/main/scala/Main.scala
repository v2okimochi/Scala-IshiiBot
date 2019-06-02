import slack.models.{BotMessage, Message, MessageReplied, ReplyMessage}

import scala.concurrent.Future

object Main extends SetBot {
  var ishiiList: List[IshiiState] = List(IshiiState.apply())

  def main(args: Array[String]): Unit = {
    client.onEvent {
      //チャンネルに書き込まれた＆スレッド返信ではない
      case message: Message =>
        println(s"message thread_ts: ${message.thread_ts}")
        println(s"message ts: ${message.ts}")
        println(s"message text: ${message.text}")
        if (selectCommand(message.text).isDefined)
          startByMessage(message, selectCommand(message.text).get,
            message.thread_ts)

      //スレッド返信だった＆bot自身の発言ではない
      //      case reply: MessageReplied =>
      //        println(s"reply thread_ts: ${reply.message.thread_ts}")
      //        println(s"reply ts: ${reply.ts}")
      //        println(s"reply text: ${reply.message.text}")
      //        if ((reply.message.replies.last.user != botId) &&
      //          selectCommand(reply.message.text).isDefined)
      //          startByReply(reply, selectCommand(reply.message.text).get)
      case _ => //ignore
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

  def startByReply(reply: MessageReplied, command: String): Future[Long] = {
    val userName: String = client.apiClient
      .getUserInfo(reply.message.user)
      .profile
      .get.real_name.getOrElse("名無しさん").toString

    ishiiList = ishiiList.init :+ Turn.start(ishiiList.last
      .copy(user = userName, command = command, log = Nil))

    val ishii = ishiiList.last
    //        println(s"turn: ${ishii.turn}\n " +
    //          s"スカラターン: ${ishii.scalaTurn}\n " +
    //          s"HP: ${ishii.hitPoint}\n " +
    //          s"MP: ${ishii.magicPower}\n " +
    //          s"守備力: ${ishii.defence}")

    client.sendMessage(reply.channel, ishiiList.last.log.mkString,
      thread_ts = Some(reply.message.thread_ts))
  }
}

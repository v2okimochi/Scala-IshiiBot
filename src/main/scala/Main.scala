import slack.models.Message

object Main extends SetBot {
  var ishiiList: List[IshiiState] = List(IshiiState.apply())

  def main(args: Array[String]): Unit = {
    client.onEvent {
      //チャンネルに書き込まれた＆スレッド返信ではない
      case message: Message =>
        message match {
          case msg if Help.search(msg.text).isDefined =>
            help(msg, Help.search(msg.text).get, msg.thread_ts)
          case msg => Command.parse(msg.text).foreach(c => startByMessage(msg, Some(c), msg.thread_ts))
        }
      case _ => //ignore
    }
  }

  // statusなどのヘルプ表示
  def help(message: Message, command: String,
           thread_ts: Option[String]): Unit = {
    command match {
      case Help.Status.id =>
        client.sendMessage(message.channel, Help.showStatus(ishiiList.last),
          message.thread_ts)
      case _ => //ignore
    }
  }

  // 1ターンの戦いが始まる
  def startByMessage(message: Message, command: Option[Command],
                     thread_ts: Option[String]): Unit = {
    val userName: String = client.apiClient
      .getUserInfo(message.user)
      .profile
      .get.real_name.getOrElse("名無しさん").toString

    ishiiList = ishiiList.init :+ Turn.start(ishiiList.last
      .copy(user = userName, command = command, log = Nil))

    val ishii = ishiiList.last
    println(s"turn: ${ishii.turn}\n " +
      s"スカラターン: ${ishii.scalaTurn}\n " +
      s"HP: ${ishii.hitPoint}\n " +
      s"MP: ${ishii.magicPower}\n " +
      s"守備力: ${ishii.defence}")
    client.sendMessage(message.channel, ishiiList.last.log.mkString,
      thread_ts = thread_ts)

    if (ishiiList.length > 1) ishiiList = ishiiList.tail
    if (ishiiList.last.condition == Conditions.dead)
      ishiiList = List(IshiiState.apply())
  }
}

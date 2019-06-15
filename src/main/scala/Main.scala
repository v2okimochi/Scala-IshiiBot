import slack.models.Message

trait Main {
  private var ishiiList: List[IshiiState] = List(IshiiState.apply())
  val slackClient: SlackClient

  def switchMessage(message: Message): Unit = message match {
    case msg if Help.search(msg.text).isDefined => help(msg, Help.search(msg.text).get)
    case msg => Command.parse(msg.text).foreach(c => startByMessage(msg, c))
  }

  // statusなどのヘルプ表示
  def help(message: Message, command: String): Unit = {
    command match {
      case Help.Status.id => slackClient.sendMessage(message, Help.showStatus(ishiiList.last))
      case _ => //ignore
    }
  }

  // 1ターンの戦いが始まる
  def startByMessage(message: Message, command: Command): Unit = {
    val userName: String = slackClient.getUserName(message)

    ishiiList = ishiiList.init :+ Turn.start(ishiiList.last
      .copy(user = userName, command = Some(command), log = Nil))

    val ishii = ishiiList.last
    println(s"turn: ${ishii.turn}\n " +
      s"スカラターン: ${ishii.scalaTurn}\n " +
      s"HP: ${ishii.hitPoint}\n " +
      s"MP: ${ishii.magicPower}\n " +
      s"守備力: ${ishii.defence}")
    slackClient.sendMessage(message, ishiiList.last.log.mkString)

    if (ishiiList.length > 1) ishiiList = ishiiList.tail
    if (ishiiList.last.condition == Conditions.dead)
      ishiiList = List(IshiiState.apply())
  }
}

object Main extends Main {
  val slackClient: SlackClient =
      new SlackClientImpl
//    new SlackClientLocalMock

  def main(args: Array[String]): Unit = slackClient.listen(switchMessage)
}

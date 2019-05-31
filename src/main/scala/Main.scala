import slack.models.Message

object Main extends SetBot with MyAction with EnemyAction {
  def main(args: Array[String]): Unit = {
    client.onMessage { message: Message =>
      if (message.text.contains("scala") ||
        message.text.contains("Scala") ||
        message.text.contains("スカラ"))
        start(message)
      //      println(s"HP: ${justHitPoint}, " +
      //        s"MP: ${magicPower}, " +
      //        s"守備力: ${justDefence}, " +
      //        s"メンタル ${justMental}")
    }
  }

  def start(message: Message) = {
    val userName: String = client.apiClient
      .getUserInfo(message.user)
      .profile
      .get.real_name.getOrElse("名無しさん").toString

    justHistory = s"${userName} のターン:\n :ishi: は Scalaを となえた！"

    myAction
    enemyAction

    client.sendMessage(message.channel, justHistory.mkString)
  }
}

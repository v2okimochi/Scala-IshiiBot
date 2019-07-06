package domain

sealed class AttackMessage(messageList: Seq[String]) {

  def getRandomMessage: String = messageList(Randomize.random.nextInt(messageList.length))

}

object AttackMessage {
  case object Neutral extends AttackMessage(Seq(
    "の おきもち！",
    "は ぶきみなえみを うかべながら とびかかってきた！",
    "は まさかりを なげつけてきた！",
    "は ふかかいな きょどうをした！",
    "は かってな ふるまいをした！",
    "は ふくざつにむすびついた ソースコードを あみだした！ コードが からみついて ほどけない！"
  ))

  case object OverLoad extends AttackMessage(Seq(
    "は ひきすうが ないのを いいことに メソッドを へんすうに かきかえた！"
  ))

}

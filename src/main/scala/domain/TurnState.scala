package domain

case class TurnState(
                      number: Int = 1,
                      scalaTurnNumber: Int = -1,
                      channelId: String = "",
                      userName: String = "",
                      ishiiState: IshiiState = IshiiState.apply(),
                      enemy: Enemy,
                      damage: Int = 0,
                      log: String = ""
                    ) {
  def isNoScala: Boolean = scalaTurnNumber == -1

  def isEndOfScalaTurn: Boolean = scalaTurnNumber >= 5
}
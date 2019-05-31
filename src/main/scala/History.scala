
trait History extends MyStates {
  var justHitPoint: Int = hitPoint
  var justMagicPower: Int = magicPower
  var justDefence: Int = defence
  var justMental: Int = mental

  var scalaCount: Int = 0
  var turnCount: Int = 0
  var justHistory: String = ""

  def initStatus = {
    justHitPoint = hitPoint
    justMagicPower = magicPower
    justDefence = defence
    justMental = mental
    turnCount = 0
  }
}

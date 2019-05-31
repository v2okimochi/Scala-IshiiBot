trait MyStates {
  val hitPoint: Int = 100
  val magicPower: Int = 30
  val defence: Int = 40
  val mental: Int = 5

  def defaultStatusHistory: List[Map[String, Int]] = {
    List(createMyStatus(hitPoint, magicPower, defence, mental))
  }

  private def createMyStatus(hitPoint: Int,
                             magicPower: Int,
                             defence: Int,
                             mental: Int): Map[String, Int] = {
    Map("hitPoint" -> hitPoint,
      "magicPower" -> magicPower,
      "defence" -> defence,
      "mental" -> mental)
  }
}

import scala.math.{floor, random}

trait RandomDice {
  def swingRange: Int = floor(random * 13).toInt - 6

  def swingRange(limit: Int) =
    floor(random * (limit * 2 - 1)).toInt - limit

  def swingThousand: Int = floor(random * 1000).toInt

  def swung(value: Int): Int =
    if (value == 0) value else value * (1 / value)

}

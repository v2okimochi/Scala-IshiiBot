package domain

// ishiiの状態異常
sealed class Condition(val label: String, val id: String)

object Condition {

  case object Dead extends Condition(label = "力尽きた", id = "dead")

  case object Escaped extends Condition(label = "逃げた", id = "escaped")

  case object Poison extends Condition(label = "毒", id = "poison")

  case object Fizzle extends Condition(label = "マホトーン", id = "fizzle")

//  val fizzle: String = "fizzle"
//  val poison: String = "poison"
//  val dead: String = "dead"
//  val escaped: String = "escaped"
}

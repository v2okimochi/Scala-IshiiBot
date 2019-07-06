package domain

sealed class AttackCategory()

object AttackCategory {

  case object NoCategory extends AttackCategory

  case object Poison extends AttackCategory

}
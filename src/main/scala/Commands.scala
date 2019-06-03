abstract case class Command(id: String, mp: Int)

object Status {
  val id = "status"
}

object Abilities {

  object Scala extends Command("scala", 2)

  object Guard extends Command("guard", 0)

  object MagicalHolyWater extends Command("MagicalHolyWater", 0)

}


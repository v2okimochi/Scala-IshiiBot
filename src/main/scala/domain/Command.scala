package domain

// ishiiの行動選択肢
sealed abstract class Command(val label: String, val mp: Int, val score: Int){
  val substrings: Set[String] = Set.empty
  val perfectMatchingWords: Set[String] = Set.empty
}

object Command {

  case object Scala extends Command("Scala", 2, 100){
    override val substrings: Set[String] = Set(
      "すから",
      "すくると"
    )
    override val perfectMatchingWords: Set[String] = Set(
      "ish scala",
      "スカラ",
      "スクルト"
    )
  }

  case object Guard extends Command("ぼうぎょ", 0, 50){
    override val substrings: Set[String] = Set.empty
    override val perfectMatchingWords: Set[String] = Set(
      "ぼうぎょ",
      "ish guard"
    )
  }

  case object MagicalHolyWater extends Command("まほうのせいすい", 0, 300){
    override val substrings: Set[String] = Set(
      "まほうのせいすい"
    )
    override val perfectMatchingWords: Set[String] = Set(
      "ish mhw",
      "ish magicalholywater"
    )
  }

  sealed abstract class Escape extends Command("にげる", 0, -100){
    override val perfectMatchingWords: Set[String] = Set(
      "にげる"
    )
  }

  case object FailureEscape extends Escape{
    override val label: String ="にげる (失敗)"
  }

  case object SuccessEscape extends Escape{
    override val label = "にげる (成功)"
  }

  case object Fight extends Command("たたかう", 0, 0){
    override val perfectMatchingWords: Set[String] = Set(
      "たたかう"
    )
  }

  def isEscape(commandString: String): Boolean =
    isMatchedCommand(commandString, new Escape {})

  def isFight(commandString: String): Boolean =
    isMatchedCommand(commandString, Fight)

  def parse(commandString: String): Option[Command] =
    Seq(Scala, Guard, MagicalHolyWater)
      .find(isMatchedCommand(commandString, _))

  private def isMatchedCommand(commandString: String, command: Command): Boolean =
    command.substrings.exists(commandString.toLowerCase.contains(_)) ||
      command.perfectMatchingWords.exists(commandString.toLowerCase.equals)
}
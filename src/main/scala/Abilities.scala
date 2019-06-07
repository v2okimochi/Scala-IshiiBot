// ishiiの行動選択肢
sealed abstract class Command(val id: String, val mp: Int)

object Abilities {

  case object Scala extends Command(id = "scala", mp = 2)

  case object Guard extends Command(id = "guard", mp = 0)

  case object MagicalHolyWater extends Command("MagicalHolyWater", 0)

  def parse(commandString: String): Option[Command] = {

    val scalaWords = List("すから", "スカラ", "すくると", "スクルト", "ish scala")
    val guardWords = List("ぼうぎょ","ish guard")
    val magicalHolyWaterWords = List("まほうのせいすい", "ish mhw")

    commandString match {
      case s if matches(s, scalaWords) => Some(Scala)
      case s if matches(s, guardWords) => Some(Guard)
      case s if matches(s, magicalHolyWaterWords) => Some(MagicalHolyWater)
      case _ => None
    }
  }

  private def matches(rawString: String, wordList: List[String], matchCase: Boolean = false): Boolean = {
    val s = if (matchCase) rawString else rawString.toLowerCase // アルファベットは小文字になり、日本語はそのまま保持される
    wordList.exists(s.contains(_))
  }
}


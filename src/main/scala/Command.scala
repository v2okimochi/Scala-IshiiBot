// ishiiの行動選択肢
sealed abstract class Command(val mp: Int)

object Command {

  case object Scala extends Command(mp = 2)
  case object Guard extends Command(mp = 0)
  case object MagicalHolyWater extends Command(mp = 0)

  private val scalaWords = Set("すから", "スカラ", "すくると", "スクルト", "ish scala")
  private val guardWords = Set("ぼうぎょ","ish guard")
  private val magicalHolyWaterWords = Set("まほうのせいすい", "ish mhw")

  def parse(commandString: String): Option[Command] = commandString match {
    case s if matches(s, scalaWords) => Some(Scala)
    case s if matches(s, guardWords) => Some(Guard)
    case s if matches(s, magicalHolyWaterWords) => Some(MagicalHolyWater)
    case _ => None
  }

  private def matches(rawString: String, words: Set[String], matchCase: Boolean = false): Boolean = {
    val s = if (matchCase) rawString else rawString.toLowerCase // アルファベットは小文字になり、日本語はそのまま保持される
    words.exists(s.contains(_))
  }
}


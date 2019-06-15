// ishiiの行動選択肢
sealed abstract class Command(val mp: Int)

object Command {

  case object Scala extends Command(mp = 2)

  case object Guard extends Command(mp = 0)

  case object MagicalHolyWater extends Command(mp = 0)

  private val scalaWords: Set[String] = Set("すから", "すくると")
  private val scalaPerfectWords: Set[String] = Set("ish scala", "スカラ", "スクルト")
  private val guardPerfectWords: Set[String] = Set("ぼうぎょ", "ish guard")
  private val mhwWords: Set[String] = Set("まほうのせいすい")
  private val mhwPerfectWords: Set[String] =
    Set("ish mhw", "ish magicalholywater")

  def parse(commandString: String): Option[Command] = commandString match {
    case s if matches(s, scalaWords) || perfectMatches(s, scalaPerfectWords) =>
      Some(Scala)
    case s if perfectMatches(s, guardPerfectWords) => Some(Guard)
    case s if matches(s, mhwWords) || perfectMatches(s, mhwPerfectWords) =>
      Some(MagicalHolyWater)
    case _ => None
  }

  //部分一致
  private def matches(rawString: String, words: Set[String], matchCase: Boolean = false): Boolean = {
    val s = if (matchCase) rawString else rawString.toLowerCase // アルファベットは小文字になり、日本語はそのまま保持される
    words.exists(s.contains(_))
  }

  //完全一致
  private def perfectMatches(rawString: String, words: Set[String]): Boolean =
    words.exists(rawString.toLowerCase.equals)
}


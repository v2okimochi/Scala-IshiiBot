package domain

// ishiiの行動選択肢
sealed abstract class Command(val label: String, val mp: Int, val score: Int)

object Command {

  case object Scala extends Command(label = "Scala", mp = 2, score = 100)

  case object Guard extends Command(label = "ぼうぎょ", mp = 0, score = 50)

  case object MagicalHolyWater extends Command(label = "まほうのせいすい", mp = 0, score = 300)

  case object Escape extends Command(label = "にげる (失敗)", mp = 0, score = -100)

  case object SuccessEscape extends Command(label = "にげる (成功)", mp = 0, score = -100)

  case object Fight extends Command(label = "たたかう", mp = 0, score = 0)

  private val scalaWords: Set[String] = Set("すから", "すくると")
  private val scalaPerfectWords: Set[String] = Set("ish scala", "スカラ", "スクルト")
  private val guardPerfectWords: Set[String] = Set("ぼうぎょ", "ish guard")
  private val mhwWords: Set[String] = Set("まほうのせいすい")
  private val mhwPerfectWords: Set[String] =
    Set("ish mhw", "ish magicalholywater")

  def isEscape(commandString: String): Boolean = commandString == "にげる"

  def isFight(commandString: String): Boolean = commandString == "たたかう"

  def parse(commandString: String): Option[Command] = commandString match {
    case s if matches(s, scalaWords) || perfectMatches(s, scalaPerfectWords) => Some(Scala)
    case s if perfectMatches(s, guardPerfectWords) => Some(Guard)
    case s if matches(s, mhwWords) || perfectMatches(s, mhwPerfectWords) => Some(MagicalHolyWater)
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


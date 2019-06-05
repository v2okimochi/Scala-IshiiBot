// ishiiの行動選択肢
abstract case class Command(id: String, mp: Int)

object Abilities {

  object Scala extends Command(id = "scala", mp = 2)

  object Guard extends Command(id = "guard", mp = 0)

  object MagicalHolyWater extends Command("MagicalHolyWater", 0)

  def search(text: String): Option[String] = {
    text match {
      case text if text.contains("すから") |
        text.contains("スカラ") |
        text.contains("すくると") |
        text.contains("スクルト") |
        text.toLowerCase == "ish scala" => Some(Scala.id)
      case "ish guard" => Some(Guard.id)
      case text if text == "ish mhw" | text == "まほうのせいすい" =>
        Some(MagicalHolyWater.id)
      case _ => None
    }
  }
}


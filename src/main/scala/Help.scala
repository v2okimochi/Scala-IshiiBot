// statusなど行動以外のヘルプ
abstract case class Preference(id: String)

object Help {

  object Status extends Preference(id = "status")

  def search(text: String): Option[String] = {
    text match {
      case "ish status" => Some(Status.id)
      case _ => None
    }
  }

  def showStatus(ishii: IshiiState): String = {
    val txt = s":ishi: のHPは のこり${ishii.hitPoint}よ。"
    ishii.hitPoint match {
      case hp if hp < 10 => txt +
        ":ishi: は もうダメだわ…… つよく いきてね。"
      case hp if hp < 20 => txt +
        "なんてこと してくれたのよ！ もう あとがないじゃない。"
      case hp if hp < 30 => txt +
        ":ishi: にだって いきのこる いじがあるのよ。こなくそ！"
      case hp if hp < 40 => txt +
        "ここが ふんばりどころよ。しょげていないで まえをむきなさい！"
      case hp if hp < 50 => txt +
        "かんがえなしに つっこまないの！ れいせいに あたまを つかってよね。"
      case hp if hp < 60 => txt +
        "ふーん しんぱいして くれるんだ。わるいきぶんは しないわね。"
      case hp if hp < 70 => txt +
        "ばかに しないでよね。こんなことで :ishi: が たおされると おもう？"
      case hp if hp < 80 => txt +
        "ちょっと ゆだんしないでよ！ ちゃんと きんちょうかんを もちなさい。"
      case _ => txt + "べつに あんたのために がんばるんじゃ ないんだからね。"
    }
  }
}
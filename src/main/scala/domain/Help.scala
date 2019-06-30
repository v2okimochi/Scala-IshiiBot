package domain

// statusなど行動以外のヘルプ
abstract case class Preference(id: String)

case class StatusMessages(minHP: Int, maxHP: Int, message: String)

object Help {

  object Status extends Preference(id = "status")

  def search(text: String): Option[String] = {
    text match {
      case "ish status" => Some(Status.id)
      case _ => None
    }
  }

  private val statusMessageList: Seq[StatusMessages] = Seq(
    StatusMessages(90, 100,
      "べつに あんたのために がんばるんじゃ ないんだからね。"),
    StatusMessages(90, 100,
      "この :ishi: に たてつこうだなんて みのほどしらずも いいところだわ！"),
    StatusMessages(90, 100,
      "くびをあらって まっていなさい。テンミニッツで しずめてやるんだから。"),
    StatusMessages(70, 90,
      ":ishi: に さからうなんて わるいこ。おしおきしてあげる！"),
    StatusMessages(70, 90,
      "ちょっと ゆだんしないでよ！ ちゃんと きんちょうかんを もちなさい。"),
    StatusMessages(70, 90,
      "いい？ ちゃんと :ishi: の いうことをきいて たたかうのよ。"),
    StatusMessages(60, 90,
      "ばかに しないでよね。こんなことで :ishi: が たおされると おもう？"),
    StatusMessages(60, 90,
      "ふーん しんぱいして くれるんだ。わるいきぶんは しないわね。"),
    StatusMessages(50, 80,
      "なにやってんのよ。 :ishi: のしんぱいも しなさいったら。"),
    StatusMessages(40, 80,
      "かんがえなしに つっこまないの！ れいせいに あたまを つかってよね。"),
    StatusMessages(30, 60,
      "ここが ふんばりどころよ。しょげていないで まえをむきなさい！"),
    StatusMessages(10, 50,
      ":ishi: にだって いきのこる いじがあるのよ。こなくそ！"),
    StatusMessages(10, 30,
      "なんてこと してくれたのよ！ もう あとがないじゃない。"),
    StatusMessages(0, 9,
      ":ishi: は もうダメだわ…… つよく いきてね。")
  )

  def getStatusMessage(ishii: IshiiState, randomNumber: Int): String = {
    println(s"randomNum: $randomNumber")
    val hpMessage: String = s":ishi: のHPは のこり${ishii.hitPoint}よ。"
    val filteredMessageList: Seq[StatusMessages] = statusMessageList
      .filter(ishii.hitPoint >= _.minHP)
      .filter(ishii.hitPoint <= _.maxHP)
    val index: Int = randomNumber % filteredMessageList.length
    hpMessage + filteredMessageList(index).message
  }
}
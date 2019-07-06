package app

import domain.{Command, Condition, IshiiState, Randomize, TurnState}
import infra.FileAccess

object IshiiTurn extends FileAccess {
  def apply(turn: TurnState): TurnState = {
    val newLog: String = turn.log + s"${turn.userName} のターン:"

    // ishiiの行動 ==> 行動後の処理
    endOfIshiiTurn(
      if (turn.ishiiState.mental < 3) doMadAction(turn.copy(log = newLog))
      else turn.ishiiState.command match {
        case Some(Command.Scala) => doScala(turn.copy(log = newLog))
        case Some(Command.Guard) => doGuard(turn.copy(log = newLog))
        case Some(Command.MagicalHolyWater) =>
          doMagicalHolyWater(turn.copy(log = newLog))
        case Some(Command.FailureEscape) => doEscape(turn.copy(log = newLog))
        case _ => throw new Exception("登録されていない行動です")
      }
    )
  }

  // ishiiの行動後の処理
  def endOfIshiiTurn(turn: TurnState): TurnState = {
    turn.scalaTurnNumber match {
      case turnNum if turnNum + 1 > 5 =>
        turn.copy(
          ishiiState = turn.ishiiState.copy(defence = IshiiState.apply().defence),
          scalaTurnNumber = -1,
          log = turn.log + ":ishi: の しゅびりょくが もとにもどった。"
        )
      case -1 => turn
      case _ => turn.copy(scalaTurnNumber = turn.scalaTurnNumber + 1)
    }
  }

  // メンタルがやられている時の行動
  def doMadAction(turn: TurnState): TurnState = {
    turn
  }

  //スカラの行動
  def doScala(turn: TurnState): TurnState = {
    val newLog: String = turn.log + "\n :ishi: は Scalaを となえた！"

    // MP不足
    if (turn.ishiiState.magicPower < 2) {
      return turn.copy(
        ishiiState = turn.ishiiState.copy(
          magicPower = fixMP(turn.ishiiState.magicPower - Command.Scala.mp)
        ),
        log = newLog + "しかし MPがたりない！")
    }

    //呪文封じ
    if (turn.ishiiState.condition == Condition.Fizzle.id) {
      return turn.copy(
        ishiiState = turn.ishiiState.copy(
          magicPower = fixMP(turn.ishiiState.magicPower - Command.Scala.mp)
        ),
        log = newLog + "しかし じゅもんは ふうじられている！")
    }

    //守備力の上昇上限
    if (turn.ishiiState.defence - IshiiState.apply().defence >= 200) {
      return turn.copy(
        ishiiState = turn.ishiiState.copy(
          magicPower = fixMP(turn.ishiiState.magicPower - Command.Scala.mp)
        ),
        log = newLog + "しかし なにも おこらなかった。")
    }

    //上昇幅決定 ＆ 上昇幅を200までに固定
    (Randomize.random.nextInt(4) match {
      case 0 => (IshiiState.apply().defence * 0.4).toInt //DQ5
      case 1 => (IshiiState.apply().defence * 0.5).toInt //DQ6
      case 2 => IshiiState.apply().defence //DQ3
      case 3 => (IshiiState.apply().defence * 1.5).toInt //DQ4
    }) match {
      case up if IshiiState.apply().defence + up > 200 =>
        turn.copy(
          ishiiState = turn.ishiiState.copy(
            magicPower = fixMP(turn.ishiiState.magicPower - Command.Scala.mp),
            defence = IshiiState.apply().defence + 200
          ),
          log = newLog + s":ishi: の しゅびりょくが" +
            s" ${200 - turn.ishiiState.defence} あがった！",
          scalaTurnNumber = turn.scalaTurnNumber - 1)
      case up =>
        turn.copy(
          ishiiState = turn.ishiiState.copy(
            magicPower = fixMP(turn.ishiiState.magicPower - Command.Scala.mp),
            defence = turn.ishiiState.defence + up
          ),
          log = newLog + s":ishi: の しゅびりょくが ${up} あがった！",
          scalaTurnNumber = if (turn.scalaTurnNumber == -1) 0 else turn.scalaTurnNumber - 1)
    }
  }

  //防御
  def doGuard(turn: TurnState): TurnState =
    turn.copy(log = turn.log + ":ishi: は みをまもっている。")

  //まほうのせいすい
  def doMagicalHolyWater(turn: TurnState): TurnState = {
    val txt: String = "\n :ishi: は まほうのせいすいを つかった！"
    val up: Int = Randomize.random.nextInt(20) + 1
    val limitedUp: Int =
      if (turn.ishiiState.magicPower + up > IshiiState.apply().magicPower)
        IshiiState.apply().magicPower - turn.ishiiState.magicPower
      else up

    if (turn.ishiiState.magicPower < IshiiState.apply().magicPower)
      turn.copy(
        ishiiState = turn.ishiiState.copy(
          magicPower = turn.ishiiState.magicPower + limitedUp
        ),
        log = turn.log + (txt + s":ishi: の MPが ${limitedUp} かいふくした！"))
    else turn.copy(log = turn.log + (txt + "しかし なにも おこらなかった。"))
  }

  //にげる
  def doEscape(turn: TurnState): TurnState = {
    val actionMessage: String = ":ishi: は にげだした！"
    val isSuccessed: Boolean = Randomize.random.nextInt(4) == 0

    if (isSuccessed) {
      val newMuteUsersList: List[String] = readFile(fileNameOfMuteUsers) :+ turn.channelId
      writeListToFile(fileNameOfMuteUsers, newMuteUsersList)
      turn.copy(
        ishiiState = turn.ishiiState.copy(
          command = Some(Command.SuccessEscape)
        ),
        log = turn.log + actionMessage)
    }
    else turn.copy(
      ishiiState = turn.ishiiState.copy(
        condition = turn.ishiiState.condition
      ),
      log = actionMessage + "しかし まわりこまれてしまった！"
    )
  }

  // MPが負の値にならないように修正
  private def fixMP(mp: Int): Int = if (mp < 0) 0 else mp
}

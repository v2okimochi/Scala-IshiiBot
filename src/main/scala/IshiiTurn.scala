object IshiiTurn extends RandomDice {
  def apply(ishii: IshiiState): IshiiState = {
    val newLog: List[String] = ishii.log :+ s"${ishii.user} のターン:"

    //ishiiの行動 ==> 行動後の処理
    endOfIshiiTurn(
      if (ishii.mental < 3) doMadAction(ishii
        .copy(log = newLog))
      else ishii.command match {
        case Abilities.Scala.id => doScala(ishii.copy(log = newLog))
        case Abilities.Guard.id => doGuard(ishii.copy(log = newLog))
      }
    )
  }

  def endOfIshiiTurn(ishii: IshiiState): IshiiState = {
    ishii.scalaTurn match {
      case turn if turn + 1 > 5 =>
        ishii.copy(defence = IshiiState.apply().defence, scalaTurn = -1,
          log = ishii.log :+ ":ishi: の しゅびりょくが もとにもどった。")
      case -1 => ishii
      case _ => ishii.copy(scalaTurn = ishii.scalaTurn + 1)
    }
  }

  def doMadAction(ishii: IshiiState): IshiiState = {
    ishii
  }

  def doScala(ishii: IshiiState): IshiiState = {
    val newLog: List[String] = ishii.log :+ "\n :ishi: は Scalaを となえた！"

    // MP不足
    if (ishii.magicPower < 2) {
      return ishii.copy(
        magicPower = fixMP(ishii.magicPower - Abilities.Scala.mp),
        log = newLog :+ "しかし MPがたりない！")
    }

    //呪文封じ
    if (ishii.condition == Conditions.fizzle) {
      return ishii.copy(
        magicPower = fixMP(ishii.magicPower - Abilities.Scala.mp),
        log = newLog :+ "しかし じゅもんは ふうじられている！")
    }

    //守備力の上昇上限
    if (ishii.defence - IshiiState.apply().defence >= 200) {
      return ishii.copy(
        magicPower = fixMP(ishii.magicPower - Abilities.Scala.mp),
        log = newLog :+ "しかし なにも おこらなかった。")
    }

    //上昇幅決定 ＆ 上昇幅を200までに固定
    (random.nextInt(4) match {
      case 0 => (IshiiState.apply().defence * 0.4).toInt //DQ5
      case 1 => (IshiiState.apply().defence * 0.5).toInt //DQ6
      case 2 => IshiiState.apply().defence //DQ3
      case 3 => (IshiiState.apply().defence * 1.5).toInt //DQ4
    }) match {
      case up if IshiiState.apply().defence + up > 200 =>
        ishii.copy(
          magicPower = fixMP(ishii.magicPower - Abilities.Scala.mp),
          defence = IshiiState.apply().defence + 200,
          log = newLog :+ s":ishi: の しゅびりょくが" +
            s" ${200 - ishii.defence} あがった！",
          scalaTurn = ishii.scalaTurn - 1)
      case up =>
        ishii.copy(
          magicPower = fixMP(ishii.magicPower - Abilities.Scala.mp),
          defence = ishii.defence + up,
          log = newLog :+ s":ishi: の しゅびりょくが ${up} あがった！",
          scalaTurn = if (ishii.scalaTurn == -1) 0 else ishii.scalaTurn - 1)
    }
  }

  def doGuard(ishii: IshiiState): IshiiState = {
    ishii.copy(log = ishii.log :+ ":ishi: は みをまもっている。")
  }

  def fixMP(mp: Int): Int = if (mp < 0) 0 else mp
}

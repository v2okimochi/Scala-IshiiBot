object IshiiTurn extends RandomDice {
  def apply(ishii: IshiiState): IshiiState = {
    ishii.log +: s"${ishii.user} のターン:"
    //ishiiの行動 ==> 行動後の処理
    endOfIshiiTurn(
      if (ishii.mental < 3) doMadAction(ishii)
      else ishii.command match {
        case Abilities.Scala.id => doScala(ishii)
      }
    )
  }

  def endOfIshiiTurn(ishii: IshiiState): IshiiState = {
    ishii.scalaTurn match {
      case x if x + 1 > 5 =>
        ishii.log +: ":ishi: の しゅびりょくが もとにもどった。"
        ishii.copy(defence = IshiiState.apply().defence, scalaTurn = -1)
      case -1 => ishii
      case _ => ishii.copy(scalaTurn = ishii.scalaTurn + 1)
    }
  }

  def doMadAction(ishii: IshiiState): IshiiState = {
    ishii
  }

  def doScala(ishii: IshiiState): IshiiState = {
    ishii.log +: "\n :ishi: は Scalaを となえた！"

    // MP不足
    if (ishii.magicPower < 2) {
      ishii.log +: "しかし MPがたりない！"
      return ishii.copy(magicPower = ishii.magicPower - Abilities.Scala.mp)
    }

    //呪文封じ
    if (ishii.condition == Conditions.fizzle) {
      ishii.log +: "しかし じゅもんは ふうじられている！"
      return ishii.copy(magicPower = ishii.magicPower - Abilities.Scala.mp)
    }

    //守備力の上昇上限
    if (ishii.defence - IshiiState.apply().defence >= 200) {
      ishii.log +: "しかし なにも おこらなかった。"
      return ishii.copy(magicPower = ishii.magicPower - Abilities.Scala.mp)
    }

    //上昇幅決定 ＆ 上昇幅を200までに固定
    (random.nextInt(4) match {
      case 0 => (IshiiState.apply().defence * 0.4).toInt //DQ5
      case 1 => (IshiiState.apply().defence * 0.5).toInt //DQ6
      case 2 => IshiiState.apply().defence //DQ3
      case 3 => (IshiiState.apply().defence * 1.5).toInt //DQ4
    }) match {
      case up if IshiiState.apply().defence + up > 200 =>
        ishii.log +: s":ishi: の しゅびりょくが ${
          200 - IshiiState.apply().defence
        } あがった！"
        ishii.copy(defence = IshiiState.apply().defence + 200)
      case up =>
        ishii.log +: s":ishi: の しゅびりょくが ${up} あがった！"
        ishii.copy(defence = ishii.defence + up)
    }
  }

  def doGuard(ishii: IshiiState): IshiiState = {
    ishii.log +: ":ishi: は みをまもっている。"
    ishii
  }
}

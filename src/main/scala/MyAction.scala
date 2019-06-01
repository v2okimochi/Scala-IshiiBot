trait MyAction extends MyStates with RandomDice with History {
  def myAction: Unit = {
    var up: Int = defence / (rnd_0to999 % 10 + 1)

    if (justDefence + up > defence * 2)
      justHistory += "しかし なにも おこらなかった。"
    else {
      justDefence += up
      justHistory += s":ishi: の しゅびりょくが ${up} あがった！"
    }

    scalaCount += 1
    if (scalaCount > 5) {
      scalaCount = 0
      justDefence = defence
      justHistory += ":ishi: のしゅびりょくが もとにもどった。"
    }
  }
}

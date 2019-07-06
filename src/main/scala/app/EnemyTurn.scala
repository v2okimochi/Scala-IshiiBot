package app

import domain.{AttackCategory, AttackMessage, Condition, Enemy, IshiiState, Randomize, TurnState}

object EnemyTurn {
  def apply(turn: TurnState): TurnState = {
    //敵を決定 ==> 敵の行動を決定 ==> ダメージ計算 ==> 死亡判定 ==> 最後の処理
    endOfEnemyTurn(judgeDead(calcDamage(selectEnemyAction(turn))))
  }

  // 1ターン終了時の処理
  def endOfEnemyTurn(turn: TurnState): TurnState = {
    turn.copy(number = turn.number + 1)
  }

  // ダメージ処理と死亡判定
  def judgeDead(turn: TurnState): TurnState = {
    val newLog: String =
      if (turn.damage <= 0)
        turn.log + "\n ミス！ :ishi: は ダメージを うけない！"
      else turn.log + s"\n:ishi: は ${
        turn.damage
      } の ダメージを うけた！"

    if (turn.ishiiState.hitPoint - turn.damage <= 0) {
      turn.copy(
        ishiiState = turn.ishiiState.copy(
          condition = Some(Condition.Dead)
        ),
        log = newLog + "\n「ぎょええーーー！」 :ishi:はちからつきた。" +
          s":ishi: は ${turn.number} ターン耐え、" +
          s"最後の守備力は${turn.ishiiState.defence} だった。")
    } else
      turn.copy(
        ishiiState = turn.ishiiState.copy(
          hitPoint = turn.ishiiState.hitPoint - turn.damage
        ),
        log = newLog)
  }

  def calcDamage(turn: TurnState): TurnState = {
    val (baseDamage, criticalLog) =
      if (isCritical)
        (getCriticalBaseDamage(turn.ishiiState, turn.enemy), "\nつうこんの いちげき！")
      else
        (getBaseDamage(turn.ishiiState, turn.enemy), "")

    val judgedCritical: TurnState = turn.copy(
      damage = baseDamage,
      log = turn.log + criticalLog
    )

    val judgedGuard: TurnState = judgeGuard(judgedCritical)

    judgedGuard
  }

  def isCritical: Boolean = Randomize.random.nextInt(100) < 5

  def getBaseDamage(ishii: IshiiState, enemy: Enemy): Int = {
    val damage = getAmpDamage(enemy.force / 2) - ishii.defence / 4
    if (damage < 0) 0 else damage
  }

  def getCriticalBaseDamage(ishii: IshiiState, enemy: Enemy): Int = {
    val damage = getAmpDamage((enemy.force * 1.5 / 2).toInt) - ishii.defence / 4
    if (damage < 0) 0 else damage
  }

  //防御ならダメージを半分にする
  def judgeGuard(turn: TurnState): TurnState =
    if (turn.ishiiState.isGuarding)
      turn.copy(damage = (turn.damage * 0.5).toInt)
    else turn

  //敵によって行動を決める
  def selectEnemyAction(turn: TurnState): TurnState = {
    val attackMessage: String = "\n" + turn.enemy.name + turn.enemy.attackMessage.getRandomMessage

    turn.copy(log = turn.log + attackMessage)
  }

  // 1/6から-1/6までランダムに揺らして返す
  def getAmpDamage(damage: Int): Int = Randomize.random.nextInt((damage * 2 / 6) + 1) + damage - damage / 6

}

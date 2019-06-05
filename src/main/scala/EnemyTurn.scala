object EnemyTurn {
  def apply(ishii: IshiiState): IshiiState = {
    //敵を決定 ==> 敵の行動を決定 ==> ダメージ計算 ==> 死亡判定 ==> 最後の処理
    endOfEnemyTurn(judgeDead(calcDamage(selectEnemyAction(selectEnemy(ishii)))))
  }

  // 1ターン終了時の処理
  def endOfEnemyTurn(ishii: IshiiState): IshiiState = {
    ishii.copy(turn = ishii.turn + 1)
  }

  // ダメージ処理と死亡判定
  def judgeDead(ishii: IshiiState): IshiiState = {
    val newLog =
      if (ishii.damage <= 0)
        ishii.log :+ "\n ミス！ :ishi: は ダメージを うけない！"
      else ishii.log :+ s"\n:ishi: は ${
        ishii.damage
      } の ダメージを うけた！"

    if (ishii.hitPoint - ishii.damage <= 0) {
      ishii.copy(condition = Conditions.dead,
        log = newLog :+ "\n「ぎょええーーー！」 :ishi:はちからつきた。" +
          s":ishi: は ${ishii.turn} ターン耐え、" +
          s"最後の守備力は${ishii.defence} だった。")
    } else
      ishii.copy(hitPoint = ishii.hitPoint - ishii.damage,
        log = newLog)
  }

  def calcDamage(ishii: IshiiState): IshiiState =
    judgeGuard(judgeCritical(ishii)).copy()

  //一定の確率で痛恨ダメージにする
  def judgeCritical(ishii: IshiiState): IshiiState = {
    if (Turn.random.nextInt(100) < 5) {
      val damage = getAmpDamage(((EnemyStates.enemies(ishii.enemyNum)
        .attack * 1.5) / 2).toInt) - ishii.defence / 4

      ishii.copy(damage = if (damage < 0) 0 else damage,
        log = ishii.log :+ "\nつうこんの いちげき！")
    } else {
      val damage = getAmpDamage(EnemyStates.enemies(ishii.enemyNum)
        .attack / 2) - ishii.defence / 4

      ishii.copy(damage = if (damage < 0) 0 else damage)
    }
  }

  //防御ならダメージを半分にする
  def judgeGuard(ishii: IshiiState): IshiiState =
    if (ishii.command == Abilities.Guard.id)
      ishii.copy(damage = (ishii.damage * 0.5).toInt)
    else ishii

  //敵によって行動を決める
  def selectEnemyAction(ishii: IshiiState): IshiiState = {
    val newLog =
      if (EnemyStates.enemies(ishii.enemyNum).actions == "")
        ishii.log :+ "\n" + EnemyStates.enemies(ishii.enemyNum).name +
          EnemyStates.attacks(Turn.random.nextInt(EnemyStates.attacks.length))
      else
        ishii.log :+ "\n" + EnemyStates.enemies(ishii.enemyNum).name +
          EnemyStates.specialAttacks(EnemyStates.enemies(ishii.enemyNum).actions)
    ishii.copy(log = newLog)
  }

  //敵をランダムに決める
  def selectEnemy(ishii: IshiiState): IshiiState =
    ishii.copy(enemyNum = Turn.random.nextInt(EnemyStates.enemies.length))

  // 1/6から-1/6までランダムに揺らして返す
  def getAmpDamage(damage: Int): Int =
    Turn.random.nextInt((damage * 2 / 6) + 1) + damage - damage / 6
}

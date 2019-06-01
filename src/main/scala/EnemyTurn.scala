object EnemyTurn extends RandomDice {
  def apply(ishii: IshiiState): IshiiState = {
    //敵を決定 ==> 敵の行動を決定 ==> ダメージ計算 ==> 死亡判定 ==> 最後の処理
    endOfEnemyTurn(
      judgeDead(
        calcDamage(
          selectEnemyAction(
            selectEnemy(ishii)
          )
        )
      )
    )
  }

  def endOfEnemyTurn(ishii: IshiiState): IshiiState = {
    ishii
  }

  def judgeDead(ishii: IshiiState): IshiiState = {
    ishii.log +: s"\n:ishi: は ${ishii.damage} の ダメージを うけた！"
    if (ishii.hitPoint - ishii.damage <= 0) {
      ishii.log +: ":ishi:はちからつきた。"
      //これまでの履歴を出す
      //
      IshiiState.apply()
    } else
      ishii
  }

  def calcDamage(ishii: IshiiState): IshiiState = {
    if (random.nextInt(100) < 5) {
      ishii.log +: "\nつうこんの いちげき！"
      ishii.copy(damage =
        getAmpDamage(((EnemyStates.enemies(ishii.enemyNum).attack * 1.5) / 2)
          .toInt) - ishii.defence / 4)
    } else ishii.copy(damage =
      getAmpDamage(EnemyStates.enemies(ishii.enemyNum).attack / 2)
        - ishii.defence / 4)
  }

  def selectEnemyAction(ishii: IshiiState): IshiiState = {
    if (EnemyStates.enemies(ishii.enemyNum).actions == "")
      ishii.log +: EnemyStates.attacks(random.nextInt(EnemyStates
        .attacks.length))
    else
      ishii.log +: EnemyStates.specialAttacks(EnemyStates
        .enemies(ishii.enemyNum).actions)
    ishii
  }

  def selectEnemy(ishii: IshiiState): IshiiState =
    ishii.copy(enemyNum = random.nextInt(EnemyStates.enemies.length))


  // 1/6から-1/6までランダムに揺らして返す
  def getAmpDamage(damage: Int): Int =
    random.nextInt((damage * 2 / 6) + 1) + damage - damage / 6
}

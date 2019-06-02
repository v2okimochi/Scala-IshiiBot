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
    ishii.copy(turn = ishii.turn + 1)
  }

  def judgeDead(ishii: IshiiState): IshiiState = {
    val newLog =
      if (ishii.damage <= 0)
        ishii.log :+ "\n ミス！ :ishi: は ダメージを うけない！"
      else ishii.log :+ s"\n:ishi: は ${
        ishii.damage
      } の ダメージを うけた！"

    if (ishii.hitPoint - ishii.damage <= 0) {
      ishii.copy(condition = Conditions.dead,
        log = newLog :+ "\n「ぎょええーーー :感嘆符:」 :ishi:はちからつきた。" +
          s":ishi: は ${ishii.turn} ターン耐え、" +
          s"守備力を最大${ishii.defence} まで上げた。")
    } else
      ishii.copy(hitPoint = ishii.hitPoint - ishii.damage,
        log = newLog)
  }

  def calcDamage(ishii: IshiiState): IshiiState = {
    if (random.nextInt(100) < 5) {
      val damage =
        getAmpDamage(((EnemyStates.enemies(ishii.enemyNum).attack * 1.5) / 2)
          .toInt) - ishii.defence / 4
      ishii.copy(damage = if (damage < 0) 0 else damage,
        log = ishii.log :+ "\nつうこんの いちげき！")
    } else {
      val damage =
        getAmpDamage(EnemyStates.enemies(ishii.enemyNum).attack / 2) -
          ishii.defence / 4
      ishii.copy(damage = if (damage < 0) 0 else damage)
    }
  }

  def selectEnemyAction(ishii: IshiiState): IshiiState = {
    val newLog =
      if (EnemyStates.enemies(ishii.enemyNum).actions == "")
        ishii.log :+ "\n" + EnemyStates.enemies(ishii.enemyNum).name +
          EnemyStates.attacks(random.nextInt(EnemyStates.attacks.length))
      else
        ishii.log :+ "\n" + EnemyStates.enemies(ishii.enemyNum).name +
          EnemyStates.specialAttacks(EnemyStates.enemies(ishii.enemyNum).actions)
    ishii.copy(log = newLog)
  }

  def selectEnemy(ishii: IshiiState): IshiiState =
    ishii.copy(enemyNum = random.nextInt(EnemyStates.enemies.length))


  // 1/6から-1/6までランダムに揺らして返す
  def getAmpDamage(damage: Int): Int =
    random.nextInt((damage * 2 / 6) + 1) + damage - damage / 6
}

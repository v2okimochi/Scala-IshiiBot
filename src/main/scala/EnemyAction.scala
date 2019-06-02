//trait EnemyAction extends EnemyStates with RandomDice with History {
//  def enemyAction: Unit = {
//    var enemy: Enemy = enemies(rnd_0to999 % (enemies.length))
//    var actionMessage: String =
//      if (enemy.actions == "") {
//        attacks(rnd_0to999 % (attacks.length))
//      } else {
//        specialAttacks(enemy.actions)
//      }
//    justHistory += ("\n" + enemy.name + actionMessage)
//
//    var critical: Boolean = if (rnd_0to999 < 50) true else false
//
//    var enemyAttackPoint: Int =
//      if (critical) {
//        justHistory += s"\nつうこんの いちげき！"
//        (enemy.attack + enemy.attack * 1.5).toInt
//      }
//      else enemy.attack
//
//    var damage: Int = (enemyAttackPoint / 2) - (justDefence / 4)
//
//    if (damage <= 0) justHistory += "\n ミス！ :ishi: は ダメージを うけない！"
//    else justHistory += s"\n :ishi: は ${damage} の ダメージをうけた！"
//
//    var remaining: Int = justHitPoint - damage
//
//    if (remaining <= 0) {
//      justHistory +=
//        "\n「ぎょええーーーー！」\n :ishi: は ちからつきた。" +
//          s"\n :ishi: は ${turnCount} ターン耐えた。最終的な守備力: ${justDefence}"
//      initStatus
//    }
//    else {
//      justHitPoint = remaining
//      turnCount += 1
//    }
//  }
//}

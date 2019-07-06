package domain

case class Enemy(
                  name: String,
                  force: Int, //攻撃力
                  attackCategory: AttackCategory, // 攻撃の種類
                  attackMessage: AttackMessage // 攻撃メッセージ
                )

object Enemy {
  def getRandomEnemy: Enemy = {
    val random = Randomize.random.nextInt(enemyList.length)
    println(s"random: $random")
    enemyList(random)
  }

  val enemyList: Seq[Enemy] = Seq(
    Enemy(name = "JVM", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "JDK", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Maven", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "sbt", force = 90, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Scala Test", force = 110, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "immutable", force = 120, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "mutable", force = 150, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Option", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Unit", force = 105, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "comet", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Ajax", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "snippet", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "REPL", force = 90, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "var", force = 140, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "val", force = 50, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "if式(Expression)", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "for式(Expression)", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "while式(Expression)", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "match式(Expression)", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "文(Statement)", force = 50, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "構文(Syntax)", force = 60, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "パターンマッチ", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "ガード式", force = 60, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "ネスト", force = 110, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "中置パターン", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "フィールド", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "抽象メンバー", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "継承", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "オブジェクト", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "シングルトン", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "コンパニオンオブジェクト", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "インスタンス", force = 65, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "クラスパラメータ", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "クラス", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "ケースクラス", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "トレイト", force = 85, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "引数", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "コンストラクタ", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "オーバーロード", force = 105, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "オーバーライド", force = 200, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.OverLoad),
    Enemy(name = "ひし形継承問題", force = 110, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "線形化", force = 95, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "型パラメータ", force = 110, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "変位指定(variance)", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "共変(covariant)", force = 95, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "反変(contravariant)", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "上限境界(upper bounds)", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "下限境界(lower bounds)", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "メソッド", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "関数", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "部分適用", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "無名関数", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "カリー化", force = 50, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "高階関数", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "コレクション", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Array", force = 60, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "List", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Map", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Set", force = 65, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Range", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Nil", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "コンス(::)", force = 70, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "foldLeft", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "foldRight", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "Vector", force = 50, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral),
    Enemy(name = "静的型付け", force = 80, attackCategory = AttackCategory.NoCategory, attackMessage = AttackMessage.Neutral)
  )
}
package domain

case class AttackCategory()

object AttackCategory {

  case object NoCategory extends AttackCategory

  case object Poison extends AttackCategory

}

case class AttackMessage(logs: Seq[String])

object AttackMessage {

  case object Neutral extends AttackMessage(Seq(
    "の おきもち！",
    "は ぶきみなえみを うかべながら とびかかってきた！",
    "は まさかりを なげつけてきた！",
    "は ふかかいな きょどうをした！",
    "は かってな ふるまいをした！",
    "は ふくざつにむすびついた ソースコードを あみだした！ コードが からみついて ほどけない！"
  ))

  case object OverLoad extends AttackMessage(Seq(
    "は ひきすうが ないのを いいことに メソッドを へんすうに かきかえた！"
  ))

}


//object EnemyStates {
//  val attacks: List[String] = List(
//    "の おきもち！",
//    "は ぶきみなえみを うかべながら とびかかってきた！",
//    "は まさかりを なげつけてきた！",
//    "は ふかかいな きょどうをした！",
//    "は かってな ふるまいをした！",
//    "は ふくざつにむすびついた ソースコードを あみだした！ コードが からみついて ほどけない！"
//  )
//  val specialAttacks: Map[String, String] = Map(
//    "override to val" -> "は ひきすうが ないのを いいことに メソッドを へんすうに かきかえた！"
//  )
//  val enemies: List[Enemy] = List(
//    Enemy("JVM", 80, ""),
//    Enemy("JDK", 80, ""),
//    Enemy("Maven", 80, ""),
//    Enemy("sbt", 90, ""),
//    Enemy("Scala Test", 110, ""),
//    Enemy("immutable", 120, ""),
//    Enemy("mutable", 150, ""),
//    Enemy("Option", 80, ""),
//    Enemy("Unit", 105, ""),
//    Enemy("comet", 80, ""),
//    Enemy("Ajax", 80, ""),
//    Enemy("snippet", 70, ""),
//    Enemy("REPL", 90, ""),
//    Enemy("var", 140, ""),
//    Enemy("val", 50, ""),
//    Enemy("if式(Expression)", 80, ""),
//    Enemy("for式(Expression)", 80, ""),
//    Enemy("while式(Expression)", 70, ""),
//    Enemy("match式(Expression)", 70, ""),
//    Enemy("文(Statement)", 50, ""),
//    Enemy("構文(Syntax)", 60, ""),
//    Enemy("パターンマッチ", 70, ""),
//    Enemy("ガード式", 60, ""),
//    Enemy("ネスト", 110, ""),
//    Enemy("中置パターン", 70, ""),
//    Enemy("フィールド", 80, ""),
//    Enemy("抽象メンバー", 80, ""),
//    Enemy("継承", 80, ""),
//    Enemy("オブジェクト", 80, ""),
//    Enemy("シングルトン", 70, ""),
//    Enemy("コンパニオンオブジェクト", 70, ""),
//    Enemy("インスタンス", 65, ""),
//    Enemy("クラスパラメータ", 70, ""),
//    Enemy("クラス", 80, ""),
//    Enemy("ケースクラス", 80, ""),
//    Enemy("トレイト", 85, ""),
//    Enemy("引数", 70, ""),
//    Enemy("コンストラクタ", 70, ""),
//    Enemy("オーバーロード", 105, ""),
//    Enemy("オーバーライド", 200, "override to val"),
//    Enemy("ひし形継承問題", 110, ""),
//    Enemy("線形化", 95, ""),
//    Enemy("型パラメータ", 110, ""),
//    Enemy("変位指定(variance)", 80, ""),
//    Enemy("共変(covariant)", 95, ""),
//    Enemy("反変(contravariant)", 80, ""),
//    Enemy("上限境界(upper bounds)", 70, ""),
//    Enemy("下限境界(lower bounds)", 70, ""),
//    Enemy("メソッド", 70, ""),
//    Enemy("関数", 80, ""),
//    Enemy("部分適用", 70, ""),
//    Enemy("無名関数", 80, ""),
//    Enemy("カリー化", 50, ""),
//    Enemy("高階関数", 70, ""),
//    Enemy("コレクション", 70, ""),
//    Enemy("Array", 60, ""),
//    Enemy("List", 70, ""),
//    Enemy("Map", 70, ""),
//    Enemy("Set", 65, ""),
//    Enemy("Range", 70, ""),
//    Enemy("Nil", 70, ""),
//    Enemy("コンス(::)", 70, ""),
//    Enemy("foldLeft", 80, ""),
//    Enemy("foldRight", 80, ""),
//    Enemy("Vector", 50, ""),
//    Enemy("静的型付け", 80, "")
//  )
//}

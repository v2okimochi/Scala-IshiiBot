object EnemyStates {
  val attacks: List[String] = List(
    "の おきもち！",
    "は ぶきみなえみを うかべながら とびかかってきた！",
    "は まさかりを なげつけてきた！",
    "は ふかかいな きょどうをした！",
    "は かってな ふるまいをした！",
    "は ふくざつにむすびついた ソースコードを あみだした！ コードが からみついて ほどけない！"
  )
  val specialAttacks: Map[String, String] = Map(
    "override to val" -> "は ひきすうが ないのを いいことに メソッドを へんすうに かきかえた！"
  )
  val enemies: List[Enemy] = List(
    Enemy("JVM", 70, ""),
    Enemy("JDK", 70, ""),
    Enemy("Maven", 70, ""),
    Enemy("sbt", 70, ""),
    Enemy("Scala Test", 70, ""),
    Enemy("immutable", 80, ""),
    Enemy("mutable", 120, ""),
    Enemy("Option", 80, ""),
    Enemy("Unit", 80, ""),
    Enemy("comet", 80, ""),
    Enemy("Ajax", 80, ""),
    Enemy("snippet", 70, ""),
    Enemy("REPL", 50, ""),
    Enemy("var", 70, ""),
    Enemy("val", 50, ""),
    Enemy("if式(Expression)", 80, ""),
    Enemy("for式(Expression)", 80, ""),
    Enemy("while式(Expression)", 70, ""),
    Enemy("match式(Expression)", 70, ""),
    Enemy("文(Statement)", 50, ""),
    Enemy("構文(Syntax)", 60, ""),
    Enemy("パターンマッチ", 70, ""),
    Enemy("ガード式", 60, ""),
    Enemy("ネスト", 90, ""),
    Enemy("中置パターン", 70, ""),
    Enemy("フィールド", 80, ""),
    Enemy("抽象メンバー", 80, ""),
    Enemy("継承", 70, ""),
    Enemy("オブジェクト", 70, ""),
    Enemy("シングルトン", 60, ""),
    Enemy("コンパニオンオブジェクト", 50, ""),
    Enemy("インスタンス", 65, ""),
    Enemy("クラスパラメータ", 70, ""),
    Enemy("クラス", 80, ""),
    Enemy("ケースクラス", 80, ""),
    Enemy("トレイト", 80, ""),
    Enemy("引数", 70, ""),
    Enemy("コンストラクタ", 70, ""),
    Enemy("オーバーロード", 90, ""),
    Enemy("オーバーライド", 150, "override to val"),
    Enemy("ひし形継承問題", 100, ""),
    Enemy("線形化", 95, ""),
    Enemy("型パラメータ", 70, ""),
    Enemy("変位指定(variance)", 80, ""),
    Enemy("共変(covariant)", 95, ""),
    Enemy("反変(contravariant)", 80, ""),
    Enemy("上限境界(upper bounds)", 60, ""),
    Enemy("下限境界(lower bounds)", 60, ""),
    Enemy("メソッド", 70, ""),
    Enemy("関数", 80, ""),
    Enemy("部分適用", 70, ""),
    Enemy("無名関数", 60, ""),
    Enemy("カリー化", 50, ""),
    Enemy("高階関数", 70, ""),
    Enemy("コレクション", 60, ""),
    Enemy("Array", 50, ""),
    Enemy("List", 60, ""),
    Enemy("Map", 60, ""),
    Enemy("Set", 65, ""),
    Enemy("Range", 50, ""),
    Enemy("Nil", 60, ""),
    Enemy("コンス(::)", 70, ""),
    Enemy("foldLeft", 80, ""),
    Enemy("foldRight", 80, ""),
    Enemy("Vector", 50, ""),
    Enemy("静的型付け", 70, "")
  )
}

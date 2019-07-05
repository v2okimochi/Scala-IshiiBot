package domain

import domain.Command.{Guard, MagicalHolyWater, Scala}
import org.scalatest.FlatSpec

class CommandSpec extends FlatSpec{

  "isEscape" should "引数が「にげる」と一致していればtrue" in {
    val testCases: Set[String] = Set(
      "にげる",
    )

    testCases.foreach(testCase =>
      assert(Command.isEscape(testCase))
    )
  }

  it should "引数が「にげる」と一致していなければfalse" in {
    val testCases: Map[String, Boolean] = Map(
      "逃げる" -> false,
      "にげる！" -> false,
      "逃げたい" -> false,
      "" -> false
    )

    testCases.foreach(testCase =>
      assert(Command.isEscape(testCase._1) === testCase._2)
    )
  }

  "isFight" should "引数が「たたかう」と一致していればtrue" in {
    val testCases: Set[String] = Set(
      "たたかう",
    )

    testCases.foreach(testCase =>
      assert(Command.isFight(testCase))
    )
  }

  it should "引数が「たたかう」と一致していなければfalse" in {
    val testCases: Map[String, Boolean] = Map(
      "戦う" -> false,
      "闘う" -> false,
      "buttle" -> false,
      "たたかう！" -> false,
      "" -> false
    )

    testCases.foreach(testCase =>
      assert(Command.isFight(testCase._1) === testCase._2)
    )
  }

  "parse" should
    """
      |引数が下記のいずれかの条件を満たす場合Some(Scala)を返す
      |・引数が下記の文字列を含む
      |   - すから
      |   - すくると
      |
      |・下記の文字列と一致
      |   - ish scala
      |   - スカラ
      |   - スクルト
    """.stripMargin in {
    val testCases: Set[String] = Set(
      "すから",
      "すくると",
      "凪のあすから",
      "すくると！",
      "ish scala",
      "スカラ",
      "スクルト"
    )

    testCases.foreach(testCase =>
      assert(Command.parse(testCase) === Some(Scala))
    )
  }

  it should
    """
      |引数が下記の条件を満たす場合Some(Guard)を返す
      |・下記の文字列と一致
      |   - ぼうぎょ
      |   - ish guard
    """.stripMargin in {
    val testCases: Set[String] = Set(
      "ぼうぎょ",
      "ish guard"
    )

    testCases.foreach(testCase =>
      assert(Command.parse(testCase) === Some(Guard))
    )
  }

  it should
    """
      |引数が下記の条件を満たす場合Some(MagicalHolyWater)を返す
      |・引数が下記の文字列を含む
      |   - まほうのせいすい
      |
      |・下記の文字列と一致
      |   - ish mhw
      |   - ish magicalholywater
    """.stripMargin in {
    val testCases: Set[String] = Set(
      "まほうのせいすい！",
      "まほうのせいすい",
      "ish mhw",
      "ish magicalholywater"
    )

    testCases.foreach(testCase =>
      assert(Command.parse(testCase) === Some(MagicalHolyWater))
    )
  }

  it should "いずれにも当てはまらない場合はNoneを返す" in {
    val testCases: Set[String] = Set(
      "",
      "すか ら",
      "す くると",
      "english scala",

      "ｽｶﾗ",
      "ｽｸﾙﾄ",
      "スカラ!",
      "スクルト！",
      "すカラ",
      "スくると",

      "防御",
      "ぼうぎょ！",
      "english guard",

      "魔法の聖水",
      "まほう の せいすい",
      "english mhw",
    )

    testCases.foreach(testCase =>
      assert(Command.parse(testCase) === None)
    )
  }
}

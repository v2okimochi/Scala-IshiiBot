package domain

sealed case class Score(val userName: String, val score: Int)

case object Scoring {
  private val deadScore: Int = -500

  def getScoreList(turnList: Seq[TurnState]): Seq[Score] = {
    val userSet: Set[TurnState] = turnList.tail.toSet // ユーザ一覧として使う
    val emptyScoreList: Seq[Score] = Seq.empty
    // ishiiを殺したユーザの翼
    val userNameKilledIshii: String = turnList.tail
      .filter(_.ishiiState.condition == Some(Condition.Dead)).last
      .userName
    println(s"killed by $userNameKilledIshii")

    // ユーザごとに得点加算
    val scoreSet: Seq[Score] =
      userSet.map(ishii =>
        Score(userName = ishii.userName,
          score = turnList.filter(_.userName == ishii.userName)
            .foldLeft(0)((now, next) =>
              now + (
                if (next.ishiiState.command.isDefined) next.ishiiState.command.get.score
                else 0
                )
            )
        )
      ).foldLeft(emptyScoreList)((now, next) => now :+ next)

    // ishiiを力尽きさせたユーザは減点
    val reducedScoreSet: Seq[Score] =
      scoreSet.map(user =>
        if (user.userName == userNameKilledIshii) user.copy(score = user.score + deadScore)
        else user
      ).foldLeft(emptyScoreList)((now, next) => now :+ next)

    reducedScoreSet.sortBy(_.score).reverse //得点の高い順に並び替え
  }
}

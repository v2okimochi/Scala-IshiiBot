package domain.database

import java.time.LocalDateTime
import scalikejdbc._

case class ResultTableValues(
                              idxOption: Option[Long],
                              dateTime: LocalDateTime,
                              turn: Int,
                              killerIdx: Long,
                              topUserIdx: Long,
                              topScore: Int
                            )
object ResultTableValues {
  val idx: SQLSyntax = sqls"idx"
  val date_time: SQLSyntax = sqls"date_time"
  val turn: SQLSyntax = sqls"turn"
  val killer_idx: SQLSyntax = sqls"killer_idx"
  val topUser_idx: SQLSyntax = sqls"top_user_idx"
  val top_score: SQLSyntax = sqls"top_score"
}
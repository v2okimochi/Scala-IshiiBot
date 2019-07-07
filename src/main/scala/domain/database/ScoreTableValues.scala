package domain.database

import java.time.LocalDate

import scalikejdbc._

case class ScoreTableValues(
                             idxOption: Option[Long],
                             resultIdx: Long,
                             date: LocalDate,
                             userIdx: Long,
                             score: Int
                           )
object ScoreTableValues {
  val idx: SQLSyntax = sqls"idx"
  val result_idx: SQLSyntax = sqls"result_idx"
  val date: SQLSyntax = sqls"date"
  val user_idx: SQLSyntax = sqls"user_idx"
  val score: SQLSyntax = sqls"score"
}
package domain.database

import scalikejdbc._

case class TurnTableValues(
                            idxOption: Option[Long],
                            resultIdx: Long,
                            scalaIdx: Int,
                            channelId: String,
                            userIdx: Long,
                            ishiiIdx: Long,
                            damage: Int,
                            log: String
                          )
object TurnTableValues {
  val idx: SQLSyntax = sqls"idx"
  val result_idx: SQLSyntax = sqls"result_idx"
  val scala_idx: SQLSyntax = sqls"scala_idx"
  val channel_id: SQLSyntax = sqls"channel_id"
  val user_id: SQLSyntax = sqls"user_idx"
  val ishii_idx: SQLSyntax = sqls"ishii_idx"
  val damage: SQLSyntax = sqls"damage"
  val log: SQLSyntax = sqls"log"
}


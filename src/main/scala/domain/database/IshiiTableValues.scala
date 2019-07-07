package domain.database

import scalikejdbc._

case class IshiiTableValues(
                             idxOption: Option[Long],
                             hitPoint: Int,
                             magicPower: Int,
                             defence: Int,
                             mental: Int,
                             conditionIdxOption: Option[Int],
                             commandIdx: Int
                           )
object IshiiTableValues {
  val idx: SQLSyntax = sqls"idx"
  val hit_point: SQLSyntax = sqls"hit_point"
  val magic_power: SQLSyntax = sqls"magic_power"
  val defence: SQLSyntax = sqls"defence"
  val mental: SQLSyntax = sqls"mental"
  val condition_idx: SQLSyntax = sqls"condition_idx"
  val command_idx: SQLSyntax = sqls"command_idx"
}
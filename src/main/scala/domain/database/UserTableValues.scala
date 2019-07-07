package domain.database

import scalikejdbc._

case class UserTableValues(
                            idxOption: Option[Long],
                            name: String
                          )
object UserTableValues {
  val idx: SQLSyntax = sqls"idx"
  val name: SQLSyntax = sqls"name"
}

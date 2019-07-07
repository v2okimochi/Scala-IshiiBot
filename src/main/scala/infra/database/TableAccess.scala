package infra.database

import scalikejdbc._

abstract class TableAccess[T] extends DatabaseAccess {
  val tableName: SQLSyntax

  def createMe: Unit

  def insert(tableValues: T): Unit

  def selectAllAsMapList(tableName: SQLSyntax): List[Map[String, Any]] =
    DB readOnly { implicit session =>
      sql""" select * from $tableName """.map(rs => rs.toMap).toList.apply()
    }
}

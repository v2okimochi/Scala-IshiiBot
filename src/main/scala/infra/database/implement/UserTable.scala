package infra.database.implement

import domain.database.UserTableValues
import infra.database.TableAccess
import scalikejdbc._

object UserTable extends TableAccess[UserTableValues] {
  override val tableName: SQLSyntax = sqls"user"

  override def createMe: Unit = DB autoCommit { implicit session =>
    sql"""
      create table $tableName (
      ${UserTableValues.idx} bigint primary key auto_increment,
      ${UserTableValues.name} varchar(50) not null
      )
      """.execute.apply()
  }

  override def insert(tableValues: UserTableValues): Unit = DB.autoCommit { implicit session =>
    val idx = tableValues.idxOption match {
      case _ => null // auto_incrementなのでどうあがいてもnullにする
    }
    sql"""
      insert into $tableName (
      ${UserTableValues.idx},
      ${UserTableValues.name}
      ) values (
      $idx,
      ${tableValues.name}
      )
      """.execute.apply()
  }

  def selectAllAsMapList: List[Map[String, Any]] = super.selectAllAsMapList(tableName)

}

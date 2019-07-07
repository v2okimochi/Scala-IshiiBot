package infra.database.implement

import domain.database.IshiiTableValues
import infra.database.TableAccess
import scalikejdbc._

object IshiiTable extends TableAccess[IshiiTableValues] {
  override val tableName: SQLSyntax = sqls"ishii_table"

  override def createMe: Unit = DB autoCommit { implicit session =>
    sql"""
      create table $tableName (
      ${IshiiTableValues.idx} bigint primary key auto_increment,
      ${IshiiTableValues.hit_point} int not null,
      ${IshiiTableValues.magic_power} int not null,
      ${IshiiTableValues.defence} int not null,
      ${IshiiTableValues.mental} int not null,
      ${IshiiTableValues.condition_idx} int,
      ${IshiiTableValues.command_idx} int not null
      )
      """.execute.apply()
  }

  override def insert(tableValues: IshiiTableValues): Unit = DB.autoCommit { implicit session =>
    val idx = tableValues.idxOption match {
      case _ => null // auto_incrementなのでどうあがいてもnullにする
    }
    val condition_idx = tableValues.conditionIdxOption match {
      case Some(value) => value
      case None => null
    }
    sql"""
      insert into $tableName (
      ${IshiiTableValues.idx},
      ${IshiiTableValues.hit_point},
      ${IshiiTableValues.magic_power},
      ${IshiiTableValues.defence},
      ${IshiiTableValues.mental},
      ${IshiiTableValues.condition_idx},
      ${IshiiTableValues.command_idx}
      ) values (
      $idx,
      ${tableValues.hitPoint},
      ${tableValues.magicPower},
      ${tableValues.defence},
      ${tableValues.mental},
      $condition_idx,
      ${tableValues.commandIdx}
      )
      """.execute.apply()
  }

  def selectAllAsMapList: List[Map[String, Any]] = super.selectAllAsMapList(tableName)
}

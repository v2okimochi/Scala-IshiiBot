package infra.database.implement

import domain.database.ResultTableValues
import infra.database.TableAccess
import scalikejdbc._

object ResultTable extends TableAccess[ResultTableValues] {
  override val tableName: SQLSyntax = sqls"result"

  override def createMe: Unit = DB autoCommit { implicit session =>
    sql"""
      create table $tableName (
      ${ResultTableValues.idx} bigint primary key auto_increment,
      ${ResultTableValues.date_time} date not null,
      ${ResultTableValues.turn} int not null,
      ${ResultTableValues.killer_idx} bigint not null,
      ${ResultTableValues.topUser_idx} bigint not null,
      ${ResultTableValues.top_score} int not null
      )
      """.execute.apply()
  }

  override def insert(tableValues: ResultTableValues): Unit = DB.autoCommit { implicit session =>
    val idx = tableValues.idxOption match {
      case _ => null // auto_incrementなのでどうあがいてもnullにする
    }
    sql"""
      insert into $tableName (
      ${ResultTableValues.idx},
      ${ResultTableValues.date_time},
      ${ResultTableValues.turn},
      ${ResultTableValues.killer_idx},
      ${ResultTableValues.topUser_idx},
      ${ResultTableValues.top_score}
      ) values (
      $idx,
      ${tableValues.dateTime},
      ${tableValues.turn},
      ${tableValues.killerIdx},
      ${tableValues.topUserIdx},
      ${tableValues.topScore}
      )
      """.execute.apply()
  }
}

package infra.database.implement

import domain.database.ScoreTableValues
import infra.database.TableAccess
import scalikejdbc._

object ScoreTable extends TableAccess[ScoreTableValues] {
  override val tableName: SQLSyntax = sqls"score"

  override def createMe: Unit = DB autoCommit { implicit session =>
    sql"""
      create table $tableName (
      ${ScoreTableValues.idx} bigint primary key auto_increment,
      ${ScoreTableValues.date} date not null,
      ${ScoreTableValues.result_idx} bigint not null,
      ${ScoreTableValues.user_idx} bigint not null,
      ${ScoreTableValues.score} int not null
      )
      """.execute.apply()
  }

  override def insert(tableValues: ScoreTableValues): Unit = DB.autoCommit { implicit session =>
    val idx = tableValues.idxOption match {
      case _ => null // auto_incrementなのでどうあがいてもnullにする
    }
    sql"""
      insert into $tableName (
      ${ScoreTableValues.idx},
      ${ScoreTableValues.date},
      ${ScoreTableValues.result_idx},
      ${ScoreTableValues.user_idx},
      ${ScoreTableValues.score}
      ) values (
      $idx,
      ${tableValues.date},
      ${tableValues.resultIdx},
      ${tableValues.userIdx},
      ${tableValues.score}
      )
      """.execute.apply()
  }

  def selectAllAsMapList: List[Map[String, Any]] = super.selectAllAsMapList(tableName)
}

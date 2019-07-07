package infra.database.implement

import domain.database.TurnTableValues
import infra.database.TableAccess
import scalikejdbc._

object Turntable extends TableAccess[TurnTableValues] {
  override val tableName = sqls"turn"

  override def createMe: Unit = DB autoCommit { implicit session =>
    sql"""
      create table $tableName (
      ${TurnTableValues.idx} bigint primary key auto_increment,
      ${TurnTableValues.result_idx} bigint not null,
      ${TurnTableValues.scala_idx} int not null,
      ${TurnTableValues.channel_id} varchar(30) not null,
      ${TurnTableValues.user_id} bigint not null,
      ${TurnTableValues.ishii_idx} bigint not null,
      ${TurnTableValues.damage} int not null,
      ${TurnTableValues.log} varchar(1000)
      )
      """.execute.apply()
  }

  override def insert(tableValues: TurnTableValues): Unit = DB.autoCommit { implicit session =>
    val idx = tableValues.idxOption match {
      case _ => null // auto_incrementなのでどうあがいてもnullにする
    }
    sql"""
      insert into $tableName (
      ${TurnTableValues.idx},
      ${TurnTableValues.result_idx},
      ${TurnTableValues.scala_idx},
      ${TurnTableValues.channel_id},
      ${TurnTableValues.user_id},
      ${TurnTableValues.ishii_idx},
      ${TurnTableValues.damage},
      ${TurnTableValues.log}
      ) values (
      $idx,
      ${tableValues.resultIdx},
      ${tableValues.scalaIdx},
      ${tableValues.channelId},
      ${tableValues.userIdx},
      ${tableValues.ishiiIdx},
      ${tableValues.damage},
      ${tableValues.log}
      )
      """.execute.apply()
  }

  def selectAllAsMapList: List[Map[String, Any]] = super.selectAllAsMapList(tableName)
}

package infra.database

import infra.database.implement.{IshiiTable, ResultTable, ScoreTable, Turntable, UserTable}
import infra.implement.FileAccess
import scalikejdbc._

trait DatabaseAccess extends FileAccess {
  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:file:./scala-ishii-bot", "user", "pass")

  private[this] val dbName = "turn"
  if (!isExistFile(dbName)) {
    IshiiTable.createMe
    ResultTable.createMe
    ScoreTable.createMe
    Turntable.createMe
    UserTable.createMe
  }
}

//object main {
//  def main(args: Array[String]): Unit = {
//    Turntable.selectAllAsMapList.foreach(x => println(x))
//  }
//}
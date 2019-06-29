package infra

import java.io.{File, PrintWriter}

import scala.io.Source
import scala.util.{Failure, Success, Try}

trait FileAccess {
  val fileNameOfMuteUsers: String = "src/main/scala/muteUsersList.data"

  def readFile(fileName: String): List[String] = {
    val fileSource = Try(Source.fromFile(fileName))
    fileSource match {
      case Success(value) => value.getLines().toList
      case Failure(exception) => List.empty
    }
  }

  def writeListToFile(fileName: String, textList: List[String]): Unit = {
    val fileSource = new PrintWriter(new File(fileName))
    try textList.foreach(row => fileSource.write(row + "\n"))
    finally fileSource.close()
  }
}

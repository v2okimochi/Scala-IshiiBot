package app

import java.security.SecureRandom

import domain.IshiiState

import scala.util.Random

object Turn {
  val random = new Random(new SecureRandom)

  //ishiiのターン ==> 敵のターン
  def start(ishii: IshiiState): IshiiState = {
    val finishedIshiiTurn: IshiiState = IshiiTurn(ishii)

    //にげるが成功した場合は敵のターンは無し
    if (finishedIshiiTurn.isEscaped) finishedIshiiTurn
    else EnemyTurn(finishedIshiiTurn)
  }
}

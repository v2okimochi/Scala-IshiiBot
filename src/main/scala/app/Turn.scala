package app

import domain.IshiiState

object Turn {
  //ishiiのターン ==> 敵のターン
  def start(ishii: IshiiState): IshiiState = {
    val finishedIshiiTurn: IshiiState = IshiiTurn(ishii)

    //にげるが成功した場合は敵のターンは無し
    if (finishedIshiiTurn.isEscaped) finishedIshiiTurn
    else EnemyTurn(finishedIshiiTurn)
  }
}

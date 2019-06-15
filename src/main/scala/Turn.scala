import scala.util.Random
import java.security.SecureRandom

object Turn {
  val random = new Random(new SecureRandom)

  //ishiiのターン ==> 敵のターン
  def start(ishii: IshiiState): IshiiState = {
    val finishedIshiiTurn: IshiiState = IshiiTurn(ishii)
    //にげるが成功した場合は敵のターンは無し
    if (finishedIshiiTurn.condition == Conditions.escaped) finishedIshiiTurn
    else EnemyTurn(finishedIshiiTurn)
  }
}

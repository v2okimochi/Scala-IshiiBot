import scala.util.Random
import java.security.SecureRandom

object Turn {
  val random = new Random(new SecureRandom)

  //ishiiのターン ==> 敵のターン
  def start(ishii: IshiiState): IshiiState = EnemyTurn(IshiiTurn(ishii))
}

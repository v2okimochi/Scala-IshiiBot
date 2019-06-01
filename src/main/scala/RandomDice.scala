import scala.util.Random
import java.security.SecureRandom

trait RandomDice {
  val random = new Random(new SecureRandom)
}

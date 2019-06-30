package infra

import java.security.SecureRandom
import scala.util.Random

object Randomize {
  val random = new Random(new SecureRandom)
}

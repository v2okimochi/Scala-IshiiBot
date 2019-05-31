import akka.actor.ActorSystem
import slack.rtm.SlackRtmClient
import scala.concurrent.ExecutionContextExecutor

class SetBot {
  val token: String = sys.env("SCALA_BOT_TOKEN_TEST")
  implicit val system: ActorSystem = ActorSystem("slack")
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  val client: SlackRtmClient = SlackRtmClient(token)
}

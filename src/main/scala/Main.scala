import app.Turn
import infra.{SlackClient, SlackClientImpl}

object Main extends Turn {
  val slackClient: SlackClient =
//    new SlackClientImpl
          new infra.SlackClientLocalMock

  def main(args: Array[String]): Unit = slackClient.listen(switchMessage)
}

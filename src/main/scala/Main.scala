import app.Turn
import domain.{Command, Condition, Help, IshiiState, Score, Scoring}
import infra.{FileAccess, SlackClient, SlackClientImpl}
import slack.models.Message

object Main extends Turn {
  val slackClient: SlackClient =
    new SlackClientImpl
  //        new infra.SlackClientLocalMock

  def main(args: Array[String]): Unit = slackClient.listen(switchMessage)
}

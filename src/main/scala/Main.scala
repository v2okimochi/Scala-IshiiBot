import app.Turn
import com.google.inject.{Guice, Inject}
import infra.SlackClient
import modules.SlackClientModule

object Main {
  def main(args: Array[String]): Unit =
    Guice.createInjector(new SlackClientModule).getInstance(classOf[SlackListener]).listenSlack
}

class SlackListener @Inject()(slackClient: SlackClient) {
  def listenSlack: Unit = slackClient.listen(new Turn(slackClient).switchMessage)
}

import akka.actor.ActorSystem
import slack.models.Message
import slack.rtm.SlackRtmClient

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

trait SlackClient {
  def listen(action: Message => Unit): Unit
  def sendMessage(originalMessage: Message, text: String): Unit
  def getUserName(message: Message): String
}

// slack apiの設定
class SlackClientImpl extends SlackClient {

  private implicit val system: ActorSystem = ActorSystem("slack")
  private implicit val ec: ExecutionContextExecutor = system.dispatcher
  private val token: String = sys.env("SCALA_BOT_TOKEN")
  private val client: SlackRtmClient = SlackRtmClient(token)

  def listen(action: Message => Unit): Unit = client.onEvent {
    //チャンネルに書き込まれた＆スレッド返信ではない
    case m: Message => action(m)
    case _ => //ignore
  }

  def sendMessage(originalMessage: Message, text: String): Unit = {
    client.sendMessage(originalMessage.channel, text, originalMessage.thread_ts)
  }

  def getUserName(message: Message): String = client.apiClient
      .getUserInfo(message.user)
      .profile
      .get.real_name.getOrElse("名無しさん").toString
}

class SlackClientLocalMock extends SlackClient {
  def quit(): Unit = ()
  def listen(action: Message => Unit): Unit = {
    def waitInput(input: String): Unit = input match {
      case "q" => quit()
      case s => {
        action(Message("", "", "Mockさん", s, None, None))
        waitInput(StdIn.readLine())
      }
    }
    waitInput(StdIn.readLine())
  }
  def sendMessage(originalMessage: Message, text: String): Unit = {
    println(text)
  }
  def getUserName(message: Message): String = message.user
}
import akka.actor.ActorSystem
import slack.models.Message
import slack.rtm.SlackRtmClient
import slack.api.SlackApiClient
import slack.api.BlockingSlackApiClient

import scala.concurrent.{Await, ExecutionContextExecutor}
import scala.concurrent.duration._
import scala.io.StdIn

trait SlackClient {
  def listen(action: Message => Unit): Unit

  def sendMessage(originalMessage: Message, text: String): Unit

  def getUserName(message: Message): String

  def leaveChannel(message: Message): Unit
}

// slack apiの設定
class SlackClientImpl extends SlackClient {

  private implicit val system: ActorSystem = ActorSystem("slack")
  private implicit val ec: ExecutionContextExecutor = system.dispatcher
  private val token: String = sys.env("SCALA_BOT_TOKEN")
  private val rtmClient: SlackRtmClient = SlackRtmClient(token)
  private val client = SlackApiClient(token)
  private val block = BlockingSlackApiClient(token)

  override def listen(action: Message => Unit): Unit = rtmClient.onEvent {
    //チャンネルに書き込まれた＆スレッド返信ではない
    case m: Message => action(m)
    case _ => //ignore
  }

  override def sendMessage(originalMessage: Message, text: String): Unit = {
    rtmClient.sendMessage(originalMessage.channel, text, originalMessage.thread_ts)
  }

  override def getUserName(message: Message): String = rtmClient.apiClient
    .getUserInfo(message.user)
    .profile
    .get.real_name.getOrElse("名無しさん").toString

  override def leaveChannel(message: Message): Unit = {
    val result = client.leaveChannel(message.channel)
    Await.ready(result, 5000 millisecond)
    println(result)
  }
}

class SlackClientLocalMock extends SlackClient {
  private def quit(): Unit = ()

  override def listen(action: Message => Unit): Unit = {
    def waitInput(input: String): Unit = input match {
      case "q" => quit()
      case s => {
        action(Message("", "", "Mockさん", s, None, None))
        waitInput(StdIn.readLine())
      }
    }

    waitInput(StdIn.readLine())
  }

  override def sendMessage(originalMessage: Message, text: String): Unit = {
    println(text)
  }

  override def getUserName(message: Message): String = message.user

  override def leaveChannel(message: Message): Unit = println("成功")

}
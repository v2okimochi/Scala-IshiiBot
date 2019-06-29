package infra

import akka.actor.ActorSystem
import slack.api.{BlockingSlackApiClient, SlackApiClient}
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
  private val rtmClient: SlackRtmClient = SlackRtmClient(token)
  private val client = SlackApiClient(token)
  private val block = BlockingSlackApiClient(token)

  override def listen(action: Message => Unit): Unit = rtmClient.onEvent {
    //チャンネルに書き込まれた＆スレッド返信ではない
    case m: Message => action(m)
    case _ => //ignore
  }

  override def sendMessage(originalMessage: Message, text: String): Unit = 
    rtmClient.sendMessage(originalMessage.channel, text, originalMessage.thread_ts)
  
  override def getUserName(message: Message): String = rtmClient.apiClient
    .getUserInfo(message.user)
    .profile
    .get.real_name.getOrElse("名無しさん").toString
}

class SlackClientLocalMock extends SlackClient {
  private def quit(): Unit = ()

  override def listen(action: Message => Unit): Unit = {
    def waitInput(input: String): Unit = input match {
      case "q" => quit()
      case s => {
        action(Message(ts = "",
          channel = "channel1",
          user = "Mockさん",
          text = s,
          is_starred = None,
          thread_ts = None))
        waitInput(StdIn.readLine())
      }
    }

    waitInput(StdIn.readLine())
  }

  override def sendMessage(originalMessage: Message, text: String): Unit =
    println(text)

  override def getUserName(message: Message): String = message.user
}
package infra.implement

import infra.SlackClient
import slack.models.Message

import scala.io.StdIn

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

  override def isBot(message: Message): Boolean = false
}

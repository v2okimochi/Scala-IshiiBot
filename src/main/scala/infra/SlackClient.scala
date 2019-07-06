package infra

import slack.models.Message

trait SlackClient {
  def listen(action: Message => Unit): Unit

  def sendMessage(originalMessage: Message, text: String): Unit

  def getUserName(message: Message): String

  def isBot(message: Message): Boolean
}



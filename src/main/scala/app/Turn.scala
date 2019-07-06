package app

import domain.{Command, Condition, Enemy, Help, IshiiState, Randomize, Score, Scoring, TurnState}
import infra.{FileAccess, SlackClient}
import slack.models.Message

trait Turn extends FileAccess {
  val slackClient: SlackClient
  private var turnList: Seq[TurnState] = Seq(
    TurnState.apply(enemy = Enemy.getRandomEnemy)
  )

  // slackに投稿された発言を解析
  def switchMessage(message: Message): Unit = message match {
    case msg if isMutedChannel(msg.channel) =>
      // ミュート時はこの分岐だけに飛ぶ
      if (Command.isFight(msg.text)) fight(msg, Command.Fight)
    case msg if Command.isEscape(msg.text) => escape(msg, Command.FailureEscape)
    case msg if Help.search(msg.text).isDefined => help(msg, Help.search(msg.text).get)
    case msg => Command.parse(msg.text).foreach(c => startByMessage(msg, c))
  }

  // チャンネルのミュートを解除
  def fight(message: Message, command: Command): Unit = {
    val channelId: String = message.channel
    val newMuteUsersList: List[String] = readFile(fileNameOfMuteUsers).filterNot(_ == channelId)
    writeListToFile(fileNameOfMuteUsers, newMuteUsersList)
    slackClient.sendMessage(message, "Scalaのむれが あらわれた！")
  }

  // チャンネルをミュート
  def escape(message: Message, command: Command): Unit = startByMessage(message, Command.FailureEscape)

  // statusなどのヘルプ表示
  def help(message: Message, command: String): Unit = {
    command match {
      case Help.Status.id =>
        val randomNumber = Randomize.random.nextInt(100)
        slackClient.sendMessage(message, Help.getStatusMessage(turnList.last.ishiiState, randomNumber))
      case _ => //ignore
    }
  }

  // 1ターンの戦いが始まる
  def startByMessage(message: Message, command: Command): Unit = {
    val userName: String = slackClient.getUserName(message)
    val stateOfTurn: TurnState = start(turnList.last
      .copy(channelId = message.channel,
        userName = userName,
        ishiiState = turnList.last.ishiiState.copy(command = Some(command)),
        enemy = Enemy.getRandomEnemy,
        log = ""))

    if (stateOfTurn.ishiiState.command.isDefined) turnList :+= stateOfTurn
    //    debugStatusPrint(ishiiList.last)

    // 1ターンのログを発言
    slackClient.sendMessage(message, turnList.last.log.mkString)

    // ishiiが力尽きたらターンログと得点を発言してishiiListをリセット
    if (turnList.last.ishiiState.condition == Some(Condition.Dead)) {
      val turnHistoryText: String = getTurnHistoryText(message, turnList)
      val scoreText: String = getScoreText(message, Scoring.getScoreList(turnList))
      slackClient.sendMessage(message, turnHistoryText + scoreText)
      turnList = Seq(
        TurnState.apply(enemy = Enemy.getRandomEnemy)
      )
    }
  }

  //ishiiのターン ==> 敵のターン
  def start(turn: TurnState): TurnState = {
    val finishedIshiiTurn: TurnState = IshiiTurn(turn)

    //にげるが成功した場合は敵のターンは無し
    if (finishedIshiiTurn.ishiiState.isEscaped) finishedIshiiTurn
    else EnemyTurn(finishedIshiiTurn)
  }

  // 既にミュートリストにあるチャンネルならtrue
  private def isMutedChannel(channelId: String): Boolean =
    readFile(fileNameOfMuteUsers).contains(channelId)

  // 力尽きるまでの得点を整理して発言
  private def getScoreText(message: Message, scoreList: Seq[Score]): String = {
    val text: String = "===== 得点ランキング =====\n"
    val scoreListText: Seq[String] = scoreList.map(score =>
      s"${scoreList.indexOf(score) + 1}位：${score.userName} ${score.score}点")
    text + scoreListText.mkString("\n") + "\n"
  }

  // 力尽きるまでのターンログを整理して発言
  private def getTurnHistoryText(message: Message, turnList: Seq[TurnState]): String = {
    val text: String = "===== ターンログ =====\n"
    val turnLogList: Seq[String] = turnList.tail.map(turn => {
      val condition: String = turn.ishiiState.condition match {
        case Some(c) => c.label
        case None => "なし"
      }
      val command: String = turn.ishiiState.command match {
        case Some(c) => c.label
        case None => "なし"
      }
      s"${turn.number}ターン目 " +
        s"(状態異常：$condition) " +
        s"${turn.userName}の命令：$command"
    })
    text + turnLogList.mkString("\n") + "\n"
  }

  private def debugStatusPrint(turn: TurnState): Unit =
    println(s"turn: ${turn.number}\n " +
      s"スカラターン: ${turn.scalaTurnNumber}\n " +
      s"HP: ${turn.ishiiState.hitPoint}\n " +
      s"MP: ${turn.ishiiState.magicPower}\n " +
      s"守備力: ${turn.ishiiState.defence}")
}

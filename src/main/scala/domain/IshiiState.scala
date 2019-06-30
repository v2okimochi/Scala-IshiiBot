package domain

case class IshiiState(
                       turn: Int = 0, // ishiiが耐えたターン数
                       scalaTurn: Int = -1, // Scalaの効果持続ターン数
                       channelId: String = "", //Scalaを唱えさせたチャンネルID
                       userName: String = "", // Scalaを唱えさせたユーザ名
                       command: Option[Command] = None, // ishiiが選んだ行動
                       enemyNum: Int = 0, // 敵の番号
                       enemyAct: String = "", // 敵の行動
                       damage: Int = 0, // ishiiが受けるダメージ
                       hitPoint: Int = 100,
                       magicPower: Int = 20,
                       defence: Int = 30,
                       mental: Int = 5,
                       condition: Option[Condition] = None,
                       log: List[String] = Nil
                     ) {
  def isGuarding: Boolean = command.contains(Command.Guard)

  def isEscaped: Boolean = command.contains(Command.SuccessEscape)
}

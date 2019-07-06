package domain

case class IshiiState(
                       hitPoint: Int = 100,
                       magicPower: Int = 20,
                       defence: Int = 60,
                       mental: Int = 5,
                       condition: Option[Condition] = None,
                       command: Option[Command] = None
                     ) {
  def isGuarding: Boolean = command.contains(Command.Guard)

  def isDead: Boolean = condition.contains(Condition.Dead)

  def isEscaped: Boolean = command.contains(Command.SuccessEscape)

  def isMad: Boolean = mental == 0
}

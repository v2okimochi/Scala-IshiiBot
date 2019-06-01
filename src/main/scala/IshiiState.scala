case class IshiiState(
                       turn: Int = 0,
                       scalaTurn: Int = -1,
                       user: String = "",
                       command: String = "",
                       enemyNum: Int = 0,
                       enemyAct: String = "",
                       damage: Int = 0,
                       hitPoint: Int = 100,
                       magicPower: Int = 20,
                       defence: Int = 60,
                       mental: Int = 5,
                       condition: String = "",
                       log: List[String] = Nil
                     )
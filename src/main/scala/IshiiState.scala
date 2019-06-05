case class IshiiState(
                       turn: Int = 0, // ishiiが耐えたターン数
                       scalaTurn: Int = -1, // Scalaの効果持続ターン数
                       user: String = "", // Scalaを唱えさせたユーザ
                       command: String = "", // ishiiが選んだ行動
                       enemyNum: Int = 0, // 敵の番号
                       enemyAct: String = "", // 敵の行動
                       damage: Int = 0, // ishiiが受けるダメージ
                       hitPoint: Int = 100,
                       magicPower: Int = 20,
                       defence: Int = 30,
                       mental: Int = 5,
                       condition: String = "",
                       log: List[String] = Nil
                     )
# Scalaを唱える石井bot

# 何のために使うの？
- サツバツSlackチャンネルを強く生きてください


# botの置き方
- 適当なサーバで実行してください
- Scalaでできているので、sbtなどでコンパイルする必要があります
- sbt runやsbt assemblyなどおすすめです
- 予めコンパイルしておいてjavaコマンドで実行するのもアリです


# ダメージ計算
- ダメージ計算式は
`(敵の攻撃力 / 2 )- ( botの守備力 / 4 )`が基準です
- 約5%の確率で痛恨の一撃が発生します。痛恨時は敵の攻撃力を1.5倍として扱います


# 使い方
- Slackのチャンネルにbotを招待して使います
- botはチャンネル内の発言やコマンドに反応します
- スレッド内で使うとスレッドに発言します

## ステータス確認
`ish status`

- botのステータスは共通です。自分の行動でいきなり力尽きても恨まないでください
- botの1行動が1ターンとして扱われます

## Scalaを唱える
【完全一致】

`ish scala`

`スカラ`

`スクルト`

【部分一致】

`すから`

`すくると`

- botはScalaを唱える度に守備力が上がります
- 守備力の上昇幅は40%, 50%, 100%, 150%のうちランダムです
- Scalaにまつわる用語たちの猛攻をしのいで強く生きてください
- Scalaで上げられる分の守備力は200までです。それ以上かけようとしても効果がありません
- Scalaの効果は、最後にかけてから5ターン目の行動後に切れます。周囲との読み合いに応じて適切なScala延ばしを行ってください

## 防御してやり過ごす
`ish guard`

`ぼうぎょ`

- 敵の攻撃は防御することで半分に減らせます
- なんと痛恨の一撃もダメージを半分にできます

## まほうのせいすい
【完全一致】

`ish mhw`

`ish magicalholywater`

【部分一致】

`まほうのせいすい`

- botのMPを1～20の範囲でランダムに回復します
- 魔法の力でMPが増えるのでMagical Holy Waterです

## ホイミ
【完全一致】

`ish heal`

【部分一致】

`ホイミ`

- botのHPを10～40の範囲でランダムに回復します

## にげる
`にげる`

- 25%の確率で逃げることができます
- 成功するとそのチャンネルがミュートリストに追加され、以降そのチャンネルではコマンドに反応しなくなります。botが煩わしくなった時に使ってください
- ミュート状態を解除するには「たたかう」を発言してください

## たたかう
`たたかう`

- botが戦闘に参加し、コマンドを受けつけるようになります。ミュートされたチャンネルでだけ使用できます
- チャンネルがミュートされる条件は、チャンネル内で「にげる」が成功した時だけです。チャンネルに招待されたばかりのbot
は既に戦う準備ができているので、安心してScalaを唱えさせてください


# プルリクのすすめ
- MITライセンスです
- Pull requests and issues are very welcomeをしています
- プルリクしたいけどコードがよくわからない場合は質問歓迎です。できる限り答えます

## 使用する外部ライブラリ
- [slack-scala-client](https://github.com/slack-scala-client/slack-scala-client)  
Copyright (c) 2015 Bryan Gilbert  
Released under the MIT license

## 開発者の方は、ローカル環境でbotを動かすことができます
- slackとlocalどちらで動かすかは、 `modules/SlackClientModule`で指定します
- Slackで使う場合はSlackClientImplと、ローカルコンソールで使う場合はSlackClientLocalMockと接続してください

## ishiiの行動を増やしたい
- ishiiの行動選択肢はCommand.scalaにまとめています
- 新たな行動を追加するにはオブジェクトを以下のように追記し、どの発言に反応させたいかを指定します

```scala
case object Scala extends Command(label = "Scala", mp = 2, score = 100) {
    // 部分一致で反応させたいコマンド
    override val substrings: Set[String] = Set(
      "すから",
      "すくると"
    )
    // 完全一致で反応させたいコマンド
    override val perfectMatchingWords: Set[String] = Set(
      "ish scala",
      "スカラ",
      "スクルト"
    )
 }
```

- 次に `IshiiTurn.scala`の `doXXX(turn: TurnState)`として実際の処理を追記します
- 最後に、追記した `doXXX()`を `apply()`内で紐付ければbotが行動できるようになります

## ish statusをリッチにしたい
- statusなど行動と無関係な処理はHelp.scalaにまとめています
- showStatusメソッドでishiiのセリフを条件分岐しています
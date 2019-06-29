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
- slackとlocalどちらで動かすかは、infra.SlackClient
.scalaのSlackClientImplクラスとSlackClientLocalMockどちらを使うかで決めます
- Main.scalaのMainオブジェクト内でどちらをインスタンス化するか指定してください（初期設定はSlackClientImpl）

## ishiiの状態を変えたい
- ishiiの状態はIshiiState型としてIshiiState.scalaにまとめています
- 各メソッドは「IshiiState型オブジェクトの一部を変えたコピー」を作って戻り値としま
- 多くのメソッドがIshiiState型を受け取って同じIshiiState型を返すのはそのためです
- HPや守備力などの状態を変えたい場合は、IshiiStateオブジェクトのcopyメソッドを呼び出す際に任意の値を指定してください

例：
```scala
ishii.copy(hitPoint = ishii.hitPoint - 20)
```

- また、ishiiの状態を初期値に戻したい場合はapply()メソッドを利用できます

```scala
ishii.copy(hitPoint = IshiiState.apply().hitPoint)
```

## ishiiの行動を増やしたい
- ishiiの行動選択肢はCommand.scalaにまとめています
- Commandを継承したオブジェクトを設定し、どの発言に反応させたいかをparseメソッド内に追記します
- 最後にIshiiTurn.scalaのdoXXXメソッドとして実際の処理を追記します
- 追記したメソッドをapplyメソッド内で紐付ければbotが行動できるようになります

## ish statusをリッチにしたい
- statusなど行動と無関係な処理はHelp.scalaにまとめています
- showStatusメソッドでishiiのセリフを条件分岐しています
name := "slack-bot-scala"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.google.inject" % "guice" % "4.0",
  "com.github.slack-scala-client" %% "slack-scala-client" % "0.2.6",
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  //  "com.typesafe.slick" %% "slick" % "2.1.0",
  //  "org.slf4j" % "slf4j-nop" % "1.6.4"
  "org.scalikejdbc" %% "scalikejdbc" % "3.2.+",
  "org.slf4j" % "slf4j-simple" % "1.7.+",
  "com.h2database" % "h2" % "1.4.+"
) 

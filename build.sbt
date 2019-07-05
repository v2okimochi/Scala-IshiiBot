name := "slack-bot-scala"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++=Seq(
  "com.github.slack-scala-client" %% "slack-scala-client" % "0.2.6",
  
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
) 

enablePlugins(JavaServerAppPackaging)

name := "UpAndRunning-BobsAkka"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= {
  val akkaVersion = "2.4.9"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
    "io.spray" %% "spray-json" % "1.3.4",
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "org.slf4j" % "slf4j-api" % "1.7.25",
    "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "2.2.0" % Test
  )
}

//Assembly Settings
mainClass in assembly := Some("com.github.janikibichi.bobsAkka.upandrunning.Main")

assemblyJarName in assembly := "UpAndRunning-BobsAkka.jar"
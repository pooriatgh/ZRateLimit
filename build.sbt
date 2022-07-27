ThisBuild / scalaVersion := "3.1.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "pooria"
ThisBuild / organizationName := "zio"

val AkkaVersion = "2.6.14"

lazy val root = (project in file("."))
  .settings(
    name := "ZData",
    libraryDependencies ++= Seq(
      "com.lightbend.akka" %% "akka-stream-alpakka-file" % "3.0.4",
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion
    ).map(_.cross(CrossVersion.for3Use2_13)) ++ Seq(
      "dev.zio" %% "zio" % "2.0.0",
      "dev.zio" %% "zio-streams" % "2.0.0",
      "org.typelevel" %% "cats-core" % "2.7.0",
      "org.typelevel" %% "cats-effect" % "3.3.14"
    )
  )

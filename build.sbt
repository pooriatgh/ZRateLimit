ThisBuild / scalaVersion :="3.0.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "pooria"
ThisBuild / organizationName := "zio"


lazy val root = (project in file("."))
  .settings(
    name := "ZData",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.0",
      "dev.zio" %% "zio-json" % "0.3.0-RC10",
      "io.d11" %% "zhttp" % "2.0.0-RC10",
      "dev.zio" %% "zio-streams" % "2.0.0",
      "dev.zio" %% "zio-macros" % "2.0.0",
      "dev.zio" %% "zio-test" % "2.0.0" % "test",
      "dev.zio" %% "zio-test-sbt" % "2.0.0" % "test",
      "dev.zio" %% "zio-test-magnolia" % "2.0.0" % "test" // optional
    )
  )

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

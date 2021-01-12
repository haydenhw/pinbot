import Dependencies._

ThisBuild / scalaVersion := "2.13.4"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

trapExit := false

lazy val root = (project in file("."))
  .settings(
    name := "cala-pg",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.postgresql" % "postgresql" % "9.3-1102-jdbc41",
      "com.softwaremill.sttp.client3" %% "core" % "3.0.0-RC15"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

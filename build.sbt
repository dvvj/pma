
ThisBuild / organization := "org.ditw"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.7"

lazy val global = project
  .in(file("."))
  .aggregate(
    common,
    pmXml
  )

lazy val common = project
  .settings(
    libraryDependencies ++= commonDeps
  )
lazy val pmXml = project.dependsOn(common)
  .settings(
    libraryDependencies ++= commonDeps
  )

name := "pubmed analysis"

lazy val dependencies = new {
  val scalaTestVer = "3.0.4"
  val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVer
  val xtract = "com.lucidchart" %% "xtract" % "2.0.1"
  val xml = "org.scala-lang.modules" %% "scala-xml" % "1.1.1"
  val commonIO = "commons-io" % "commons-io" % "2.6"
}

lazy val commonDeps = Seq(
  dependencies.scalaTest,
  dependencies.xtract,
  dependencies.xml,
  dependencies.commonIO
)

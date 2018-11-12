
ThisBuild / organization := "org.ditw"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.7"

lazy val global = project
  .in(file("."))
  .aggregate(
    common,
    pmXml,
    sparkRun
  )

lazy val common = project
  .settings(
    libraryDependencies ++= commonDeps
      ++ sparkDeps
  )

lazy val sparkDeps = Seq(
  dependencies.spark2_12
)
lazy val sparkRun = project
  .in(file("spark-run"))
  .settings(
    libraryDependencies ++= commonDeps
      ++ sparkDeps
  )
  .dependsOn(common)

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
  val spark2_12 = "org.apache.spark" %% "spark-core" % "2.4.0"
}

lazy val commonDeps = Seq(
  dependencies.scalaTest,
  dependencies.xtract,
  dependencies.xml,
  dependencies.commonIO
)

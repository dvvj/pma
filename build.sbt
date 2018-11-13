
ThisBuild / organization := "org.ditw"
ThisBuild / version := "0.1.0-SNAPSHOT"
//ThisBuild / scalaVersion := "2.12.7"
ThisBuild / scalaVersion := "2.11.12"

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
  dependencies.spark2_11
)
lazy val sparkRun = project
  .in(file("spark-run"))
  .settings(
    libraryDependencies ++= commonDeps
      ++ sparkDeps,
    exportJars := true
    ,assemblyStrategySetting
  )
  .dependsOn(common, pmXml)

lazy val pmXml = project.dependsOn(common)
  .settings(
    libraryDependencies ++= commonDeps
  )

name := "pubmed analysis"

lazy val dependencies = new {
  val scalaTestVer = "3.0.4"
  val hadoopVer = "2.7.3"
  val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVer
  val xtract = "com.lucidchart" %% "xtract" % "2.0.1"
  val xml = "org.scala-lang.modules" %% "scala-xml" % "1.1.1"
  val commonIO = "commons-io" % "commons-io" % "2.6"
  val spark2_11 = "org.apache.spark" %% "spark-core" % "2.3.0"
  val spark2_12 = "org.apache.spark" %% "spark-core" % "2.4.0"
  val hadoop_aws = "org.apache.hadoop" % "hadoop-aws" % hadoopVer
  val hadoop_common = "org.apache.hadoop" % "hadoop-common" % hadoopVer
}

lazy val commonDeps = Seq(
  dependencies.scalaTest,
  dependencies.xtract,
  dependencies.xml,
  dependencies.commonIO,
  dependencies.hadoop_aws,
  dependencies.hadoop_common
)

//lazy val assemblyStrategySetting = assemblyMergeStrategy in assembly := {
//  case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
//  case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
//  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
//  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
//  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
//  case PathList("com", "google", xs @ _*) => MergeStrategy.last
//  case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
//  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
//  case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
//  case "about.html" => MergeStrategy.rename
//  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
//  case "META-INF/mailcap" => MergeStrategy.last
//  case "META-INF/mimetypes.default" => MergeStrategy.last
//  case "plugin.properties" => MergeStrategy.last
//  case "log4j.properties" => MergeStrategy.last
//  case x =>
//    val oldStrategy = (assemblyMergeStrategy in assembly).value
//    oldStrategy(x)
//}

lazy val assemblyStrategySetting = assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
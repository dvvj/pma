package org.ditw.pma.sparkRun

import org.ditw.pma.common.spark.SparkUtils

object SparkTest extends App {

  val spark = SparkUtils.sparkContextLocal()

  val lines = spark.textFile("/media/sf_vmshare/aff-w2v")

  println(s"lines: ${lines.count()}")

  spark.stop()

}

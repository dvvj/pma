package org.ditw.pma.sparkRun

import com.lucidchart.open.xtract.XmlReader
import org.ditw.pma.common.spark.SparkUtils
import org.ditw.pmxml.model.ArtiSet

import scala.xml.XML

object ExtractPM {

  def main(args:Array[String]):Unit = {
    val spark = SparkUtils.sparkContextLocal()
    val runLocally = if (args.length > 0) args(0).toBoolean else true
    val inputPath = if (args.length > 1) args(1) else "file:///media/sf_vmshare/_pmd/*.xml"
    val parseErrors = spark.wholeTextFiles(inputPath)
      .map { p =>
        val xml = XML.loadString(p._2)
        val parsed = XmlReader.of[ArtiSet].read(xml)
        val errors = parsed.errors.size
        if (errors > 0)
          println(parsed.errors)
        errors
      }

    println(s"lines: ${parseErrors.sum}")

    spark.stop()

  }

}

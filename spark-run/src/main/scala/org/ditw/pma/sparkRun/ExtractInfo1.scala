package org.ditw.pma.sparkRun

import org.apache.spark.storage.StorageLevel
import org.ditw.pma.common.spark.SparkUtils
import org.ditw.pmxml.model.{AAAuAff, Arti}

object ExtractInfo1 {

  def main(args:Array[String]):Unit = {
    val runLocally = if (args.length > 0) args(0).toBoolean else true
    val inputPath = if (args.length > 1) args(1) else "file:///media/sf_vmshare/pmj9"
    val outputPath = if (args.length > 2) args(2) else "file:///media/sf_vmshare/pmj9AuAff"
    val parts = if (args.length > 3) args(3).toInt else 4

    val spark = SparkUtils.sparkContext(runLocally, "ExtractInfo1", parts)

    val pmid2AuAff = spark.textFile(inputPath)
      .map(Arti.readJson)
      .map { arti =>
//        val pmid = arti.citation.pmid
//        val ids = arti.artiIds
        val auLst = arti.citation.authors
        arti.citation.pmid -> AAAuAff.createFrom(auLst)
      }
      .sortBy(_._1)
      .persist(StorageLevel.MEMORY_AND_DISK_SER_2)

    pmid2AuAff.mapValues(AAAuAff.toJson)
      .saveAsTextFile(outputPath)

    spark.stop()
  }

}

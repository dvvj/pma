package org.ditw.pmxml.parsing

import java.nio.charset.StandardCharsets

import org.apache.commons.io.IOUtils

import scala.io.Source

object TestHelpers {

  def loadRes(path:String):String = {
    val is = getClass.getResourceAsStream(path)
    val res = IOUtils.toString(is, StandardCharsets.UTF_8)
    is.close()
    res
  }

  val TestStr_Simple:String = loadRes("/testdata/simple.xml")

  val TestStr_AuthorWithIdentifier = loadRes("/testdata/author-identifier.xml")
}

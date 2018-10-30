package org.ditw.pmxml.model

import com.lucidchart.open.xtract.{ XmlReader, __ }
import com.lucidchart.open.xtract.XmlReader._
import Constants.XmlTags._
import cats.syntax.all._

case class AbstractText(
                       label:String,
                       category:String,
                       text:String
                       ) {

}

object AbstractText {
  implicit val reader:XmlReader[AbstractText] = (
    attribute[String](Label),
    attribute[String](NlmCategory),
    (__).read[String]
  ).mapN(apply)
}
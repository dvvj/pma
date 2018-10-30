package org.ditw.pmxml.model

import com.lucidchart.open.xtract.{XmlReader, __}

import Constants.XmlTags._
import cats.syntax.all._

case class Arti(
                 citation:Citation,
                 pubStatus:String,
                 artiIds:ArtiIds
               ) {

}

object Arti extends Serializable {
  implicit val reader:XmlReader[Arti] = (
    (__ \ MedlineCitation).read[Citation],
    (__ \ PubmedData \ PublicationStatus).read[String],
    (__ \ PubmedData \ ArticleIdList).read[ArtiIds]
  ).mapN(apply)
}

package org.ditw.pmxml.model

import com.lucidchart.open.xtract.{XmlReader, __}
import Constants.XmlTags._
import cats.syntax.all._
import org.json4s.DefaultFormats

case class Arti(
                 citation:Citation,
                 pubStatus:String,
                 artiIds:ArtiIds
               ) {

}

object Arti extends Serializable {
  private val _fmts = DefaultFormats
  def toJson(arti:Arti):String = {
    implicit val fmts = _fmts
    import org.json4s.jackson.Serialization.write
    write(arti)
  }

  implicit val reader:XmlReader[Arti] = (
    (__ \ MedlineCitation).read[Citation],
    (__ \ PubmedData \ PublicationStatus).read[String],
    (__ \ PubmedData \ ArticleIdList).read[ArtiIds]
  ).mapN(apply)
}

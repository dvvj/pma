package org.ditw.pmxml.model

import com.lucidchart.open.xtract.{ XmlReader, __ }
import com.lucidchart.open.xtract.XmlReader._

import Constants.XmlTags._
import cats.syntax.all._

case class Citation(
                   pmid:Int
                   ) {

}

object Citation extends Serializable {
  implicit val reader:XmlReader[Citation] = (
    (__ \ PMID).read[Int].map(apply)
  )
}

case class ArtiId(
                 idType:String,
                 idVal:String
                 )


object ArtiId extends Serializable {
  implicit val reader:XmlReader[ArtiId] = (
      (
        attribute[String](IdType),
        (__).read[String]
      )
    ).mapN(apply)
}

case class ArtiIds(
                  ids:Seq[ArtiId]
                  )

object ArtiIds extends Serializable {
  implicit val reader:XmlReader[ArtiIds] = (
      (
        (__ \ ArticleId).read(seq[ArtiId].map(apply))
      )
    )
}

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

case class ArtiSet(artis:Seq[Arti]) {

}

object ArtiSet extends Serializable {
  implicit val reader:XmlReader[ArtiSet] = (
    (__ \ PubmedArticle).read(seq[Arti]).map(apply)
  )
}
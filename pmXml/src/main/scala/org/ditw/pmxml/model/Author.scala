package org.ditw.pmxml.model

import com.lucidchart.open.xtract.XmlReader._
import com.lucidchart.open.xtract.{XmlReader, __}
import org.ditw.pmxml.model.Constants.XmlTags._
import cats.syntax.all._

case class Author(
                 _isValid:String,
                 lastName:String,
                 foreName:String,
                 initials:String,
                 affInfo:Seq[AffInfo],
                 idfr:Option[Identifier] = None
                 ) {
  val isValid:Boolean = _isValid == "Y"
}

object Author extends Serializable {
  implicit val reader:XmlReader[Author] =
    (
      attribute[String](ValidYN),
      (__ \ LastName).read[String],
      (__ \ ForeName).read[String],
      (__ \ Initials).read[String],
      (__ \ AffiliationInfo).read(seq[AffInfo]),
      (__ \ _Identifier).read[Identifier].optional
    ).mapN(apply)
}
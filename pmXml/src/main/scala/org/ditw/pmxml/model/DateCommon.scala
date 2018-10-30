package org.ditw.pmxml.model

import com.lucidchart.open.xtract.{ XmlReader, __ }
import Constants.XmlTags._
import cats.syntax.all._

import DateCommon._
case class DateCommon(
                     year:Int,
                     private val _month:String,
                     day:Int
                     ) {
  val month:Int = {
    if (_month.forall(_.isDigit)) {
      _month.toInt
    }
    else {
      monthMap(_month)
    }
  }

}

object DateCommon {
  private val monthMap:Map[String, Int] = Map(
    List("Jan") -> 1,
    List("Feb") -> 2,
    List("Mar") -> 3,
    List("Apr") -> 4,
    List("May") -> 5,
    List("Jun") -> 6,
    List("Jul") -> 7,
    List("Aug") -> 8,
    List("Sept", "Sep") -> 9,
    List("Oct") -> 10,
    List("Nov") -> 11,
    List("Dec") -> 12
  ).flatMap(kv => kv._1.map(_ -> kv._2))

  implicit val reader:XmlReader[DateCommon] = (
    (__ \ Year).read[Int],
    (__ \ Month).read[String],
    (__ \ Day).read[Int]
  ).mapN(apply)
}

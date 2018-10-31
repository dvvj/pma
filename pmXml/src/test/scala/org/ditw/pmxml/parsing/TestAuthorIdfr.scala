package org.ditw.pmxml.parsing

import com.lucidchart.open.xtract.XmlReader
import org.ditw.pmxml.model.{ArtiSet, Author, Identifier}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import scala.xml.XML

class TestAuthorIdfr extends FlatSpec with Matchers with TableDrivenPropertyChecks {

  import TestHelpers._
  private val testData = Table(
    ("testxml", "authorsWithIdentifier"),
    (
      TestStr_AuthorWithIdentifier,
      Map(
        28993670 -> Seq(
          Author(
            "Y", "Palesi", "Fulvia", "F",
            Option(Identifier("ORCID", "http://orcid.org/0000-0001-5027-8770"))
          )
        )
      )
    )
  )

  "Author with identifier tests" should "pass" in {
    forAll (testData) { (textxml, authorsWithId) =>
      val xml = XML.loadString(textxml)
      val parsed = XmlReader.of[ArtiSet].read(xml)

      val pmid2AuthorsWithIds = parsed.map { p =>
        p.artis.map(arti => arti.citation.pmid -> arti.citation.authors.authors.filter(_.idfr.nonEmpty)).toMap
      }.getOrElse(Map())
      println(pmid2AuthorsWithIds.size)

      pmid2AuthorsWithIds shouldBe authorsWithId
    }
  }
}

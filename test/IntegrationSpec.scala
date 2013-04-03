package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import controllers.{Home, DynamicGlobal, Welcome}

class IntegrationSpec extends Specification {

  trait StubWelcome extends Welcome {
    override def message = "Stub Welcome"
  }

  val stubs = new DynamicGlobal {
    override lazy val home = new Home with StubWelcome
  }

  "Home page" should {

    "give stubbed message with stub" in {
      running(TestServer(3333, FakeApplication(withGlobal = Some(stubs))), HTMLUNIT) {
        browser =>

          browser.goTo("http://localhost:3333/")
          browser.pageSource must contain("Stub Welcome")

      }
    }

    "give standard message with no stubs" in {
      running(TestServer(3333, FakeApplication()), HTMLUNIT) {
        browser =>

          browser.goTo("http://localhost:3333/")
          browser.pageSource must contain("Your new application is ready.")

      }
    }

  }

}
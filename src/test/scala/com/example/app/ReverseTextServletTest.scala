package com.example.app

import com.example.app.libs.TestRunner
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class ReverseTextServletTest extends ScalatraGatlingTest {
  override def testScenario(): ScenarioBuilder = {
    val data = Array(
      Map("t" -> "abbbba", "r" -> "abbbba"),
      Map("t" -> "87654321", "r" -> "12345678"),
      Map("t" -> "jjksjksjdsjk", "r" -> "kjsdjskjskjj"),
     Map("t" -> "fa fa fa fa fa", "r" -> "af af af af af")
    ).random

    scenario("ReverseTextServlet Test")
      .feed(data)
      .repeat(3) {
      exec(http("reverse")
        .get("/reverse?text=${t}").check(status is 200)
        .check(bodyString is "${r}")
      )
    }.exec(
      http("reverse no params")
        .get("/reverse").check(status is 400)
        .check(bodyString is "text query parameter required, example reverse?text=red star")
    )
  }
}

object Runner extends App {
  TestRunner.forSimulationClass(classOf[ReverseTextServletTest].getName)
    .run

}

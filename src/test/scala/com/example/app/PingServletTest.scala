package com.example.app

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.{bodyString, http, status}

class PingServletTest extends ScalatraGatlingTest {
  override def testScenario(): ScenarioBuilder = scenario("PingServlet Test")
    .exec(http("index")
      .get("/").check(status is 200)
      .check(bodyString is "Ping Servlet Home")
    ).exec(http("index")
    .get("/voodoo").check(status is 404)
    .check(bodyString is "Not Found")
  ).exec(http("ping")
      .get("/ping").check(status is 200)
      .check(bodyString is "pong"))

}

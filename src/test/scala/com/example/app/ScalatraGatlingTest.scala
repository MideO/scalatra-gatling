package com.example.app

import com.example.app.libs.LocalTestSimulation
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.InjectionStep
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocol

trait ScalatraGatlingTest
  extends LocalTestSimulation
    with ScalatraTestApplication {

  override val protocol: HttpProtocol = http
    .baseURL(url)
    .disableWarmUp
    .build

  override def injection: InjectionStep = atOnceUsers(1)

  setUp(testScenario().inject(injection)).protocols(protocol)
}

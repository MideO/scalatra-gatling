package com.example.app

import java.util.concurrent.TimeUnit

import com.example.app.libs.{AsciiArt, LocalTestSimulation}
import io.gatling.core.Predef._
import io.gatling.core.controller.inject.InjectionStep
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocol

trait ScalatraGatlingTest
  extends LocalTestSimulation
    with ScalatraTestApplication {
  before {
    AsciiArt.draw("Scalatra-Gatling", 30, 200)
    TimeUnit.SECONDS.sleep(2)
  }
  override val protocol: HttpProtocol = http
    .baseURL(url)
    .disableWarmUp
    .build

  override def injection: InjectionStep = atOnceUsers(1)

  setUp(testScenario().inject(injection)).protocols(protocol)
}

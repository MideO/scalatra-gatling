package com.example.app

import akka.event.slf4j.Logger
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, urlPathMatching}
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.matching.UrlPattern
import io.gatling.app.Gatling
import io.gatling.core.Predef._
import io.gatling.core.config.GatlingPropertiesBuilder
import io.gatling.core.controller.inject.InjectionStep
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocol

package object libs {

  trait TestSimulation extends Simulation{
    val protocol:HttpProtocol
    def injection: InjectionStep
    def testScenario(): ScenarioBuilder
  }

  trait TestApplication {
    private val logger = Logger(this.getClass.getName)

    def url:String
    def doStart():Unit
    def doStop(): Unit


    def start():Unit = {
      logger.info("Starting test application")
      doStart()
      logger.info("Started test application")
    }

    def stop():Unit = {
      logger.info("Stopping test application")
      doStop()
      logger.info("Stopped test application")
    }


  }

  object TestStubServer {
    val port = 8081

    private lazy val wireMockServer: WireMockServer = {
      val options: WireMockConfiguration = new WireMockConfiguration()
        .port(port)
        .disableRequestJournal()
      new WireMockServer(options)
    }

    def start() {
      wireMockServer.start()
    }

    def stop() {
      wireMockServer.stop()
    }

    def stub( httpMethod: UrlPattern => MappingBuilder, url: String, responsePayload: String = "{}", statusCode: Int = 200): this.type = {
      if(!wireMockServer.isRunning) wireMockServer.start()
      wireMockServer.stubFor(httpMethod(urlPathMatching(url))
        .willReturn(aResponse()
          .withStatus(statusCode)
          .withBody(responsePayload)
        )
      )
      this
    }

  }

  object TestRunner {
    lazy val props = new GatlingPropertiesBuilder

    def forSimulationClass(s:String): this.type = {
      props.simulationClass(s)
      this
    }

    def saveResultDirectoryTo(s:String): this.type ={
      props.resultsDirectory("build/reports/performanceTest")
      this
    }

    def fromSourceDirectory(s:String): this.type = {
      props.sourcesDirectory("src/test")
      this
    }

    def run: Int = {
      Gatling.fromMap(props.build)
    }
  }
}

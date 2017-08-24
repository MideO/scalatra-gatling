package com.example.app

import akka.actor.ActorSystem
import org.scalatra.{FutureSupport, ScalatraServlet}

import scala.concurrent.{ExecutionContext, Future}

trait ScalatraGatlingStack extends ScalatraServlet with FutureSupport {
  implicit val system = ActorSystem("MySystem")
  override protected implicit def executor: ExecutionContext = system.dispatcher


  get("/") {
    Future {
      "Ping Servlet Home"
    }
  }

  notFound {
    Future {
      halt(404,  "Not Found")
    }
  }
}

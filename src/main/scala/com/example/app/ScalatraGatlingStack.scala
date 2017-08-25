package com.example.app

import akka.actor.ActorSystem
import org.scalatra.{FutureSupport, NotFound, Ok, ScalatraServlet}

import scala.concurrent.{ExecutionContext, Future}

trait ScalatraGatlingStack extends ScalatraServlet with FutureSupport {
  implicit val system = ActorSystem("MySystem")
  override protected implicit def executor: ExecutionContext = system.dispatcher


  get("/") {
    Future {
      Ok("Ping Servlet Home")
    }
  }

  notFound {
    Future {
      NotFound("Not Found")
    }
  }
}

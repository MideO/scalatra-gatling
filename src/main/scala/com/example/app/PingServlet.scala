package com.example.app

import org.scalatra.Ok

import scala.concurrent.Future

class PingServlet extends ScalatraGatlingStack {
  get("/ping") {
    Future {
      Ok("pong")
    }
  }
}

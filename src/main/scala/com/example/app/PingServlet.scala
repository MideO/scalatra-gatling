package com.example.app

import scala.concurrent.Future

class PingServlet extends ScalatraGatlingStack {
  get("/ping") {
    Future {
      "pong"
    }
  }
}

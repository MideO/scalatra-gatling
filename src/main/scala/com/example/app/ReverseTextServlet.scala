package com.example.app

import org.scalatra.{BadRequest, Ok}


class ReverseTextServlet extends ScalatraGatlingStack {
  get("/") {
    params.get("text") match {
      case Some(s:Any) => Ok(s.asInstanceOf[String].reverse)
      case None => BadRequest("text query parameter required, example reverse?text=red star")
    }
  }
}

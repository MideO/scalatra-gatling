package com.example.app

import java.util.concurrent.TimeUnit

import com.example.app.libs.TestApplication
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

trait ScalatraTestApplication extends TestApplication {
  lazy private val server = new Server(8080)
  lazy private val context = new WebAppContext()

  override def url: String = "http://localhost:8080"

  override def doStart(): Unit = {
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")
    server.setHandler(context)
    server.start()
    while(!server.isRunning) TimeUnit.SECONDS.sleep(1)
  }

  override def doStop(): Unit = {
    if(server.isStarted) server.stop()
  }
}


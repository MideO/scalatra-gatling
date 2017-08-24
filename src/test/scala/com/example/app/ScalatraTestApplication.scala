package com.example.app

import java.util.concurrent.TimeUnit

import akka.event.slf4j.Logger
import com.example.app.libs.TestApplication
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

trait ScalatraTestApplication extends TestApplication {
  lazy private val server = new Server(8080)
  lazy private val context = new WebAppContext()
  val logger = Logger(this.getClass.getName)

  override def start(): Unit = {
    logger.info("Starting test application")
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")

    server.setHandler(context)

    server.start()
    while(!server.isRunning) TimeUnit.SECONDS.sleep(1)
    logger.info("Started test application")
  }

  override def stop(): Unit = {
    logger.info("Stopping test application")
    if(server.isStarted) server.stop()
    logger.info("Stopped test application")
  }

  override def url: String = "http://localhost:8080"
}


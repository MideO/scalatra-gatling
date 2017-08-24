import org.scalatra.sbt._
import io.gatling.sbt.GatlingPlugin

val ScalatraVersion = "2.5.1"

ScalatraPlugin.scalatraSettings

organization := "com.example"

name := "scalatra-gatling"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "com.typesafe.akka" % "akka-actor_2.11" % "2.5.4",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "container;compile",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.2" % "test",
  "io.gatling" % "gatling-test-framework" % "2.2.2" % "test",
  "com.github.tomakehurst" % "wiremock" % "2.7.1" % "test"
)

lazy val `scalatra-gatling` = project.in(file("."))
  .enablePlugins(JettyPlugin)
  .enablePlugins(GatlingPlugin)

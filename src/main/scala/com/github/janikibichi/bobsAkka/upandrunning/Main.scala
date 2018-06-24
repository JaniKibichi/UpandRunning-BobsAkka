package com.github.janikibichi.bobsAkka.upandrunning

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.Future

object Main extends App with RequestTimeout{
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val actorSystem = ActorSystem() //ActorMaterializer requires implicit ActorSystem
  implicit val ec = actorSystem.dispatcher //bindingFuture.map requires implicit ExecutionContext

  val api = new RestApi(actorSystem, requestTimeout(config)).routes //provide a Route

  implicit val materializer = ActorMaterializer() //BindAndHandle requires implicit materializer
  val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(api, host, port) //Start the Server

  val log = Logging(actorSystem.eventStream, "upandrunning-tickets")

  bindingFuture.map{
    serverBinding => log.info(s"RESTApi bound to${serverBinding.localAddress}")
  }.onFailure{
    case ex: Exception =>
      log.error(ex, "Failed to bind to {}:{}!", host, port)
      actorSystem.terminate()
  }
}

trait RequestTimeout {
  import scala.concurrent.duration._
  def requestTimeout(config: Config): Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}
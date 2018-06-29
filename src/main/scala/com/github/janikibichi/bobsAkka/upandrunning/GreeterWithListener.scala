package com.github.janikibichi.bobsAkka.upandrunning

import akka.actor
import akka.actor.{Actor, ActorLogging, ActorRef, Props}

case class Greeting(message: String)

object GreetWithListener{
  def props(listener: Option[ActorRef] = None) = actor.Props(new GreetWithListener(listener))
}

class GreetWithListener(listener: Option[ActorRef]) extends Actor with ActorLogging{
  def receive = {
    case Greeting(who) => {
      val message = "Hello " + who + "!"
      log.info(message)
      listener.foreach(_ ! message)
    }
  }
}
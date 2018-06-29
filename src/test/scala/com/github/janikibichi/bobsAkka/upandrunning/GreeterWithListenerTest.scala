package com.github.janikibichi.bobsAkka.upandrunning

import akka.actor.{ActorSystem, UnhandledMessage}
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}

class GreeterWithListenerTest extends TestKit(ActorSystem("testsystem"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {
  "The Greeter" must{

    "say Hello World! when a Greeting(\"World\") is sent to it" in {
      val props = GreetWithListener.props(Some(testActor))
      val greeter = system.actorOf(props, "greetwithlistener-1")
      greeter ! Greeting("World")
      expectMsg("Hello World!")
    }

    "say something else and see what happens" in {
      val props = GreetWithListener.props(Some(testActor))
      val greeter = system.actorOf(props, "greetwithlistener-2")

      system.eventStream.subscribe(testActor, classOf[UnhandledMessage])
      greeter ! "World"
      expectMsg(UnhandledMessage("World", system.deadLetters, greeter))
    }
  }
}

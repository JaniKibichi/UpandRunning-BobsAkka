package com.github.janikibichi.bobsAkka.upandrunning

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestActorRef, TestKit}
import org.scalatest.{MustMatchers, WordSpecLike}

class SilentTestActor extends TestKit(ActorSystem("silenttestactor"))
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll{
  "A Silent Actor" must{
    "change state when it receives a message, single threaded" in{
      //Write the test, first fail
      //fail("not implemented yet")
      import SilentActor._

      val silentActor = TestActorRef[SilentActor]
      silentActor ! SilentMessage("whisper")
      silentActor.underlyingActor.state must (contain ("whisper"))
    }

    "change state when it receives a message, multi-threaded" in{
      //Write the test, first fail
      //fail("not implemented yet")
      import SilentActor._

      val silentActor = system.actorOf(Props[SilentActor], "s3")
      silentActor ! SilentMessage("whisper1")
      silentActor ! SilentMessage("whisper2")
      silentActor ! GetState(testActor)
      expectMsg(Vector("whisper1","whisper2"))
    }
  }
}
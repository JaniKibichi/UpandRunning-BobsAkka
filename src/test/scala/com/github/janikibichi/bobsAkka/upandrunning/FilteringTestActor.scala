package com.github.janikibichi.bobsAkka.upandrunning

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}


class FilteringTestActor extends TestKit(ActorSystem("filteringtestactor"))
with WordSpecLike
with MustMatchers
with StopSystemAfterAll{

  "filter out particular messages" in {
    import FilteringActor._

    val props = FilteringActor.props(testActor, 5)
    val filter = system.actorOf(props, "filter-1")
    filter ! Event(1)
    filter ! Event(2)
    filter ! Event(1)
    filter ! Event(3)
    filter ! Event(1)
    filter ! Event(4)
    filter ! Event(5)
    filter ! Event(5)
    filter ! Event(6)

    val eventIds = receiveWhile() {
      case Event(id) if id <= 5 => id
    }

    eventIds must be (List(1,2,3,4,5))
    expectMsg(Event(6))
  }

  "filter out particular messages using expectNoMsg" in{
    import FilteringActor._

    val props = FilteringActor.props(testActor, 5)
    val filter = system.actorOf(props, "filter-2")
    filter ! Event(1)
    filter ! Event(2)
    expectMsg(Event(1))
    expectMsg(Event(2))
    expectNoMsg
    filter ! Event(4)
    filter ! Event(5)
    filter ! Event(5)
    expectMsg(Event(4))
    expectMsg(Event(5))
    expectNoMsg
  }
}

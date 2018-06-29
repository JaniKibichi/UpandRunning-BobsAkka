package com.github.janikibichi.bobsAkka.upandrunning

import akka.actor.{Actor, ActorRef, Props}


object SendingActor{
  def props(receiver: ActorRef) = Props(new SendingActor(receiver))
  case class Event(id: Long)
  case class SortEvents(unsorted: Vector[Event])
  case class SortedEvents(sorted: Vector[Event])
}

class SendingActor(receiver: ActorRef) extends Actor{
  import SendingActor._

  def receive = {
    case SortEvents(unsorted) =>
      receiver ! SortedEvents(unsorted.sortBy(_.id))
  }
}

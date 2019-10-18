package com.akka.akkademo.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class AkkaTest {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef ref = system.actorOf(Props.create(AkkaActor.class),"akkaActor");
        ref.tell("main",ActorRef.noSender());


        Timeout timeout = new Timeout(Duration.create(2, TimeUnit.SECONDS));
        Future<Object> future = Patterns.ask(ref, "吃了吗?", timeout);

    }


    static class AkkaActor extends AbstractActor{
        @Override
        public Receive createReceive() {
            return receiveBuilder().match(String.class,(msg)->{
                ActorRef ref = getContext().actorOf(Props.create(SimpleActor.class),"simpleActor");
                ref.tell("Hi,I'm AkkaActor " + msg,ref);
            }).build();
        }
    }

    static class SimpleActor extends AbstractActor{
        @Override
        public Receive createReceive() {
            return receiveBuilder().match(String.class,(msg)->{
                System.out.println("SimpleActor msg = " + msg);
            }).build();
        }
    }

}

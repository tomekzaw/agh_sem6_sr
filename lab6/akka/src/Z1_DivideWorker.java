import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.concurrent.atomic.AtomicInteger;

public class Z1_DivideWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public Receive createReceive() {
            return receiveBuilder()
                    .match(String.class, s -> {
                        int count = counter.incrementAndGet();
                        String result = Divide(s);
                        getSender().tell("result: " + result + " (operation count: " + count + ")", getSelf());
                    })
                    .matchAny(o -> log.info("received unknown message"))
                    .build();
    }
    
    private String Divide(String s){
        String[] split = s.split(" ");
        int a = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);
        return (a/b) + "";
    }
}

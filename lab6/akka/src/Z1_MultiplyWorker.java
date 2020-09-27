import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.concurrent.atomic.AtomicInteger;

public class Z1_MultiplyWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final AtomicInteger counter = new AtomicInteger();
    
    @Override
    public AbstractActor.Receive createReceive() {
            return receiveBuilder()
                    .match(String.class, s -> {
                        String result = Multiply(s);
                        getSender().tell("result: " + result +
                                " (operation count: " + counter.incrementAndGet() + ")", getSelf());
                    })
                    .matchAny(o -> log.info("received unknown message"))
                    .build();
    }
    
    private String Multiply(String s){
        String[] split = s.split(" ");
        int a = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);
        return (a*b) + "";
    }
}

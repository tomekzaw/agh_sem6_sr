import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Z2_LocalActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                // TODO: forward msg from AppLocal to remote actor
                // TODO: print response
                // TODO: need to distinguish between remote response and msg from AppLocal to avoid infinite msg loop
                .match(String.class, s -> {
                    if (getSender().path().name().equals("remote")) {
                        // print response
                        System.out.println("Received response: " + s);
                    } else {
                        // send request
                        String path = "akka://remote_system@127.0.0.1:2552/user/remote";
                        getContext().actorSelection(path).tell(s, getSelf());
                        System.out.println("Sent request: " + s);
                    }
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}

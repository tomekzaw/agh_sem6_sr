import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.ClientRequest;

public class Server extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
            return receiveBuilder()
                    .match(ClientRequest.class, request -> {
                        final ActorRef worker = getContext().actorOf(Props.create(Worker.class));
                        worker.forward(request, getContext());
                    })
                    .matchAny(o -> log.info("received unknown message"))
                    .build();
    }
}

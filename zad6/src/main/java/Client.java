import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.ClientRequest;
import data.ServerResponse;

public class Client extends AbstractActor {
    private final ActorRef server;
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Client(ActorRef server) {
        this.server = server;
    }

    @Override
    public Receive createReceive() {
            return receiveBuilder()
                    .match(ClientRequest.class, request -> {
                        server.tell(request, getSelf());
                    })
                    .match(ServerResponse.class, response -> {
                        log.info(response.toString());
                    })
                    .matchAny(o -> log.info("received unknown message"))
                    .build();
    }
}

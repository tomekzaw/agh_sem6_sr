import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.*;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Worker extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private ActorRef client;
    private String productName;
    private final List<Integer> prices = new LinkedList<>();
    private Integer queries;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ClientRequest.class, request -> {
                    client = getSender();
                    productName = request.getProductName();

                    final ActorRef shop1 = getContext().actorOf(Props.create(Shop.class));
                    final ActorRef shop2 = getContext().actorOf(Props.create(Shop.class));
                    final ActorRef statistician = getContext().actorOf(Props.create(Historian.class));

                    shop1.tell(request, getSelf());
                    shop2.tell(request, getSelf());
                    statistician.tell(request, getSelf());

                    getContext().system().scheduler().scheduleOnce(
                            Duration.ofMillis(300), getSelf(), TimeoutPill.getInstance(),
                            getContext().system().dispatcher(), ActorRef.noSender());
                })
                .match(ShopResponse.class, r -> {
                    int price = r.getPrice();
                    synchronized (prices) {
                        prices.add(price);
                    }
                    log.info(r.toString());
                    if (prices.size() == 2 && queries != null) {
                        int minPrice = Collections.min(prices);
                        ServerResponse response = new ServerResponse(productName, minPrice, queries);
                        client.tell(response, null);
                        getContext().stop(getSelf());
                    }
                })
                .match(HistorianResponse.class, r -> {
                    queries = r.getPopularity();
                    log.info(r.toString());
                    if (prices.size() == 2) {
                        int minPrice = Collections.min(prices);
                        ServerResponse response = new ServerResponse(productName, minPrice, queries);
                        client.tell(response, null);
                        getContext().stop(getSelf());
                    }
                })
                .match(TimeoutPill.class, r -> {
                    Integer minPrice = null;
                    if (!prices.isEmpty()) {
                        minPrice = Collections.min(prices);
                    }
                    ServerResponse response = new ServerResponse(productName, minPrice, queries);
                    client.tell(response, null);
                    getContext().stop(getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}

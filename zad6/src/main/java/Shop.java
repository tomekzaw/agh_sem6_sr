import akka.actor.AbstractActor;
import akka.actor.PoisonPill;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.ClientRequest;
import data.ShopResponse;

import java.util.concurrent.ThreadLocalRandom;

public class Shop extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
            return receiveBuilder()
                    .match(ClientRequest.class, request -> {
                        // String productName = request.getProductName();

                        sleep();
                        final int price = getPrice();

                        final ShopResponse response = new ShopResponse(price);
                        getSender().tell(response, null);

                        self().tell(PoisonPill.getInstance(), null);
                    })
                    .matchAny(o -> log.info("received unknown message"))
                    .build();
    }

    private void sleep() throws InterruptedException {
        long millis = ThreadLocalRandom.current().nextLong(100, 500+1);
        log.info("Waiting for " + Long.toString(millis) + " ms");
        Thread.sleep(millis);
    }

    private int getPrice() {
        return ThreadLocalRandom.current().nextInt(1, 10+1);
    }
}

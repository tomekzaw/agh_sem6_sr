package alerts;

import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionsHandler {
        private Map<StreamObserver<Notification>, Subscription> subscribers = new HashMap<>();

        public void addSubscriber(StreamObserver<Notification> responseObserver, Subscription subscription) {
        this.subscribers.put(responseObserver, subscription);
    }

    public void removeSubscriber(StreamObserver<Notification> responseObserver) {
        this.subscribers.remove(responseObserver);
    }

    public void sendNotification(Notification notification) {
        this.subscribers.forEach((StreamObserver<Notification> responseObserver, Subscription subscription) -> {
            for (String hashtag : notification.getHashtagsList()) {
                if (subscription.getHashtagsList().contains(hashtag)) {
                    responseObserver.onNext(notification);
                    break;
                }
            }
        });
        System.out.println("Message published");
    }

    public void shutdown() {
        this.subscribers.keySet().forEach(StreamObserver::onCompleted);
    }
}

package alerts;

import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

public class AlertsImpl extends AlertsGrpc.AlertsImplBase {
    private SubscriptionsHandler subscriptionsHandler;

    public AlertsImpl(SubscriptionsHandler subscriptionsHandler) {
        this.subscriptionsHandler = subscriptionsHandler;
    }

    @Override
    public void subscribe(Subscription request, StreamObserver<Notification> responseObserver)
    {
        System.out.println("Client connected");

        ServerCallStreamObserver serverCallresponseObserver = (ServerCallStreamObserver) responseObserver;

        serverCallresponseObserver.setOnCancelHandler(() -> {
            System.out.println("Client disconnected");
            this.subscriptionsHandler.removeSubscriber(responseObserver);
        });

        this.subscriptionsHandler.addSubscriber(responseObserver, request);
    }
}

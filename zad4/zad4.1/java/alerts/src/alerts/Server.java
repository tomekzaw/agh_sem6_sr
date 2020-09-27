package alerts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import io.grpc.ServerBuilder;

public class Server
{
	private static final Logger logger = Logger.getLogger(Server.class.getName());

	private int port = 50051;
	private io.grpc.Server server;

	private SubscriptionsHandler subscriptionsHandler = new SubscriptionsHandler();

	private void start() throws IOException 
	{
        server = ServerBuilder.forPort(port)
            .addService(new AlertsImpl(this.subscriptionsHandler))
            .build()
            .start();

		logger.info("Server started, listening on " + port);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            this.stop();
            System.err.println("*** server shut down");
		}));
	}

	private void stop() {
		if (server != null) {
			server.shutdown();
		}
	}

    public static void main(String[] args) throws IOException {
        final Server server = new Server();
        server.start();
        server.handleMessages();
    }

    public void handleMessages() {
        logger.info("Waiting for messages...");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	    while (true) {
            // System.out.print("> ");
            // System.out.flush();

            String line;
            try {
                line = in.readLine();
            } catch (IOException e) {
                break;
            }

            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            String[] split = line.split(" #");

            String message = split[0];
            List<String> hashtags = Arrays.asList(split);
            hashtags = hashtags.subList(1, hashtags.size());

            long numberOfExclamationMarks = line.chars().filter(ch -> ch == '!').count();
            Importance importance;
            if (numberOfExclamationMarks == 0) {
                importance = Importance.UNKNOWN;
            } else if (numberOfExclamationMarks == 1) {
                importance = Importance.LOW;
            } else if (numberOfExclamationMarks == 2) {
                importance = Importance.MEDIUM;
            } else {
                importance = Importance.HIGH;
            }

            Notification notification = Notification.newBuilder()
                .setMessage(message)
                .addAllHashtags(hashtags)
                .setImportance(importance)
                .build();

            this.subscriptionsHandler.sendNotification(notification);
        }

	    this.subscriptionsHandler.shutdown();
    }
}

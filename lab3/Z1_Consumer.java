import com.rabbitmq.client.*;

import java.io.IOException;

public class Z1_Consumer {

    public static void main(String[] argv) throws Exception {

        // info
        System.out.println("Z1 CONSUMER");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        // QoS
        channel.basicQos(1);

        // queue
        String QUEUE_NAME = "queue1";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // consumer (handle msg)
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
                int timeToSleep = Integer.parseInt(message);
                try {
                    Thread.sleep(timeToSleep * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // System.out.println("Processed: " + message);

                // Po przetworzeniu wiadomości
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // start listening
        System.out.println("Waiting for messages...");

        // Po otrzymaniu wiadomości
        // channel.basicConsume(QUEUE_NAME, true, consumer);
        // Po przetworzeniu wiadomości
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // close
//        channel.close();
//        connection.close();
    }
}

#!/usr/bin/env python
import pika
import sys
from itertools import count
from threading import Thread
from termcolor import colored


if __name__ == '__main__':
    agency_id = sys.argv[1] if len(sys.argv) == 2 else input('Enter agency ID: ')

    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()

    channel.exchange_declare(exchange='order', exchange_type='topic')
    channel.exchange_declare(exchange='info', exchange_type='topic')

    result = channel.queue_declare(queue='', exclusive=True)
    info_queue = result.method.queue

    for routing_key in f'agency.{agency_id}', 'agencies', 'all':
        channel.queue_bind(exchange='info', queue=info_queue, routing_key=routing_key)

    def on_info(ch, method, properties, body):
        if properties.correlation_id is not None:
            order_id = properties.correlation_id
            print('\r' + colored(f'Order {order_id} completed', 'green'))
        else:
            print('\r' + colored(f'Received: {body.decode()}', 'yellow'))

    channel.basic_consume(queue=info_queue, on_message_callback=on_info, auto_ack=True)

    def consumer_routine():
        try:
            channel.start_consuming()
        except KeyboardInterrupt:
            pass

    Thread(target=consumer_routine, daemon=True).start()

    try:
        for order_num in count(1):
            service_id = input()
            routing_key = service_id
            order_id = agency_id + '-' + str(order_num)

            print(colored(f'Agency ID: {agency_id}\nOrder ID: {order_id}\nService ID: {service_id}', 'cyan'))

            channel.basic_publish(
                exchange='order',
                routing_key=routing_key,
                properties=pika.BasicProperties(
                    reply_to=f'agency.{agency_id}',
                    correlation_id=order_id,
                    delivery_mode=2),
                body=f'Agency {agency_id} places order {order_id} for service {service_id}')
    except KeyboardInterrupt:
        pass

    # no connection.close() here because there is channel.start_consuming() in another thread

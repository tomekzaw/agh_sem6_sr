#!/usr/bin/env python
import sys
import pika
from tqdm import tqdm
from time import sleep
from termcolor import colored
from datetime import datetime
from threading import Thread


try:
    from signal import pause
except ImportError:
    def pause():
        try:
            while True:
                sleep(1)
        except KeyboardInterrupt:
            pass


def on_order(ch, method, properties, body):
    service_id = method.routing_key
    order_id = properties.correlation_id

    print('\r' + colored(f'[{datetime.now():%H:%M:%S.%f}] Received order {order_id} for service {service_id}', 'blue'))

    for i in tqdm(range(100), desc=f'{service_id:10s} {order_id:8s}', leave=False, mininterval=None, bar_format='{desc} {percentage:3.0f}% {bar}'):
        sleep(0.04)

    print('\r' + colored(f'[{datetime.now():%H:%M:%S.%f}] Order {order_id} for service {service_id} completed', 'green'))

    ch.basic_publish(
        exchange='info',
        routing_key=properties.reply_to,
        properties=pika.BasicProperties(correlation_id=properties.correlation_id),
        body=f'Order {order_id} completed')

    ch.basic_ack(delivery_tag=method.delivery_tag)


def receive_orders_routine(service_id):
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()

    channel.exchange_declare(exchange='order', exchange_type='topic')
    channel.exchange_declare(exchange='info', exchange_type='topic')

    channel.queue_declare(service_id, durable=True)
    channel.queue_bind(exchange='order', queue=service_id, routing_key=service_id)

    channel.basic_qos(prefetch_count=1)
    channel.basic_consume(queue=service_id, on_message_callback=on_order)

    try:
        channel.start_consuming()
    except KeyboardInterrupt:
        pass

    # no connection.close() here


def receive_info_routine():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()

    channel.exchange_declare(exchange='info', exchange_type='topic')

    result = channel.queue_declare(queue='', exclusive=True)
    info_queue = result.method.queue

    for routing_key in 'carriers', 'all':
        channel.queue_bind(exchange='info', queue=info_queue, routing_key=routing_key)

    def on_info(ch, method, properties, body):
        print('\r' + colored('Received: ' + body.decode(), 'yellow'))

    channel.basic_consume(queue=info_queue, on_message_callback=on_info, auto_ack=True)

    try:
        channel.start_consuming()
    except KeyboardInterrupt:
        pass

    # no connection.close() here


if __name__ == '__main__':
    service_ids = sys.argv[1:] if len(sys.argv) >= 2 else input('Enter service ids: ').split()

    for service_id in service_ids:
        Thread(target=receive_orders_routine, args=(service_id,), daemon=True).start()

    Thread(target=receive_info_routine, daemon=True).start()

    pause()
    print()

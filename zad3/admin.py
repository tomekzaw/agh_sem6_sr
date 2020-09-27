#!/usr/bin/env python
import pika
from threading import Thread
from datetime import datetime
from termcolor import colored


if __name__ == '__main__':
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()

    channel.exchange_declare(exchange='order', exchange_type='topic')
    channel.exchange_declare(exchange='info', exchange_type='topic')

    result = channel.queue_declare(queue='', exclusive=True)
    queue_name = result.method.queue

    channel.queue_bind(exchange='order', queue=queue_name, routing_key='#')
    channel.queue_bind(exchange='info', queue=queue_name, routing_key='#')

    def on_info(ch, method, properties, body):
        print('\r' + colored(f'[{datetime.now():%H:%M:%S.%f}] {body.decode()}', attrs=['dark']))

    channel.basic_consume(queue=queue_name, on_message_callback=on_info, auto_ack=True)

    def consumer_routine():
        try:
            channel.start_consuming()
        except KeyboardInterrupt:
            pass

    Thread(target=consumer_routine, daemon=True).start()

    try:
        while True:
            try:
                cmd, msg = input().split(' ', 1)
                assert cmd in ('all', 'agencies', 'carriers')
            except (ValueError, AssertionError):
                print('Invalid command')
                continue

            channel.basic_publish(
                exchange='info',
                routing_key=cmd,
                body=msg.encode())
    except KeyboardInterrupt:
        pass

    # no connection.close() here because there is channel.start_consuming() in another thread

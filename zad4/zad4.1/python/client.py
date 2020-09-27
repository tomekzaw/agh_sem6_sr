import sys
from time import sleep
from datetime import datetime
from termcolor import colored

import grpc
from generated import alerts_pb2
from generated import alerts_pb2_grpc


def display_notification(notification):
    importance_string = {
        alerts_pb2.Importance.LOW: colored('LOW', 'green'),
        alerts_pb2.Importance.MEDIUM: colored('MEDIUM', 'yellow'),
        alerts_pb2.Importance.HIGH: colored('HIGH', 'red'),
    }.get(notification.importance, None)
    if importance_string is not None:
        print(importance_string)

    print(colored('"' + notification.message + '"', attrs=['bold']))

    for hashtag in notification.hashtags:
        print('#' + hashtag)

    print()


if __name__ == '__main__':
    hashtags = list(map(lambda arg: arg.replace('#', ''), sys.argv[1:]))
    if not hashtags:
        raise SystemExit('No hashtags provided')

    print('\n' + colored('Observing: ' + ' '.join(map(lambda hashtag: '#' + hashtag, hashtags)), attrs=['bold']) + '\n')

    subscription = alerts_pb2.Subscription(hashtags=hashtags)

    options = [
        ('grpc.keepalive_time_ms', 10000),
        ('grpc.keepalive_timeout_ms', 5000),
        ('grpc.keepalive_permit_without_calls', 1),
        ('grpc.http2_max_pings_without_data', 0),
        ('grpc.http2_min_sent_ping_interval_without_data_ms', 10000)
    ]

    try:
        while True:
            with grpc.insecure_channel('10.0.0.5:50051', options) as channel:
                stub = alerts_pb2_grpc.AlertsStub(channel)

                try:
                    iterator = stub.subscribe(subscription)
                    for notification in iterator:
                        display_notification(notification)

                    print(colored('Server disconnected gracefully', 'green'))

                except grpc.RpcError as e:
                    status_code = e.code()

                    if e.details() == 'Stream removed':
                        print(colored('Server disconnected, trying to reconnect...', 'yellow'))

                    elif status_code == grpc.StatusCode.UNAVAILABLE:
                        print(colored('Server unavailable', 'red'))
                        sleep(1)

                    else:
                        print(e)
                        raise SystemExit

    except KeyboardInterrupt:
        pass

#!/usr/bin/env python
import utils
import socket
import struct
import config
from threading import Thread
from termcolor import colored

clients = {}


def accept_tcp_connections(serversocket):
    while True:
        tcpclientsocket, clientaddress = serversocket.accept()
        if len(clients) < config.max_clients:
            clientthread = Thread(target=handle_client, args=(tcpclientsocket, clientaddress))
            clientthread.start()
        else:
            tcpclientsocket.close()
            ip, port = clientaddress
            print(colored(f'Connection from {ip}:{port} refused, too many clients', 'red'))


def handle_client(tcpclientsocket, clientaddress):
    ip, port = clientaddress      
    print(colored(f'New connection from {ip}:{port}', 'green'))
    clients[clientaddress] = tcpclientsocket
    
    while True:
        data = utils.tcp_recvmsg(tcpclientsocket)
        if data == b'':
            del clients[clientaddress]
            tcpclientsocket.close()
            print(colored(f'Client {ip}:{port} has disconnected', 'red'))
            return

        for tcpotherclientsocket in clients.values():
            if tcpotherclientsocket != tcpclientsocket:
                tcpotherclientsocket.sendall(data)

        print(colored(f'[TCP] {data}', attrs=['dark']))


def receive_udp(udpsock):
    while True:
        try:
            data, udpclientaddress = udpsock.recvfrom(1024)
        except OSError:
            return
        
        for udpotherclientaddress in clients.keys():
            if udpotherclientaddress != udpclientaddress:
                udpsock.sendto(data, udpotherclientaddress)

        print(colored(f'[UDP] {data}', attrs=['dark']))


if __name__ == '__main__':
    print('Python Chat Server')
    print('Press Ctrl+C to exit')

    tcpserversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM, socket.IPPROTO_TCP)
    try:
        tcpserversocket.bind(config.server_address)
    except OSError:
        tcpserversocket.close()
        print(colored('Address already taken', 'red'))
        raise SystemExit
    tcpserversocket.listen(5)

    udpsock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
    try:
        udpsock.bind(config.server_address)
    except OSError:
        tcpserversocket.close()
        print(colored('Address already taken', 'red'))
        raise SystemExit

    Thread(target=accept_tcp_connections, args=(tcpserversocket,), daemon=True).start()
    Thread(target=receive_udp, args=(udpsock,), daemon=True).start()

    utils.pause()

    for clientaddress, tcpclientsocket in clients.items():
        tcpclientsocket.shutdown(socket.SHUT_WR)
        ip, port = clientaddress
        print(colored(f'Client {ip}:{port} disconnected', 'red'))
    tcpserversocket.close()

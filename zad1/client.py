#!/usr/bin/env python
import os
import json
import utils
import socket
import select
import struct
import config
from time import sleep
from threading import Thread
from termcolor import colored


def receive(tcp_sock, udp_sock, mcast_sock):
    while True:
        ready_socks, _, _ = select.select([tcp_sock, udp_sock, mcast_sock], [], []) 

        if tcp_sock in ready_socks:
            try:
                data = utils.tcp_recvmsg(tcp_sock)
            except (ConnectionResetError, ConnectionAbortedError):
                print(colored('Too many clients', 'red'))
                os._exit(1)
            except Exception as exc:
                os._exit(1)

            if data == b'':
                print(colored('Server disconnected', 'red'))
                os._exit(1)

            author, content = utils.readmsg(data)
            print(colored(f'[TCP] {author}> {content}', 'cyan'))

        if udp_sock in ready_socks:
            data, _ = udp_sock.recvfrom(1024)
            author, content = utils.readmsg(data)
            print(colored(f'[UDP] {author}> {content}', 'green'))

        if mcast_sock in ready_socks:
            data, _ = mcast_sock.recvfrom(1024)
            author, content = utils.readmsg(data)
            print(colored(f'[Multicast] {author}> {content}', 'yellow'))


if __name__ == '__main__':
    print('Python Chat Client')
    print('Press Ctrl+C to exit')
    
    while True:
        try:
            nickname = input('Choose a nickname: ')
        except KeyboardInterrupt:
            raise SystemExit
        if nickname:
            break

    tcp_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM, socket.IPPROTO_TCP)
    try:
        tcp_sock.connect(config.server_address)
    except ConnectionRefusedError:
        print(colored('Server unavailable', 'red'))
        raise SystemExit
    _, tcp_port = tcp_sock.getsockname()

    udp_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
    udp_sock.bind(('', tcp_port))

    mcast_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
    mcast_sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    mcast_sock.bind(('', config.multicast_port))

    group = socket.inet_aton(config.multicast_group)
    mreq = struct.pack('4sL', group, socket.INADDR_ANY)
    mcast_sock.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)
    
    thread = Thread(target=receive, args=(tcp_sock, udp_sock, mcast_sock), daemon=True)
    thread.start()        

    while True:
        try:
            line = input()
        except KeyboardInterrupt:
            tcp_sock.close()
            udp_sock.close()
            mcast_sock.close()
            raise SystemExit
        if not line:
            continue

        if line.startswith('/'):
            first, *rest = line.split(' ', 1)
            command = first[1:].lower()
            message = ''.join(rest)
        else:
            command, message = None, line

        if command in ('q', 'quit', 'exit'):
            tcp_sock.close()
            udp_sock.close()
            mcast_sock.close()
            raise SystemExit 
        
        data = utils.makemsg(nickname, message)
        if command is None or command in ('t', 'tcp'):
            tcp_sock.sendall(data)
        elif command in ('u', 'udp'):
            udp_sock.sendto(data, config.server_address)
        elif command in ('m', 'mcast', 'multicast'):
            mcast_sock.sendto(data, (config.multicast_group, config.multicast_port))
        else:
            print(colored(f'Invalid command /{command}', 'red'))
        
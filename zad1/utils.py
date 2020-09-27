#!/usr/bin/env python
import json
import struct
import socket
import typing
from time import sleep


def pause():
    try:
        while True:
            sleep(1)
    except KeyboardInterrupt:
        pass


def tcp_recvall(sock: socket.socket, nbytes: int) -> typing.Optional[bytes]:
    buffer = bytearray()
    while len(buffer) < nbytes:
        data = sock.recv(nbytes - len(buffer))
        if data == b'':
            return b''
        buffer.extend(data)
    return bytes(buffer)


def tcp_recvmsg(sock: socket.socket) -> typing.Optional[bytes]:
    raw_msglen = tcp_recvall(sock, 4)
    if raw_msglen == b'':
        return b''
    msglen, = struct.unpack('>I', raw_msglen)
    raw_msg = tcp_recvall(sock, msglen)
    if raw_msg == b'':
        return b''
    return raw_msglen + raw_msg


def makemsg(author: str, content: str) -> bytes:
    data = json.dumps({
        'author': author,
        'content': content,
    }).encode()
    return struct.pack('>I', len(data)) + data


def readmsg(data: bytes) -> typing.Tuple[str, str]:    
    msg = json.loads(data[4:].decode())
    return msg['author'], msg['content']

import socket;

serverPort = 9009
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
serverSocket.bind(('', serverPort))

if __name__ == '__main__':
    print('PYTHON UDP SERVER')

    while True:
        buff, address = serverSocket.recvfrom(1024)
        print("python udp server received msg: " + str(buff, 'cp1250'))
    
        if b'Java' in buff:
            msg = 'Thank you, Java!'  
            serverSocket.sendto(bytes(msg, 'cp1250'), address)
        elif b'Python' in buff:
            msg = 'Thank you, Python!'
            serverSocket.sendto(bytes(msg, 'cp1250'), address)

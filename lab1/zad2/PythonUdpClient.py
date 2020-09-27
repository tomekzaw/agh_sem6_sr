import socket;

serverIP = "127.0.0.1"
serverPort = 9008
msg = "żółta gęś"

if __name__ == '__main__':
    print('PYTHON UDP CLIENT')
    client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    client.sendto(bytes(msg, 'cp1250'), (serverIP, serverPort))

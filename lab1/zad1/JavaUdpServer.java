import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class JavaUdpServer {

    public static void main(String args[])
    {
        System.out.println("JAVA UDP SERVER");
        DatagramSocket socket = null;
        int portNumber = 9008;

        try{
            socket = new DatagramSocket(portNumber);

            // Serwer otrzymuje wiadomość...
            byte[] receiveBuffer = new byte[1024];
            Arrays.fill(receiveBuffer, (byte)0);
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String msg = new String(receivePacket.getData());
            System.out.println("received msg: " + msg.trim());
            
            // Należy pobrać adres nadawcy z otrzymanego datagramu
            InetAddress address = receivePacket.getAddress();
            int sourcePortNumber = receivePacket.getPort();

            // ...i wysyła odpowiedź
            byte[] sendBuffer = "Ping Java Udp from Server".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, sourcePortNumber);
            socket.send(sendPacket);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class JavaUdpServer {

    public static void main(String args[])
    {
        System.out.println("JAVA UDP SERVER");
        DatagramSocket socket = null;
        int portNumber = 9008;

        try{
            socket = new DatagramSocket(portNumber);
            byte[] receiveBuffer = new byte[4];

            Arrays.fill(receiveBuffer, (byte)0);
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            int nb = ByteBuffer.wrap(receiveBuffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
            System.out.println(String.format("received msg: %d" , nb));

            nb += 1;
            InetAddress address = receivePacket.getAddress();
            int sourcePortNumber = receivePacket.getPort();
            byte[] sendBuffer = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(nb).array();
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

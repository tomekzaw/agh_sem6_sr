package SmartHome.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import SmartHome.BulbulatorPrx;
import SmartHome.PTZCameraPrx;
import SmartHome.PanTiltZoom;
import SmartHome.TempSensorPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ConnectionRefusedException;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.ObjectPrx;
import javafx.scene.Camera;

public class Client 
{
	public static void main(String[] args)
	{
		Client client = new Client();
		client.run(args);
	}

	public void run(String[] args)
	{
		int status = 0;
		Communicator communicator = null;

		try {
            communicator = Util.initialize(args);

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("> ");
                System.out.flush();

                String line;
                try {
                    line = in.readLine();
                } catch (IOException e) {
                    break;
                }

                if (line == null) {
                    continue;
                }

                if (line.equalsIgnoreCase("exit")) {
                    break;
                }

                if (line.equalsIgnoreCase("temp1")) {

                    ObjectPrx base = communicator.propertyToProxy("TempSensor1.Proxy");

                    TempSensorPrx obj = TempSensorPrx.checkedCast(base);
                    if (obj == null) {
                        throw new Error("Invalid proxy");
                    }

                    float temperature = obj.getTemperature();
                    System.out.println(String.format("Temperature: %f", temperature));

                } else if (line.equalsIgnoreCase("camera1 getPanTiltZoom")) {

                    ObjectPrx base = communicator.propertyToProxy("Camera1.Proxy");

                    PTZCameraPrx obj = PTZCameraPrx.checkedCast(base);
                    if (obj == null) {
                        throw new Error("Invalid proxy");
                    }

                    PanTiltZoom ptz = obj.getPanTiltZoom();
                    System.out.println(String.format("pan=%d tilt=%d zoom=%d", ptz.getPan(), ptz.getTilt(), ptz.getZoom()));

                } else if (line.equalsIgnoreCase("bul")) {

                    ObjectPrx base = communicator.propertyToProxy("Bulbulator1.Proxy");

                    BulbulatorPrx obj = BulbulatorPrx.checkedCast(base);
                    if (obj == null) {
                        throw new Error("Invalid proxy");
                    }

                    obj.bulbul();

                } else {
                    System.out.println("Invalid command");
                }
            }

        } catch (ConnectionRefusedException e) {
            System.out.println("Connection lost");
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		}

		if (communicator != null) {
			try {
				communicator.destroy();
			} catch (Exception e) {
				e.printStackTrace();
				status = 1;
			}
		}

		System.exit(status);
	}

}
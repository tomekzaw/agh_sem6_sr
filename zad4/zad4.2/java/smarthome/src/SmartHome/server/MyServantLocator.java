package SmartHome.server;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ServantLocator;

import java.util.LinkedList;
import java.util.List;

public class MyServantLocator implements ServantLocator
{
    private final List<String> availableNames = new LinkedList<>(); // for debugging only

    public ServantLocator.LocateResult locate(Current current)
    {
        String name = current.id.name;
        System.out.println("locate(" + name + ")");
        this.availableNames.add(name);

        ObjectAdapter adapter = current.adapter;

        switch (name) {
            case "tempSensor1":
                TempSensorI tempSensor1Servant = new TempSensorI(20, 23);
                adapter.add(tempSensor1Servant, new Identity(name, "tempSensor"));
                return new ServantLocator.LocateResult(tempSensor1Servant, null);

            case "tempSensor2":
                TempSensorI tempSensor2Servant = new TempSensorI(-3, -1);
                adapter.add(tempSensor2Servant, new Identity(name, "tempSensor"));
                return new ServantLocator.LocateResult(tempSensor2Servant, null);

            case "camera1":
                CameraI camera1Servant = new CameraI();
                adapter.add(camera1Servant, new Identity(name, "camera"));
                return new ServantLocator.LocateResult(camera1Servant, null);

            case "camera2":
                PTZCameraI camera2Servant = new PTZCameraI();
                adapter.add(camera2Servant, new Identity(name, "camera"));
                return new ServantLocator.LocateResult(camera2Servant, null);

            case "bulbulator1":
                BulbulatorI bulbulator1Servant = new BulbulatorI();
                adapter.add(bulbulator1Servant, new Identity(name, "bulbulator"));
                return new ServantLocator.LocateResult(bulbulator1Servant, null);

            default:
                throw new RuntimeException("Invalid name");
        }
    }

    public void finished(Current current, com.zeroc.Ice.Object servant, java.lang.Object cookie) {
    }

    public void deactivate(String category) {
    }

    public void printServantsList() {
        System.out.println("Available devices:");
        for (String name : this.availableNames) {
            System.out.println(" * " + name);
        }
    }
}

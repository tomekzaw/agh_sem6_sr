package SmartHome.server;

import SmartHome.TempSensor;
import com.zeroc.Ice.Current;

import java.util.Random;

public class TempSensorI implements TempSensor
{
	private static final long serialVersionUID = -2448962912780867770L;

	private float min, max;

    private Random random = new Random();

	public TempSensorI(float min, float max) {
		System.out.println("TempSensorI instantiated");
		this.min = min;
		this.max = max;
	}

	@Override
	public synchronized float getTemperature(Current __current)
	{
		float temperature = (float) (min + random.nextFloat() * (max - min));
		return temperature;
	}
}

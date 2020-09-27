package SmartHome.server;

import com.zeroc.Ice.Current;
import SmartHome.Bulbulator;

public class BulbulatorI implements Bulbulator
{
	private static final long serialVersionUID = -2448962912780867770L;

	public BulbulatorI() {
		System.out.println("BulbulatorI instantiated");
	}

	@Override
	public synchronized void bulbul(Current __current)
	{
		for (int i = 0; i < 10; i++) {
			System.out.print("bul ");
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}
}

package SmartHome.server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.ObjectAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;

public class Server
{
	private MyServantLocator sl = new MyServantLocator();

	public static void main(String[] args)
	{
		Server server = new Server();
		server.run(args);
	}

	public void run(String[] args)
	{
		int status = 0;
		Communicator communicator = null;

		try {
			communicator = Util.initialize(args);

			ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");

            adapter.addServantLocator(sl, "");

			adapter.activate();
			
			System.out.println("Ready...");

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				// System.out.print("> ");
				// System.out.flush();

				String line;
				try {
					line = in.readLine();
				} catch (IOException e) {
					break;
				}

				if (line.equalsIgnoreCase("list")) {
					this.sl.printServantsList();
				} else {
					System.out.println("Invalid command");
				}
			}

			// communicator.waitForShutdown();

        } catch (Exception e) {
            if (e.getCause() instanceof BindException) {
                System.out.println("Address already in use");
            } else {
                e.printStackTrace();
            }
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

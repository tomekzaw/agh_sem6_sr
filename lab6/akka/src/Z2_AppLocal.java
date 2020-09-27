import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

// TODO: no need to change anything in this file
// TODO: remember to place config files in main project folder
public class Z2_AppLocal {

    public static void main(String[] args) throws Exception {

        // config
        File configFile = new File("local_app.conf");
        Config config = ConfigFactory.parseFile(configFile);
        
        // create actor system & actors
        final ActorSystem system = ActorSystem.create("local_system", config);
        final ActorRef local = system.actorOf(Props.create(Z2_LocalActor.class), "local");

        // interaction
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("q")) {
                break;
            }
            local.tell(line, null);
        }

        system.terminate();
    }
}
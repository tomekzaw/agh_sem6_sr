import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import data.ClientRequest;
import data.HistorianResponse;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteOpenMode;

import java.sql.*;

public class Historian extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:db.sqlite";

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ClientRequest.class, request -> {
                    String productName = request.getProductName();

                    Class.forName(DRIVER);
                    final SQLiteConfig config = new SQLiteConfig();
                    config.setOpenMode(SQLiteOpenMode.FULLMUTEX);

                    try (final Connection conn = DriverManager.getConnection(DB_URL, config.toProperties())) {
                        conn.setAutoCommit(true);

                        try (Statement statement = conn.createStatement()) {
                            statement.execute("CREATE TABLE IF NOT EXISTS history (query VARCHAR PRIMARY KEY, popularity INTEGER);");
                        }

                        int popularity = 0;
                        try (final PreparedStatement select = conn.prepareStatement("SELECT popularity FROM history WHERE query = ? LIMIT 1")) {
                            select.setString(1, productName);
                            try (final ResultSet resultSet = select.executeQuery()) {
                                if (resultSet.next()) {
                                    popularity = resultSet.getInt("popularity");
                                }
                            }
                        }

                        HistorianResponse response = new HistorianResponse(popularity);
                        getSender().tell(response, null);

                        try (PreparedStatement upsert =
                                conn.prepareStatement("INSERT INTO history(query, popularity) VALUES(?, 1) ON CONFLICT(query) DO UPDATE SET popularity = popularity + 1;")) {
                            upsert.setString(1, productName);
                            upsert.execute();
                        }
                    }

                    getContext().stop(getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}

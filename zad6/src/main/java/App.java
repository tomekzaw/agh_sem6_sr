import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.pattern.Patterns;
import akka.stream.Materializer;
import data.ClientRequest;
import akka.NotUsed;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.javadsl.Flow;
import data.ServerResponse;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;

import static akka.http.javadsl.server.Directives.*;
import static akka.http.javadsl.server.PathMatchers.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws Exception {
        final ActorSystem system = ActorSystem.create("ceneo");

        final ActorRef server = system.actorOf(Props.create(Server.class), "server");
        final ActorRef[] clients = IntStream
                .rangeClosed(1, 10)
                .mapToObj(i -> system.actorOf(Props.create(Client.class, server), "client" + Integer.toString(i)))
                .toArray(ActorRef[]::new);

        // from https://doc.akka.io/docs/akka-http/current/routing-dsl/index.html#minimal-example

        final Http http = Http.get(system);
        final Materializer materializer = Materializer.matFromSystem(system);

        App app = new App(system, server, materializer);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Server online at http://localhost:8080/");
        System.out.println("Enter product name");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line.equals("q") || line.equals("quit")) {
                break;
            } else {
                String[] productNames = line.split(",");
                IntStream.range(0, productNames.length)
                        .forEach(i -> clients[i].tell(new ClientRequest(productNames[i]), null));
            }
        }

        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private final ActorSystem system;
    private final ActorRef server;
    private final Materializer materializer;

    public App(ActorSystem system, ActorRef server, Materializer materializer) {
        this.system = system;
        this.server = server;
        this.materializer = materializer;
    }

    private Route createRoute() {
        return concat(
                path(segment("price").slash(segment()), (String productName) ->
                        get(() -> {
                            final ClientRequest request = new ClientRequest(productName);

                            final CompletionStage<Object> f = Patterns.ask(this.server, request, Duration.ofSeconds(1))
                                    .exceptionally(exc -> new ServerResponse(productName, null, null));

                            return onSuccess(f, (r) -> {
                                ServerResponse response = (ServerResponse) r;
                                return complete(response.toString());
                            });
                        })
                ),
                path(segment("review").slash(segment()), (String productName) ->
                        get(() -> {
                            String url;
                            try {
                                url = new URIBuilder("https://www.opineo.pl/")
                                        .addParameter("szukaj", productName)
                                        .addParameter("s", "2")
                                        .build().toString();
                            } catch (URISyntaxException e) {
                                return complete("invalid product name");
                            }

                            final CompletionStage<String> f = Http.get(system).singleRequest(HttpRequest.create(url))
                                    .thenCompose(response -> response.entity().toStrict(1000, materializer))
                                    .thenApply(entity -> Jsoup.parse(entity.getData().utf8String())
                                            .body()
                                            .getElementById("screen")
                                            .getElementsByClass("pl_attr")
                                            .first()
                                            .getElementsByTag("ul")
                                            .first()
                                            .text()
                                    ).exceptionally(exc -> "timeout");

                            return onSuccess(f, zalety -> complete(zalety));
                        })
                )
        );
    }
}
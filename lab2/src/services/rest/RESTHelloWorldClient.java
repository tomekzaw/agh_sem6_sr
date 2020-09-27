package services.rest;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class RESTHelloWorldClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI());
		
		// Fluent interfaces
		System.out.println(target.path("rest").path("hello").request()
			.accept(MediaType.TEXT_PLAIN).get(Response.class).toString());
			
		// Get plain text
		System.out.println(target.path("rest").path("hello").request()
			.accept(MediaType.TEXT_PLAIN).get(String.class));
			
		// Get XML
		System.out.println(target.path("rest").path("hello").request()
			.accept(MediaType.TEXT_XML).get(String.class));
			
		// Get HTML
		System.out.println(target.path("rest").path("hello").request()
			.accept(MediaType.TEXT_HTML).get(String.class));
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/sr_lab2_tomekzaw").build();
	}
}

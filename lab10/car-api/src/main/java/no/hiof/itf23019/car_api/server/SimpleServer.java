package no.hiof.itf23019.car_api.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ServiceConfigurationError;
import java.util.Set;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import no.hiof.itf23019.car_api.model.Car;
import no.hiof.itf23019.car_api.resource.RestResource;

public class SimpleServer {

	private String baseUri;
	private HttpServer server;

	public static void main(String[] args) {

		String address = "0.0.0.0";
		int port = 6565;

		SimpleServer simpleServer = new SimpleServer(address, port, null);

		RestResource.testCreateCar(new Car("Volvo", "Red"));
		RestResource.testCreateCar(new Car("Toyota", "Grey"));

		// Start Server
		Set<Class<?>> classes = new HashSet<>(Arrays.asList(RestResource.class));
		String[] packages = { "no.hiof.itf23019" };

		simpleServer.startServer(classes, packages);

		// Listen for a stop command
		simpleServer.listenForInput();

	}

	public SimpleServer(String address, int port, String serviceUri) {
		// the result url is http://address:port/serviceUri
		baseUri = getUri(address, port, serviceUri, false);

	}

	public void startServer(Set<Class<?>> classes, String[] packages) {
		final ResourceConfig config = new ResourceConfig();
		config.registerClasses(classes);
		config.packages(packages);

		URI uri = UriBuilder.fromUri(baseUri).build();
		try {
			server = GrizzlyHttpServerFactory.createHttpServer(uri, config, false);
			server.getServerConfiguration().setAllowPayloadForUndefinedHttpMethods(true);
			server.start();
			System.out.println("Started insecure server at: " + baseUri);
		} catch (IOException | ProcessingException e) {
			throw new ServiceConfigurationError(
					"Make sure you gave a valid address in the config file! (Assignable to this JVM and not in use already)", e);
		}
	}

	private static String getUri(String address, int port, String serviceUri, boolean isSecure) {
		if (address == null) {
			throw new NullPointerException("Address can not be null (Utility:getUri throws NPE)");
		}

		UriBuilder ub = UriBuilder.fromPath("").host(address);
		if (isSecure) {
			ub.scheme("https");
		} else {
			ub.scheme("http");
		}
		if (port > 0) {
			ub.port(port);
		}
		if (serviceUri != null) {
			ub.path(serviceUri);
		}

		String url = ub.toString();

		return url;
	}

	public void listenForInput() {
		System.out.println("Type \"stop\" to shutdown Server...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			while (!input.equals("stop")) {
				input = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		shutdown();
	}

	protected void shutdown() {
		if (server != null) {
			server.shutdownNow();
		}
		System.out.println("Server stopped");
		System.exit(0);
	}
}

package no.hiof.itf23019.car_api.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.UriBuilder;

import no.hiof.itf23019.car_api.resource.RestResource;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import no.hiof.itf23019.car_api.model.Car;

public class SimpleClient {

	
	public static void main(String[] args) {
		
		Client client =  createClient();
		
		
		//DEMO GET
		String uri = "http://localhost:6565/example/cars/0";
		Builder request = client.target(UriBuilder.fromUri(uri).build()).request().header("Content-type", "application/json");
		Response response = request.get();
		
		
		System.out.println("GET " + uri);
		if(response.getStatusInfo().getFamily() == Family.SUCCESSFUL)
		{
			Car car = response.readEntity(Car.class);
			System.out.println(car);
		}
		
		//DEMO POST
		uri = "http://localhost:6565/example/cars/";
		request = client.target(UriBuilder.fromUri(uri).build()).request().header("Content-type", "application/json");
		Car newCar = new Car("TestBrand", "TestColor");
		
		System.out.println("POST " + uri);
		response = request.post(Entity.json(newCar));
		
		if(response.getStatusInfo().getFamily() == Family.SUCCESSFUL)
		{
			Car car = response.readEntity(Car.class);
			System.out.println(car);
		}
	}


	
	private static Client createClient() {
		ClientConfig configuration = new ClientConfig();
		configuration.property(ClientProperties.CONNECT_TIMEOUT, 30000);
		configuration.property(ClientProperties.READ_TIMEOUT, 30000);

		Client client = ClientBuilder.newClient(configuration);
		

		return client;
	}
}

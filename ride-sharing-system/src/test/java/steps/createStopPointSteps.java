package steps;

import cucumber.api.java.en.Given;
//import src.MainApp;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.MainApp;
import src.model.Route;
import src.model.StopPoint;

import static org.junit.Assert.*;

public class createStopPointSteps {
	
	StopPoint stop;
	String street;
	Integer num;
	Route testRoute = new Route("TestRoute");

	@Given("^A street address$")
	public void a_street_address() throws Throwable {
		num = 12;
	    street = "Brockworth Place";
	}

	@When("^A stop point is created$")
	public void a_stop_point_is_created() throws Throwable {
	    stop = new StopPoint(num, street);
	}

	@When("^The address is unique$")
	public void the_address_is_unique() throws Throwable {
		stop = new StopPoint(num, street);
		testRoute.add(stop);
		testRoute.add(stop);
	    assertEquals(testRoute.getStops().size(),1);
	}

	@Then("^The stop is added to the data source$")
	public void the_route_is_added_to_the_data_source() throws Throwable {
		assertTrue(testRoute.getStops().contains(stop));
	}

}

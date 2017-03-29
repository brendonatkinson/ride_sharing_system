package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Route;
import src.model.StopPoint;
import src.model.User;

import static org.junit.Assert.*;

public class addRouteSteps {
	
	User user = new User("James", true);
	StopPoint stop1;
	StopPoint stop2;
	StopPoint stop3;
	Route route;

	@Given("^I am a driver$")
	public void i_am_a_driver() throws Throwable {
	    assertTrue(user.isDriver());
	}

	@When("^I have a collection StopPoints$")
	public void i_have_a_collection_StopPoints() throws Throwable {
		stop1 = new StopPoint(15, "Creyke Road");
		stop2 = new StopPoint(12, "Main St");
		stop3 = new StopPoint(999, "Grassmere St");
	}

	@Then("^A route is created$")
	public void a_route_is_created() throws Throwable {
	    route = new Route("NewRoute");
	    route.add(stop1);
	    route.add(stop2);
	    route.add(stop3);
	    assertEquals(route.toString(), "NewRoute: 15 Creyke Road, 12 Main St, 999 Grassmere St,");
	}

}

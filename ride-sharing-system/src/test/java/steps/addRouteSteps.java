package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Car;
import src.model.Route;
import src.model.StopPoint;
import src.model.User;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.mockito.Mock;
import org.mockito.Mockito;

public class addRouteSteps {
	
	Route testRoute;
	Collection<StopPoint> stops;
	
	@Mock
	Car testCar = Mockito.mock(Car.class);
	User testUser = Mockito.mock(User.class);
	StopPoint testStop1 = Mockito.mock(StopPoint.class);
	StopPoint testStop2 = Mockito.mock(StopPoint.class);
	StopPoint testStop3 = Mockito.mock(StopPoint.class);
	

	@When("^I have a collection StopPoints$")
	public void i_have_a_collection_StopPoints() throws Throwable {
		stops = new ArrayList<StopPoint>();
		stops.add(testStop1);
		stops.add(testStop2);
		stops.add(testStop3);
		assertEquals(stops.size(), 3);
	}

	@Then("^A route is created$")
	public void a_route_is_created() throws Throwable {
		Mockito.when(testStop1.getAddress()).thenReturn("15 Creyke Road");
		Mockito.when(testStop2.getAddress()).thenReturn("12 Main St");
		Mockito.when(testStop3.getAddress()).thenReturn("999 Grassmere St");
	    testRoute = new Route("NewRoute");
	    for (StopPoint stops : stops){
	    	testRoute.add(stops);
	    }
	    assertEquals(testRoute.getStopsString().get(), "NewRoute: 15 Creyke Road, 12 Main St, 999 Grassmere St, ");
	}

}

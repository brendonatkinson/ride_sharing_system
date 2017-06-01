package steps;


import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Car;
import src.model.Route;
import src.model.StopPoint;
import src.model.Trip;
import src.model.User;
import java.util.ArrayList;
import java.util.Collection;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class addTripSteps {
	
	@Mock
	User testUser = Mockito.mock(User.class);
	Car testCar = Mockito.mock(Car.class);
	StopPoint testStop1 = Mockito.mock(StopPoint.class);
	StopPoint testStop2 = Mockito.mock(StopPoint.class);
	StopPoint testStop3 = Mockito.mock(StopPoint.class);
	Route testRoute = Mockito.mock(Route.class);
	
	Collection<StopPoint> stops;
	Trip testTrip = new Trip(testUser, testCar, testRoute, "To UC", false);

	@Given("^I am a driver$")
	public void i_am_a_driver() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@When("^I select a route I can add stop times$")
	public void i_select_a_route_I_can_add_stop_times() throws Throwable {
		stops = new ArrayList<StopPoint>();
		stops.add(testStop1);
		stops.add(testStop2);
		stops.add(testStop3);
		
		for (StopPoint stop: stops){
	    	testTrip.addStop(stop, "11:00");
	    }
	    
	    assertEquals(testTrip.getStops().size(), 3);
	}


	@When("^Specify the direction of travel$")
	public void specify_the_direction_of_travel() throws Throwable {
		assertEquals(testTrip.getTripDirection().get(),"To UC"); 
	}

	@When("^Indicate recurrency$")
	public void indicate_recurrency() throws Throwable {
	    assertFalse(testTrip.getRecurrency());
	}

	@Then("^The trip is created but not shared$")
	public void the_trip_is_created_but_not_shared() throws Throwable {
	    assertFalse(testTrip.getTripShared());
	}

}

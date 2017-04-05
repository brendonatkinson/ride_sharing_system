package steps;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Car;
import src.model.RideOverview;
import src.model.Route;
import src.model.StopPoint;
import src.model.Trip;
import src.model.User;

public class viewAvailableRidesSteps {

	@Mock
	User testUser = Mockito.mock(User.class);
	Car testCar = Mockito.mock(Car.class);
	StopPoint testStop1 = Mockito.mock(StopPoint.class);
	StopPoint testStop2 = Mockito.mock(StopPoint.class);
	StopPoint testStop3 = Mockito.mock(StopPoint.class);
	StopPoint selectedStop = Mockito.mock(StopPoint.class);
	Route testRoute = new Route("TestRoute");
	Trip testTrip = new Trip(testUser, testCar, testRoute, "To UC", false);
	
	RideOverview testContainer = new RideOverview();
	
	@Given("^A list of Stop Points$")
	public void a_list_of_Stop_Points() throws Throwable {
	    testContainer.addStop(testStop1);
	    testContainer.addStop(testStop2);
	    testContainer.addStop(testStop3);
	    assertNotNull(testContainer.getAvailStops());
	}

	@When("^I select a Stop Point$")
	public void i_select_a_Stop_Point() throws Throwable {
	    selectedStop = testContainer.getStopList().get(0);
	}

	@Then("^The Available Rides are displayed$")
	public void the_Available_Rides_are_displayed() throws Throwable {
		testRoute.add(testStop1);
		testContainer.addTrip(testTrip);
		assertNotNull(testContainer.getTripsInvolving(testStop1));
	}

}

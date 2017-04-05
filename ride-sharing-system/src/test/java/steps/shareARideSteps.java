package steps;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.MainApp;
import src.model.Car;
import src.model.Route;
import src.model.Trip;
import src.model.User;

public class shareARideSteps {
	
	@Mock
	User testUser = Mockito.mock(User.class);
	Car testCar = Mockito.mock(Car.class);
	Route testRoute = Mockito.mock(Route.class);
	MainApp main = Mockito.mock(MainApp.class);
	
	Trip testTrip = new Trip(testUser, testCar, testRoute, "To UC", false);
	

	@Given("^I have a trip$")
	public void i_have_a_trip() throws Throwable {
	    assertNotNull(testTrip);
	}

	@When("^I share the ride$")
	public void i_press_share() throws Throwable {
	    testTrip.setTripShared(true);
	    //main.addTrip(testTrip);
	    }

	@Then("^The ride is displayed in the available rides list$")
	public void the_ride_is_displayed_in_the_available_rides_list() throws Throwable {
	   //assertTrue(main.getCurrTrips().contains(testTrip));
	}

}

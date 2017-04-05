package steps;

import org.mockito.Mock;
import org.mockito.Mockito;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Car;
import src.model.Route;
import src.model.Trip;
import src.model.User;
import static org.junit.Assert.*;

public class bookRideSteps {
	Trip t;
	@Mock
	Route testRoute = Mockito.mock(Route.class);
	Car testCar = Mockito.mock(Car.class);
	User testUser = Mockito.mock(User.class);

	@When("^I press book button$")
	public void i_press_book_button() throws Throwable {
		t = new Trip(testUser, testCar, testRoute, "To UC", false);
		t.setAvailSeats(4);
		t.bookRide(testUser);
	}

	@Then("^I am booked on the ride, available seats =-(\\d+)$")
	public void i_am_booked_on_the_ride_available_seats(int arg1) throws Throwable {
	    assertTrue(t.getBookedUsers().contains(testUser));
	    assertTrue(t.getAvailSeats().equals(3));
	}

}

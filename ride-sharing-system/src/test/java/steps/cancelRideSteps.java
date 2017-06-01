package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
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

public class cancelRideSteps {
	
	@Given("^I have a booked ride$")
	public void i_have_a_booked_ride() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I cancel the ride$")
	public void i_cancel_the_ride() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The seat is freed up$")
	public void the_seat_is_freed_up() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}

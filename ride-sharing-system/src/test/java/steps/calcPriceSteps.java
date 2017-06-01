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

public class calcPriceSteps {


	@Given("^I have a car$")
	public void i_have_a_car() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I add a stoppoint$")
	public void i_add_a_stoppoint() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The price is calculated$")
	public void the_price_is_calculated() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}

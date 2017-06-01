package steps;

import cucumber.api.PendingException;
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

public class registerUserSteps {
	
	@When("^I register a new account$")
	public void i_register_a_new_account() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^A new account is created$")
	public void a_new_account_is_created() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}

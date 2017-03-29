package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Car;
import src.model.User;

import static org.junit.Assert.*;

public class registerCarSteps {
	
	public User test = new User("Peter", true);
	public Car newcar = new Car("Sedan", "Honda", "Blue", "FLZ111", 1997, 4);

	
	@Given("^I am a registered user$")
	public void i_am_a_registered_user() throws Throwable {
	    assertEquals("User name is Peter", test.getName(), "Peter");
	}

	@When("^I register a new car$")
	public void i_register_a_new_car() throws Throwable {
	    test.addCar(newcar);
	    assertNotNull(test.getCars());
	}

	@Then("^It is associated with my account$")
	public void it_is_associated_with_my_account() throws Throwable {
		String plate = test.getCars().get(0).getLicensePlate();
	    assertEquals(plate, newcar.getLicensePlate());
	}

}

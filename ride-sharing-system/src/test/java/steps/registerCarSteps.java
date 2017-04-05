package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Car;
import src.model.User;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Mockito;

public class registerCarSteps {
	
	@Mock
	Car testCar = Mockito.mock(Car.class);
	public User testUser = new User("Peter", true);
	
	@Given("^I am a registered user$")
	public void i_am_a_registered_user() throws Throwable {
	    assertEquals("User name is Peter", testUser.getName(), "Peter");
	}

	@When("^I register a new car$")
	public void i_register_a_new_car() throws Throwable {
	    testUser.addCar(testCar);
	    assertEquals(testUser.getCars().size(), 1);
	}

	@Then("^It is associated with my account$")
	public void it_is_associated_with_my_account() throws Throwable {
		Mockito.when(testCar.getLicensePlate()).thenReturn("ABC123");
		String plate = testUser.getCars().get(0).getLicensePlate();
	    assertEquals(plate, testCar.getLicensePlate());
	}

}

package steps;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.model.Car;
import src.model.Route;
import src.model.StopPoint;
import src.model.Trip;
import src.model.User;

import java.util.Iterator;

import static org.junit.Assert.*;

public class addTripSteps {
	
	public User user = new User("James", true);
	public Car newcar = new Car("Sedan", "Honda", "Blue", "FLZ111", 1997, 4);
	public StopPoint stop1 = new StopPoint(15, "Creyke Road");
	public StopPoint stop2 = new StopPoint(12, "Main St");
	public StopPoint stop3 = new StopPoint(999, "Grassmere St");
	public Route triproute = new Route("NewRoute");
	public Trip newtrip = new Trip(user, newcar, false, false);

	@When("^I select a route I can add stop times$")
	public void i_select_a_route_I_can_add_stop_times() throws Throwable {
		triproute.add(stop1);
		triproute.add(stop2);
		triproute.add(stop3);
		
		Iterator<StopPoint> iter = triproute.getStops().iterator();
	    while (iter.hasNext()){
	    	newtrip.addStop(iter.next(), "11:00");
	    }
	    
	    assertNotNull(newtrip.getRoute());
	}


	@When("^Specify the direction of travel$")
	public void specify_the_direction_of_travel() throws Throwable {
		assertEquals(newtrip.getTripDirection(),"Uni ->"); 
	}

	@When("^Indicate recurrency$")
	public void indicate_recurrency() throws Throwable {
	    assertFalse(newtrip.getRecurrency());
	}

	@Then("^The trip is created but not shared$")
	public void the_trip_is_created_but_not_shared() throws Throwable {
	    assertFalse(newtrip.getTripShared());
	}

}

package src.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Trip {
	
	private Collection<Tuple> route;
	private Boolean tripDirection = false; //True -> Uni, False <- Uni
	private Boolean recurrency;
	private String recurrDays;
	private Date expiryDate;
	private User creatingUser;
	private Car rideToBeUsed;
	private Boolean tripShared = false;
	

	public Trip(User creator, Car wheels, Boolean direction, Boolean recurr, String days, Date expiry){
		this.creatingUser = creator;
		this.rideToBeUsed = wheels;
		this.tripDirection = direction;
		this.recurrency = recurr;
		this.recurrDays = days;
		this.expiryDate = expiry;
		this.route = new ArrayList<Tuple>();
	}
	
	public Trip(User creator, Car wheels, Boolean direction, Boolean recurr){
		this.creatingUser = creator;
		this.rideToBeUsed = wheels;
		this.tripDirection = direction;
		this.recurrency = recurr;
		this.route = new ArrayList<Tuple>();
	}
	
	public void addStop(StopPoint stop, String time){
		route.add(new Tuple(stop, time));
	}

	public Collection<Tuple> getRoute() {
		return route;
	}

	public String getTripDirection() {
		String direction = "Uni ->";
		if (tripDirection)
			direction = "Uni <-";
		return direction;
	}

	public void setTripDirection(Boolean tripDirection) {
		this.tripDirection = tripDirection;
	}

	public Boolean getRecurrency() {
		return recurrency;
	}

	public void setRecurrency(Boolean recurrency) {
		this.recurrency = recurrency;
	}

	public String getRecurrDays() {
		return recurrDays;
	}

	public void setRecurrDays(String recurrDays) {
		this.recurrDays = recurrDays;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getTripShared() {
		return tripShared;
	}

	public void setTripShared(Boolean tripShared) {
		this.tripShared = tripShared;
	}

	public User getCreatingUser() {
		return creatingUser;
	}

	public Car getRideToBeUsed() {
		return rideToBeUsed;
	}
}

class Tuple { 
	  public final StopPoint stop; 
	  public final String time; 
	  public Tuple(StopPoint x, String y) { 
	    this.stop = x; 
	    this.time = y; 
	  } 
	} 

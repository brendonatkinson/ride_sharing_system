package src.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trip {
	
	private Collection<Tuple> stopTimes;
	private Route route;
	private BooleanProperty tripDirection; //True -> Uni, False <- Uni
	private BooleanProperty recurrency;
	private String recurrDays;
	private Date expiryDate;
	private User creatingUser;
	private Car rideToBeUsed;
	private BooleanProperty tripShared;
	

	public Trip(User creator, Car wheels, Route routeToFollow, Boolean direction, Boolean recurr, String days, Date expiry){
		this.creatingUser = creator;
		this.rideToBeUsed = wheels;
		this.route = routeToFollow;
		this.tripDirection = new SimpleBooleanProperty(direction);
		this.recurrency = new SimpleBooleanProperty(recurr);
		this.tripShared = new SimpleBooleanProperty(false);
		this.recurrDays = days;
		this.expiryDate = expiry;
		this.stopTimes = new ArrayList<Tuple>();
	}
	
	public Trip(User creator, Car wheels, Route routeToFollow, Boolean direction, Boolean recurr){
		this.creatingUser = creator;
		this.rideToBeUsed = wheels;
		this.route = routeToFollow;
		this.tripDirection = new SimpleBooleanProperty(direction);
		this.recurrency = new SimpleBooleanProperty(recurr);
		this.stopTimes = new ArrayList<Tuple>();
		this.tripShared = new SimpleBooleanProperty(false);
	}
	
	public void addStop(StopPoint stop, String time){
		stopTimes.add(new Tuple(stop, time));
	}
	
	public Collection<Tuple> getStops(){
		return this.stopTimes;
	}

	public Route getRoute() {
		return route;
	}

	public StringProperty getTripDirection() {
		StringProperty direction = new SimpleStringProperty("From UC");
		if (tripDirection.get())
			direction.set("To UC");
		return direction;
	}

	public void setTripDirection(Boolean tripDirection) {
		this.tripDirection.set(tripDirection);
	}
	

	public Boolean getRecurrency() {
		return recurrency.get();
	}
	
	public BooleanProperty getRecurrencyProperty() {
		return recurrency;
	}

	public void setRecurrency(Boolean recurrency) {
		this.recurrency.set(recurrency);
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
		return tripShared.get();
	}
	
	public BooleanProperty getTripSharedProperty() {
		return tripShared;
	}

	public void setTripShared(Boolean tripShared) {
		this.tripShared.set(tripShared);
	}

	public User getCreatingUser() {
		return creatingUser;
	}

	public StringProperty getRideToBeUsed() {
		return new SimpleStringProperty(rideToBeUsed.toString());
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

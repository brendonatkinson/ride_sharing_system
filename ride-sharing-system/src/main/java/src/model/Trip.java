package src.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trip {
	
	private Map<StopPoint,String> stopTimes;
	private Route route;
	private BooleanProperty tripDirection; //True -> Uni, False <- Uni
	private BooleanProperty recurrency;
	private StringProperty recurrDays;
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
		this.recurrDays = new SimpleStringProperty(days);
		this.expiryDate = expiry;
		this.stopTimes = new HashMap<StopPoint, String>();
	}
	
	public Trip(User creator, Car wheels, Route routeToFollow, Boolean direction, Boolean recurr){
		this.creatingUser = creator;
		this.rideToBeUsed = wheels;
		this.route = routeToFollow;
		this.tripDirection = new SimpleBooleanProperty(direction);
		this.recurrency = new SimpleBooleanProperty(recurr);
		this.stopTimes = new HashMap<StopPoint, String>();
		this.tripShared = new SimpleBooleanProperty(false);
	}
	
	public void addStop(StopPoint stop, String time){
		stopTimes.put(stop, time);
	}
	
	public Map<StopPoint, String> getStops(){
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
		return recurrDays.get();
	}

	public void setRecurrDays(String recurrDays) {
		this.recurrDays.set(recurrDays);
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

	public StringProperty getRecurrDaysProperty() {
		return recurrDays;
	}
}

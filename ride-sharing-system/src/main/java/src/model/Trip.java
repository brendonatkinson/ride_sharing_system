package src.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trip {
	
	private Map<StopPoint,String> stopTimes;
	private Route route;
	private StringProperty tripDirection;
	private BooleanProperty recurrency;
	private StringProperty recurrDays;
	private LocalDate expiryDate;
	private User creatingUser;
	private Car rideToBeUsed;
	private BooleanProperty tripShared;
	
	public Trip(User creator, Car wheels, Route routeToFollow, String direction, Boolean recurr){
		this.creatingUser = creator;
		this.rideToBeUsed = wheels;
		this.route = routeToFollow;
		this.tripDirection = new SimpleStringProperty(direction);
		this.recurrency = new SimpleBooleanProperty(recurr);
		this.stopTimes = new HashMap<StopPoint, String>();
		this.tripShared = new SimpleBooleanProperty(false);
		this.recurrDays = new SimpleStringProperty();
		this.expiryDate = LocalDate.now();
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
		return tripDirection;
	}

	public void setTripDirection(String direction) {
		this.tripDirection.set(direction);
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

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
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
	
	public Car getCar() {
		return rideToBeUsed;
	}
	
	public void setCar(Car newCar) {
		rideToBeUsed = newCar;
	}

	public StringProperty getRecurrDaysProperty() {
		return recurrDays;
	}

	public void setRoute(Route newRoute) {
		this.route = newRoute;
		
	}
}

/*
 * bja90
 * 46376139
 */
package src.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Trip.
 */
public class Trip {

	/** The stop times. */
	private Map<StopPoint,String> stopTimes;

	/** The route. */
	private Route route;

	/** The trip direction. */
	private StringProperty tripDirection;

	/** The recurrency. */
	private BooleanProperty recurrency;

	/** The recurr days. */
	private StringProperty recurrDays;

	/** The expiry date. */
	private LocalDate expiryDate;

	/** The creating user. */
	private User creatingUser;

	/** The ride to be used. */
	private Car rideToBeUsed;

	/** The trip shared. */
	private BooleanProperty tripShared;

	/** The booked users. */
	private Collection<User> bookedUsers;

	/** The avail seats. */
	private Integer availSeats;

	/** The day of trip. */
	private LocalDate dayOfTrip;

	/**
	 * Instantiates a new trip.
	 *
	 * @param creator the creator
	 * @param wheels the wheels
	 * @param routeToFollow the route to follow
	 * @param direction the direction
	 * @param recurr the recurr
	 */
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
		this.bookedUsers = new ArrayList<User>();
		this.dayOfTrip = LocalDate.now();
		this.availSeats = new Integer(0);  //No seats available as default
	}


	/**
	 * Gets the avail seats.
	 *
	 * @return the avail seats
	 */
	public Integer getAvailSeats() {
		return availSeats;
	}

	/**
	 * Sets the avail seats.
	 *
	 * @param availSeats the new avail seats
	 */
	public void setAvailSeats(Integer availSeats) {
		this.availSeats = availSeats;
	}

	/**
	 * Adds the stop.
	 *
	 * @param stop the stop
	 * @param time the time
	 */
	public void addStop(StopPoint stop, String time){
		stopTimes.put(stop, time);
	}

	/**
	 * Gets the stops.
	 *
	 * @return the stops
	 */
	public Map<StopPoint, String> getStops(){
		return this.stopTimes;
	}

	/**
	 * Gets the route.
	 *
	 * @return the route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * Gets the trip direction.
	 *
	 * @return the trip direction
	 */
	public StringProperty getTripDirection() {
		return tripDirection;
	}

	/**
	 * Sets the trip direction.
	 *
	 * @param direction the new trip direction
	 */
	public void setTripDirection(String direction) {
		this.tripDirection.set(direction);
	}


	/**
	 * Gets the recurrency.
	 *
	 * @return the recurrency
	 */
	public Boolean getRecurrency() {
		return recurrency.get();
	}

	/**
	 * Gets the recurrency property.
	 *
	 * @return the recurrency property
	 */
	public BooleanProperty getRecurrencyProperty() {
		return recurrency;
	}

	/**
	 * Sets the recurrency.
	 *
	 * @param recurrency the new recurrency
	 */
	public void setRecurrency(Boolean recurrency) {
		this.recurrency.set(recurrency);
	}

	/**
	 * Gets the recurr days.
	 *
	 * @return the recurr days
	 */
	public String getRecurrDays() {
		return recurrDays.get();
	}

	/**
	 * Sets the recurr days.
	 *
	 * @param recurrDays the new recurr days
	 */
	public void setRecurrDays(String recurrDays) {
		this.recurrDays.set(recurrDays);
	}

	/**
	 * Gets the expiry date.
	 *
	 * @return the expiry date
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiry date.
	 *
	 * @param expiryDate the new expiry date
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Gets the trip shared.
	 *
	 * @return the trip shared
	 */
	public Boolean getTripShared() {
		return tripShared.get();
	}

	/**
	 * Gets the trip shared property.
	 *
	 * @return the trip shared property
	 */
	public BooleanProperty getTripSharedProperty() {
		return tripShared;
	}

	/**
	 * Sets the trip shared.
	 *
	 * @param tripShared the new trip shared
	 */
	public void setTripShared(Boolean tripShared) {
		this.tripShared.set(tripShared);
	}

	/**
	 * Gets the creating user.
	 *
	 * @return the creating user
	 */
	public User getCreatingUser() {
		return creatingUser;
	}

	/**
	 * Gets the ride to be used.
	 *
	 * @return the ride to be used
	 */
	public StringProperty getRideToBeUsed() {
		return new SimpleStringProperty(rideToBeUsed.toString());
	}

	/**
	 * Gets the car.
	 *
	 * @return the car
	 */
	public Car getCar() {
		return rideToBeUsed;
	}

	/**
	 * Gets the day of trip.
	 *
	 * @return the day of trip
	 */
	public LocalDate getDayOfTrip() {
		return dayOfTrip;
	}


	/**
	 * Sets the day of trip.
	 *
	 * @param dayOfTrip the new day of trip
	 */
	public void setDayOfTrip(LocalDate dayOfTrip) {
		this.dayOfTrip = dayOfTrip;
	}


	/**
	 * Sets the car.
	 *
	 * @param newCar the new car
	 */
	public void setCar(Car newCar) {
		rideToBeUsed = newCar;
	}

	/**
	 * Gets the recurr days property.
	 *
	 * @return the recurr days property
	 */
	public StringProperty getRecurrDaysProperty() {
		return recurrDays;
	}

	/**
	 * Sets the route.
	 *
	 * @param newRoute the new route
	 */
	public void setRoute(Route newRoute) {
		this.route = newRoute;

	}

	/**
	 * Book ride.
	 *
	 * @param currUser the curr user
	 * @return the boolean
	 */
	public Boolean bookRide(User currUser) {
		if (this.availSeats > 0){
			bookedUsers.add(currUser);
			this.availSeats -= 1;
			return true;
		}
		return false;
	}


	/**
	 * Gets the booked users.
	 *
	 * @return the booked users
	 */
	public Collection<User> getBookedUsers() {
		return this.bookedUsers;
	}


}

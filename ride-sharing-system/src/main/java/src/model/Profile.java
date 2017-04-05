/*
 * bja90
 * 46376139
 */
package src.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class Profile.
 */
public class Profile {

	/** The curr user. */
	private User currUser;

	/** The observable routes. */
	private ObservableList<Route> observableRoutes;

	/** The observable trips. */
	private ObservableList<Trip> observableTrips;

	/** The stop container. */
	private Set<StopPoint> stopContainer;

	/**
	 * Instantiates a new profile.
	 *
	 * @param newUser the new user
	 */
	public Profile(User newUser){
		currUser = newUser;
		observableRoutes = FXCollections.observableArrayList();
		observableTrips = FXCollections.observableArrayList();
		stopContainer = new HashSet<StopPoint>();
	}

	/**
	 * Gets the user routes.
	 *
	 * @return the user routes
	 */
	public ObservableList<Route> getUserRoutes() {
		return observableRoutes;
	}

	/**
	 * Adds the route.
	 *
	 * @param userRoute the user route
	 * @return the boolean
	 */
	public Boolean addRoute(Route userRoute) {
		return this.observableRoutes.add(userRoute);
	}

	/**
	 * Removes the route.
	 *
	 * @param routeToDel the route to del
	 */
	public void removeRoute(Route routeToDel) {
		this.observableRoutes.remove(routeToDel);
	}

	/**
	 * Gets the stop container.
	 *
	 * @return the stop container
	 */
	public Iterator<StopPoint> getStopContainer() {
		return stopContainer.iterator();
	}

	/**
	 * Gets the curr user.
	 *
	 * @return the curr user
	 */
	public User getCurrUser() {
		return currUser;
	}

	/**
	 * Sets the curr user.
	 *
	 * @param currUser the new curr user
	 */
	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}

	/**
	 * Gets the trips.
	 *
	 * @return the trips
	 */
	public ObservableList<Trip> getTrips() {
		return observableTrips;
	}

	/**
	 * Adds the trip.
	 *
	 * @param newTrip the new trip
	 */
	public void addTrip(Trip newTrip) {
		this.observableTrips.add(newTrip);
	}

	/**
	 * Removes the trip.
	 *
	 * @param tripToDel the trip to del
	 */
	public void removeTrip(Trip tripToDel) {
		this.observableTrips.remove(tripToDel);
	}

	/**
	 * Reset profile.
	 */
	public void resetProfile() {
		this.observableRoutes.clear();
		this.observableTrips.clear();
		this.currUser = null;
		this.stopContainer.clear();

	}
}

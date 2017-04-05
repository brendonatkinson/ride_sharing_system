package src.model;

import java.util.Collection;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class RideOverview.
 */
public class RideOverview {

	/** The avail trips. */
	Collection<Trip> availTrips;
	
	/** The avail stops. */
	Collection<StopPoint> availStops;

	/**
	 * Instantiates a new ride overview.
	 */
	public RideOverview(){
		availTrips = new HashSet<Trip>();
		availStops = new HashSet<StopPoint>();
	}

	/**
	 * Adds the trip.
	 *
	 * @param newTrip the new trip
	 * @return the boolean
	 */
	public Boolean addTrip(Trip newTrip){
		return availTrips.add(newTrip);
	}

	/**
	 * Adds the stop.
	 *
	 * @param newStop the new stop
	 * @return the boolean
	 */
	public Boolean addStop(StopPoint newStop){
		return availStops.add(newStop);
	}

	/**
	 * Gets the avail trips.
	 *
	 * @return the avail trips
	 */
	public Collection<Trip> getAvailTrips() {
		return availTrips;
	}

	/**
	 * Gets the avail stops.
	 *
	 * @return the avail stops
	 */
	public Collection<StopPoint> getAvailStops() {
		return availStops;
	}

	/**
	 * Gets the trip list.
	 *
	 * @return the trip list
	 */
	public ObservableList<Trip> getTripList() {
		return FXCollections.observableArrayList(availTrips);
	}

	/**
	 * Gets the stop list.
	 *
	 * @return the stop list
	 */
	public ObservableList<StopPoint> getStopList() {
		return FXCollections.observableArrayList(availStops);
	}

	/**
	 * Removes the stop.
	 *
	 * @param stopToDel the stop to del
	 */
	public void removeStop(StopPoint stopToDel) {
		availStops.remove(stopToDel);

	}

	/**
	 * Gets the trips involving.
	 *
	 * @param selectedStop the selected stop
	 * @return the trips involving
	 */
	public Collection<Trip> getTripsInvolving(StopPoint selectedStop) {
		Collection<Trip> filteredTrips = new HashSet<Trip>();
		for (Trip trips: availTrips){
			if (trips.getRoute().getStops().contains(selectedStop)){
				filteredTrips.add(trips);
			}
		}
		return filteredTrips;

	}
}

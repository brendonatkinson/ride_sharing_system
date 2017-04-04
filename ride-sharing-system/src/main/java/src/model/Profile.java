package src.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Profile {
	
	private User currUser;
	private ObservableList<Route> observableRoutes;
	private ObservableList<Trip> observableTrips;
	private Set<StopPoint> stopContainer;
	
	public Profile(User newUser){
		currUser = newUser;
		observableRoutes = FXCollections.observableArrayList();
		observableTrips = FXCollections.observableArrayList();
		stopContainer = new HashSet<StopPoint>();
	}
	
	public ObservableList<Route> getUserRoutes() {
		return observableRoutes;
	}

	public Boolean addRoute(Route userRoute) {
		return this.observableRoutes.add(userRoute);
	}
	
	public void removeRoute(Route routeToDel) {
		this.observableRoutes.remove(routeToDel);
	}

	public Iterator<StopPoint> getStopContainer() {
		return stopContainer.iterator();
	}

	public User getCurrUser() {
		return currUser;
	}
	
	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}

	public ObservableList<Trip> getTrips() {
		return observableTrips;
	}

	public void addTrip(Trip newTrip) {
		 this.observableTrips.add(newTrip);
	}
	
	public void removeTrip(Trip tripToDel) {
		this.observableTrips.remove(tripToDel);
	}

	public void resetProfile() {
		this.observableRoutes.clear();
		this.observableTrips.clear();
		this.currUser = null;
		this.stopContainer.clear();
		
	}
}

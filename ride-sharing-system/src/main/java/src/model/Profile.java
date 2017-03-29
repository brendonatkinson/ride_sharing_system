package src.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Profile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User currUser;
	private ObservableList<Route> observableRoutes;
	private Set<StopPoint> stopContainer;
	
	public Profile(User newUser){
		currUser = newUser;
		observableRoutes= FXCollections.observableArrayList();
		stopContainer = new HashSet<StopPoint>();
	}
	
	public ObservableList<Route> getUserRoutes() {
		return observableRoutes;
	}

	public Boolean addRoute(Route userRoute) {
		return this.observableRoutes.add(userRoute);
	}

	public Iterator<StopPoint> getStopContainer() {
		return stopContainer.iterator();
	}

	public Boolean newStopPoint(StopPoint newStop) {
		return this.stopContainer.add(newStop);
	}

	public User getCurrUser() {
		return currUser;
	}
	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}

	public void removeRoute(Route routeToDel) {
		this.observableRoutes.remove(routeToDel);
	}

}

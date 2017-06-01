/*
 * bja90
 * 46376139
 */
package src.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

	private String userName;

	private Integer passWord;

	/** The observable routes. */
	private ObservableList<Route> observableRoutes;

	/** The stop container. */
	private Set<StopPoint> stopContainer;

	private List<String> notifications;
	/**
	 * Instantiates a new profile.
	 *
	 * @param newUser the new user
	 */
	public Profile(User newUser, String pw){
		userName = newUser.getUniversityID().toString();
		passWord = pw.hashCode();
		currUser = newUser;
		observableRoutes = FXCollections.observableArrayList();
		stopContainer = new HashSet<StopPoint>();
		notifications = new ArrayList<String>();
	}

	/**
	 * Gets the user routes.
	 *
	 * @return the user routes
	 */
	public ObservableList<Route> getUserRoutes() {
		return observableRoutes;
	}

	public Boolean processNotifications() {
		notifications.clear();
		for (Car car : currUser.getCars()){
			//Test Rego expiry
			if (car.getRegoExpiry().until(LocalDate.now()).getDays() <= 7){
				notifications.add("Rego One Week");
			} else if (car.getRegoExpiry().until(LocalDate.now()).getDays() <= 14){
				notifications.add("Rego Two Weeks");
			} else if (car.getRegoExpiry().until(LocalDate.now()).getMonths() < 1){
				notifications.add("Rego One Month");
			}
			//Test WOF Expiry
			if (car.getWofExpiry().until(LocalDate.now()).getDays() <= 7){
				notifications.add("WOF One Week");
			} else if (car.getWofExpiry().until(LocalDate.now()).getDays() <= 14){
				notifications.add("WOF Two Weeks");
			} else if (car.getWofExpiry().until(LocalDate.now()).getMonths() < 1){
				notifications.add("WOF One Month");
			}
		}
		// Drivers License Expiry
		if (currUser.getDriversLicense().getExpiryDate().until(LocalDate.now()).getDays() <= 7){
			notifications.add("License One Week");
		} else if (currUser.getDriversLicense().getExpiryDate().until(LocalDate.now()).getDays() <= 14){
			notifications.add("License Two Weeks");
		} else if (currUser.getDriversLicense().getExpiryDate().until(LocalDate.now()).getMonths() < 1){
			notifications.add("License One Month");
		}
		if (notifications.isEmpty()){
			return false;
		}	else {
			return true;
		}

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
	 * Reset profile.
	 */
	public void resetProfile() {
		this.observableRoutes.clear();
		this.currUser = null;
		this.stopContainer.clear();

	}

	public Boolean login(String password){
		Integer passwordHash = password.hashCode();
		if (passwordHash.equals(passWord)){
			return true;
		} else {
			return false;
		}
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String pw) {
		this.passWord = pw.hashCode();
	}

	public String getUserName() {
		return userName;
	}

	public String getNotifications() {
		return String.join(",", notifications);
	}
}

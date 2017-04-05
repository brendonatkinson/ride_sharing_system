package src.model;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {

	/** The name. */
	private StringProperty name;

	/** The role. */
	private BooleanProperty role;

	/** The observable cars. */
	private ObservableList<Car> observableCars = FXCollections.observableArrayList();

	/**
	 * Instantiates a new user.
	 *
	 * @param uname the uname
	 * @param urole the urole
	 */
	public User(String uname, Boolean urole){
		name = new SimpleStringProperty(uname);
		role = new SimpleBooleanProperty(urole);
	}


	/**
	 * Sets the driver.
	 *
	 * @param role the role to set
	 */
	public void setDriver(Boolean role) {
		this.role.set(role);
	}

	/**
	 * Returns the drivers role.
	 *
	 * @return the role
	 */
	public Boolean getRole() {
		return this.role.get();
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * Gets the name property.
	 *
	 * @return the name property
	 */
	public StringProperty getNameProperty() {
		return name;
	}


	/**
	 * Adds the car.
	 *
	 * @param vehicle the vehicle
	 */
	public void addCar(Car vehicle){
		observableCars.add(vehicle);
	}

	/**
	 * Gets the cars.
	 *
	 * @return the cars
	 */
	public ObservableList<Car> getCars() {
		return observableCars;
	}


	/**
	 * Removes the car.
	 *
	 * @param carToDel the car to del
	 */
	public void removeCar(Car carToDel) {
		observableCars.remove(carToDel);
	}


	/**
	 * Sets the name.
	 *
	 * @param text the new name
	 */
	public void setName(String text) {
		this.name.set(text);

	}
}

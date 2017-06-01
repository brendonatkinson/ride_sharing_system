/*
 * bja90
 * 46376139
 */
package src.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {

	/** The name. */
	private String name;

	/** The role. */
	private String role;

	/** The observable cars. */
	private ObservableList<Car> observableCars = FXCollections.observableArrayList();
	
	private String universityEmail;
	
	private Integer universityID;
	
	private String address;
	
	private Integer homePhone;
	
	private Integer mobilePhone;
	
	private DriversLicense driversLicense;
	

	public User(String name, String role, String universityEmail, Integer universityID, String address,
			Integer homePhone, Integer mobilePhone, DriversLicense driversLicense) {
		this.name = name;
		this.role = role;
		this.universityEmail = universityEmail;
		this.universityID = universityID;
		this.address = address;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.driversLicense = driversLicense;
	}




	/**
	 * Sets the driver.
	 *
	 * @param role the role to set
	 */
	public void setDriver(String role) {
		this.role = role;
	}

	/**
	 * Returns the drivers role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return this.role;
	}
	


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
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


	public ObservableList<Car> getObservableCars() {
		return observableCars;
	}


	public void setObservableCars(ObservableList<Car> observableCars) {
		this.observableCars = observableCars;
	}


	public String getUniversityEmail() {
		return universityEmail;
	}


	public void setUniversityEmail(String universityEmail) {
		this.universityEmail = universityEmail;
	}


	public Integer getUniversityID() {
		return universityID;
	}


	public void setUniversityID(Integer universityID) {
		this.universityID = universityID;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Integer getHomePhone() {
		return homePhone;
	}


	public void setHomePhone(Integer homePhone) {
		this.homePhone = homePhone;
	}


	public Integer getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(Integer mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public DriversLicense getDriversLicense() {
		return driversLicense;
	}


	public void setDriversLicense(DriversLicense driversLicense) {
		this.driversLicense = driversLicense;
	}


	public void setRole(String role) {
		this.role = role;
	}


	/**
	 * Sets the name.
	 *
	 * @param text the new name
	 */
	public void setName(String text) {
		this.name = text;

	}
}

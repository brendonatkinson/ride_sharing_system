package src.model;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {

	private StringProperty name;
	private BooleanProperty role;
	private ObservableList<Car> observableCars = FXCollections.observableArrayList();

	public User(String uname, Boolean urole){
		name = new SimpleStringProperty(uname);
		role = new SimpleBooleanProperty(urole);
	}


	/**
	 * @param role the role to set
	 */
	public void setDriver(Boolean role) {
		this.role.set(role);
	}


	public String getName() {
		return name.get();
	}
	
	public StringProperty getNameProperty() {
		return name;
	}


	public void addCar(Car vehicle){
		observableCars.add(vehicle);
	}

	public ObservableList<Car> getCars() {
        return observableCars;
    }


	public void removeCar(Car carToDel) {
		observableCars.remove(carToDel);
	}
}

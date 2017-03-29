package src.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Car
{
	private StringProperty carType;
	private StringProperty model;
	private StringProperty carColour;
	private StringProperty licensePlate;
	private IntegerProperty carYear;
	private IntegerProperty numSeats;
	private IntegerProperty availSeats;
	
	public Car(String type, String carModel, String colour, String plate, int year, int seats){
		carType = new SimpleStringProperty(type);
		model = new SimpleStringProperty(carModel);
		carColour = new SimpleStringProperty(colour);
		licensePlate = new SimpleStringProperty(plate);
		carYear =  new SimpleIntegerProperty(year);
		numSeats =  new SimpleIntegerProperty(seats);
		availSeats = new SimpleIntegerProperty(0);  //No seats available as default
	}

	public void setModel(String model) {
		this.model.set(model);
	}
	
	public void setType(String type) {
		this.carType.set(type);
	}
	public void setColour(String colour) {
		this.carColour.set(colour);
	}
	public void setPlate(String plate) {
		this.licensePlate.set(plate);
	}
	public void setYear(Integer year) {
		this.carYear.set(year);
	}
	public void setNumSeats(Integer seats) {
		this.numSeats.set(seats);
	}

	/**
	 * @return the numSeats
	 */
	public Integer getNumSeats() {
		return numSeats.get();
	}
	
	/**
	 * @return the available seats
	 */
	public Integer getAvailSeats() {
		return availSeats.get();
	}

	/**
	 * @param numSeats the numSeats to set
	 */
	public void setAvailSeats(Integer seats) {
		this.availSeats.set(this.availSeats.get() + seats);
	}

	/**
	 * @return the carType
	 */
	public String getCarType() {
		return carType.get();
	}

	/**
	 * @return the model
	 */
	public StringProperty getModelProperty() {
		return model;
	}
	
	public String getModel() {
		return model.get();
	}

	public StringProperty getLicensePlateProperty() {
		return licensePlate;
	}
	
	public String getLicensePlate() {
		return licensePlate.get();
	}

	public Integer getCarYear() {
		return carYear.get();
	}

	public String getCarColour() {
		return carColour.get();
	}

}

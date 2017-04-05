package src.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 */
public class Car
{

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  model.get() + " " + licensePlate.get();
	}

	/** The car type. */
	private StringProperty carType;

	/** The model. */
	private StringProperty model;

	/** The car colour. */
	private StringProperty carColour;

	/** The license plate. */
	private StringProperty licensePlate;

	/** The car year. */
	private IntegerProperty carYear;

	/** The num seats. */
	private IntegerProperty numSeats;



	/**
	 * Instantiates a new car.
	 *
	 * @param type the type
	 * @param carModel the car model
	 * @param colour the colour
	 * @param plate the plate
	 * @param year the year
	 * @param seats the seats
	 */
	public Car(String type, String carModel, String colour, String plate, int year, int seats){
		carType = new SimpleStringProperty(type);
		model = new SimpleStringProperty(carModel);
		carColour = new SimpleStringProperty(colour);
		licensePlate = new SimpleStringProperty(plate);
		carYear =  new SimpleIntegerProperty(year);
		numSeats =  new SimpleIntegerProperty(seats);
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(String model) {
		this.model.set(model);
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.carType.set(type);
	}

	/**
	 * Sets the colour.
	 *
	 * @param colour the new colour
	 */
	public void setColour(String colour) {
		this.carColour.set(colour);
	}

	/**
	 * Sets the plate.
	 *
	 * @param plate the new plate
	 */
	public void setPlate(String plate) {
		this.licensePlate.set(plate);
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		this.carYear.set(year);
	}

	/**
	 * Sets the num seats.
	 *
	 * @param seats the new num seats
	 */
	public void setNumSeats(Integer seats) {
		this.numSeats.set(seats);
	}

	/**
	 * Gets the num seats.
	 *
	 * @return the numSeats
	 */
	public Integer getNumSeats() {
		return numSeats.get();
	}

	/**
	 * Gets the car type.
	 *
	 * @return the carType
	 */
	public String getCarType() {
		return carType.get();
	}

	/**
	 * Gets the model property.
	 *
	 * @return the model
	 */
	public StringProperty getModelProperty() {
		return model;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public String getModel() {
		return model.get();
	}

	/**
	 * Gets the license plate property.
	 *
	 * @return the license plate property
	 */
	public StringProperty getLicensePlateProperty() {
		return licensePlate;
	}

	/**
	 * Gets the license plate.
	 *
	 * @return the license plate
	 */
	public String getLicensePlate() {
		return licensePlate.get();
	}

	/**
	 * Gets the car year.
	 *
	 * @return the car year
	 */
	public Integer getCarYear() {
		return carYear.get();
	}

	/**
	 * Gets the car colour.
	 *
	 * @return the car colour
	 */
	public String getCarColour() {
		return carColour.get();
	}

}

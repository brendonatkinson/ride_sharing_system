/*
 * bja90
 * 46376139
 */
package controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.model.Car;


// TODO: Auto-generated Javadoc
/**
 * The Class CarEditController.
 */
public class CarEditController { // NO_UCD (use default)

	/** The model field. */
	@FXML
	private TextField modelField;

	/** The type field. */
	@FXML
	private TextField typeField;

	/** The plate field. */
	@FXML
	private TextField plateField;

	/** The colour field. */
	@FXML
	private TextField colourField;

	/** The seats field. */
	@FXML
	private TextField seatsField;

	/** The year field. */
	@FXML
	private TextField yearField;
	
	@FXML
	private TextField costField;
	
	@FXML
	private TextField performField;
	
	/** The year field. */
	@FXML
	private DatePicker wofExpiry;
	
	@FXML
	private DatePicker regoExpiry;

	/** The dialog stage. */
	private Stage dialogStage;

	/** The car. */
	private Car car;

	/** The ok clicked. */
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the dialog stage.
	 *
	 * @param dialogStage the new dialog stage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the car.
	 *
	 * @param car the new car
	 */
	public void setCar(Car car) {
		this.car = car;

		modelField.setText(car.getModel());
		typeField.setText(car.getCarType().toString());
		yearField.setText(car.getCarYear().toString());
		plateField.setText(car.getLicensePlate());
		colourField.setText(car.getCarColour());
		seatsField.setText(car.getNumSeats().toString());
		performField.setText(car.getPerformance().toString());
		costField.setText(car.getTripCost().toString());
		regoExpiry.setValue(car.getRegoExpiry());
		wofExpiry.setValue(car.getWofExpiry());
		
	}


	/**
	 * Checks if is ok clicked.
	 *
	 * @return true, if is ok clicked
	 */
	public boolean isOkClicked() {
		return okClicked;
	}


	/**
	 * Handle ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			car.setModel(modelField.getText());
			car.setType(typeField.getText());
			car.setColour(colourField.getText());
			car.setNumSeats(Integer.parseInt(seatsField.getText()));
			car.setPlate(plateField.getText());
			car.setYear(Integer.parseInt(yearField.getText()));
			car.setPerformance(Integer.parseInt(performField.getText()));
			car.setTripCost(Integer.parseInt(costField.getText()));
			car.setWofExpiry(wofExpiry.getValue());
			car.setRegoExpiry(regoExpiry.getValue());
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (modelField.getText() == null || modelField.getText().length() == 0) {
			errorMessage += "No valid model!\n"; 
		}
		if (colourField.getText() == null || colourField.getText().length() == 0) {
			errorMessage += "No valid colour!\n"; 
		}
		if (plateField.getText() == null || plateField.getText().length() == 0) {
			errorMessage += "No valid number plates!\n"; 
		}
		if (typeField.getText() == null || typeField.getText().length() == 0) {
			errorMessage += "No valid type!\n"; 
		}
		if (seatsField.getText() == null || seatsField.getText().length() == 0) {
			errorMessage += "No valid seat number!\n"; 
		} else {
			// try to parse the seats into an int.
			try {
				Integer.parseInt(seatsField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid seat number (must be an integer)!\n"; 
			}
		}
		if (yearField.getText() == null || yearField.getText().length() == 0) {
			errorMessage += "No valid year!\n"; 
		} else {
			// try to parse the year into an int.
			try {
				Integer.parseInt(yearField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid year (must be an integer)!\n"; 
			}
		}
		if (performField.getText() == null || performField.getText().length() == 0) {
			errorMessage += "No valid performance!\n"; 
		} else {
			// try to parse the seats into an int.
			try {
				Integer.parseInt(performField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid performance (must be an integer)!\n"; 
			}
		}
		if (costField.getText() == null || costField.getText().length() == 0) {
			errorMessage += "No valid cost figure!\n"; 
		} else {
			// try to parse the seats into an int.
			try {
				Integer.parseInt(costField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid cost figure (must be an integer)!\n"; 
			}
		}
		if (wofExpiry.getValue().isAfter(LocalDate.now()) || regoExpiry.getValue().isAfter(LocalDate.now())){
			errorMessage += "Invalid WOF/Rego expiry!\n"; 
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
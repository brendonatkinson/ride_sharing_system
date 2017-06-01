/*
 * bja90
 * 46376139
 */
package controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.model.DriversLicense;
import src.model.Profile;
import src.model.User;


// TODO: Auto-generated Javadoc
/**
 * The Class UserEditController.
 */
public class UserEditController { // NO_UCD (use default)

	@FXML
	private TextField nameField;

	@FXML
	private TextField addressField;

	@FXML
	private TextField homePhoneField;

	@FXML
	private TextField mobilePhoneField;

	@FXML
	private TextField ucIDField;
	
	@FXML
	private TextField ucEmailField;
	
	@FXML
	private ChoiceBox<String> accountTypeChoice;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private PasswordField confirmPasswordField;
	
	/** Drivers License Fields */
	@FXML
	private TextField licenseNumberField;
	
	@FXML
	private ChoiceBox<String> licenseTypeChoice;
	
	@FXML
	private DatePicker licenseIssueDate;
	
	@FXML
	private DatePicker licenseExpiryDate;
	
	
	/** The dialog stage. */
	private Stage dialogStage;

	/** The ok clicked. */
	private boolean okClicked = false;

	private Profile user;

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
	public void setUser(Profile user) {
		if (user.getCurrUser().getName() != null){
			this.user = user;

			nameField.setText(user.getCurrUser().getName());
			addressField.setText(user.getCurrUser().getAddress());
			homePhoneField.setText(user.getCurrUser().getHomePhone().toString());
			mobilePhoneField.setText(user.getCurrUser().getMobilePhone().toString());
			ucIDField.setText(user.getCurrUser().getUniversityID().toString());
			ucEmailField.setText(user.getCurrUser().getUniversityEmail());
			//Populate account types
			licenseNumberField.setText(user.getCurrUser().getDriversLicense().getNumber().toString());
			//Populate license types
			licenseIssueDate.setValue(user.getCurrUser().getDriversLicense().getIssueDate());
			licenseExpiryDate.setValue(user.getCurrUser().getDriversLicense().getExpiryDate());}
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
			user.getCurrUser().setName(nameField.getText());
			user.getCurrUser().setAddress(addressField.getText());
			user.getCurrUser().setUniversityEmail(ucEmailField.getText());
			user.getCurrUser().setUniversityID(Integer.parseInt(addressField.getText()));
			user.getCurrUser().setHomePhone(Integer.parseInt(addressField.getText()));
			user.getCurrUser().setMobilePhone(Integer.parseInt(addressField.getText()));
			user.getCurrUser().setDriversLicense(new DriversLicense(licenseTypeChoice.getValue(), Integer.parseInt(licenseNumberField.getText())));
			user.getCurrUser().getDriversLicense().setExpiryDate(licenseExpiryDate.getValue());
			user.getCurrUser().getDriversLicense().setIssueDate(licenseIssueDate.getValue());
			user.setPassWord(passwordField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}


	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (!passwordField.getText().equals(confirmPasswordField.getText())) {
			errorMessage += "Passwords must match!\n"; 
		}
		
		if (nameField.getText() == null || nameField.getText().length() == 0) {
			errorMessage += "Name Invalid!\n"; 
		}
		if (addressField.getText() == null || addressField.getText().length() == 0) {
			errorMessage += "Address Invalid!\n"; 
		}
		if (!ucEmailField.getText().contains("@uclive.ac.nz") || !ucEmailField.getText().contains("@canterbury.ac.nz")) {
			errorMessage += "Email Invalid!\n Must contain @uclive.ac.nz or @canterbury.ac.nz\n"; 
		}
		
		if (licenseIssueDate.getValue().isAfter(LocalDate.now()) || licenseExpiryDate.getValue().isAfter(LocalDate.now())) {
			errorMessage += "Invalid License Dates!\n"; 
		}
		
		if (homePhoneField.getText() == null || homePhoneField.getText().length() == 0) {
			errorMessage += "No valid home phone number!\n"; 
		} else {
			// try to parse the seats into an int.
			try {
				Integer.parseInt(homePhoneField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid home phone number (must be an integer)!\n"; 
			}
		}
		if (mobilePhoneField.getText() == null || mobilePhoneField.getText().length() == 0) {
			errorMessage += "No valid mobile phone number!\n"; 
		} else {
			// try to parse the seats into an int.
			try {
				Integer.parseInt(mobilePhoneField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid mobile phone number (must be an integer)!\n"; 
			}
		}
		if (ucIDField.getText() == null || ucIDField.getText().length() == 0) {
			errorMessage += "No valid UC ID!\n"; 
		} else {
			// try to parse the seats into an int.
			try {
				Integer.parseInt(ucIDField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid UC ID (must be an integer)!\n"; 
			}
		}
		if (licenseNumberField.getText() == null || licenseNumberField.getText().length() == 0) {
			errorMessage += "No valid license number!\n"; 
		} else {
			// try to parse the seats into an int.
			try {
				Integer.parseInt(ucIDField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid license number (must be an integer)!\n"; 
			}
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
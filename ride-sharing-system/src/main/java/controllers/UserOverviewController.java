/*
 * bja90
 * 46376139
 */
package controllers;

import src.MainApp;
import src.model.Car;
import src.model.DriversLicense;
import src.model.Profile;
import src.model.User;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

// TODO: Auto-generated Javadoc
/**
 * The Class UserOverviewController.
 */
public class UserOverviewController { /** The car table. */
	// NO_UCD (use default)
	@FXML
	private TableView<Car> carTable;

	/** The model column. */
	@FXML
	private TableColumn<Car, String> modelColumn;

	/** The plates column. */
	@FXML
	private TableColumn<Car, String> platesColumn;

	/** The user name. */
	@FXML
	private TextField userName;
	
	@FXML
	private Button editButton;

	/** The curr user. */
	private User currUser;

	/** The main app. */
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * Instantiates a new user overview controller.
	 */
	public UserOverviewController() {
	}

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		// Add observable list data to the table
		
			// Initialize the person table with the two columns.
			modelColumn.setCellValueFactory(cellData -> cellData.getValue().getModelProperty());
			platesColumn.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());
	}

	/**
	 * Handle delete car.
	 */
	@FXML
	private void handleDeleteCar() {
		int selectedIndex = carTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			Car carToDel = carTable.getItems().get(selectedIndex);
			mainApp.getCurrUserProfile().getCurrUser().removeCar(carToDel);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Car Selected");
			alert.setContentText("Please select a car in the table.");

			alert.showAndWait();
		}
	}

	/**
	 * Handle new car.
	 */
	@FXML
	private void handleNewCar() {
		Car tempCar = new Car("Sedan", "", "", "", 0, 0);
		boolean okClicked = mainApp.mainHelper.showCarEditDialog(tempCar);
		if (okClicked) {
			currUser.addCar(tempCar);
		}
	}

	/**
	 * Handle edit car.
	 */
	@FXML
	private void handleEditCar() {
		Car selectedCar = carTable.getSelectionModel().getSelectedItem();
		if (selectedCar != null) {
			mainApp.mainHelper.showCarEditDialog(selectedCar);

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}


	/**
	 * Handle edit car.
	 */
	@FXML
	private void handleEditUser() {
		mainApp.mainHelper.showUserEditDialog(mainApp.getCurrUserProfile());
	}

	/**
	 * Handle save user.
	 */
	@FXML
	private void handleSaveUser() {
		if (!userName.getText().isEmpty() || userName.getText() != null){
			try {
				MainApp.save(mainApp.getCurrUserProfile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Handle new user.
	 */
	@FXML
	private void handleNewUser() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Creating a new User Profile");
		alert.setContentText("Are you sure you want to do this? It will remove all routes and trips.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			clearDialogFields();
			mainApp.getCurrUserProfile().resetProfile();
			Profile tempUser = new Profile(new User("", "", "", 0, "", 0, 0, new DriversLicense("", 0)),"");
			boolean okClicked = mainApp.mainHelper.showUserEditDialog(tempUser);
			if (okClicked) {
				mainApp.setCurrUser(tempUser);
			}

		}
	}

	/**
	 * Clear dialog fields.
	 */
	private void clearDialogFields() {
		carTable.getItems().clear();
		userName.setText(currUser.getName());
		mainApp.getCurrUserProfile().resetProfile();
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		currUser = mainApp.getCurrUserProfile().getCurrUser();
		if (currUser.getName().equals(""))
		{
			editButton.setDisable(true);
		}
			
		carTable.setItems(currUser.getCars());
		userName.setText(currUser.getName());
	}
}
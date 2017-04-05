package controllers;

import src.MainApp;
import src.model.Car;
import src.model.User;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	 * Handle save user.
	 */
	@FXML
	private void handleSaveUser() {
		if (!userName.getText().isEmpty() || userName.getText() != null){
			mainApp.getCurrUserProfile().getCurrUser().setName(userName.getText());
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
			//mainApp.getCurrUserProfile().resetProfile();
		}
	}

	/**
	 * Clear dialog fields.
	 */
	private void clearDialogFields() {
		carTable.getItems().clear();
		userName.setText("");
		mainApp.getCurrUserProfile().resetProfile();
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		currUser = mainApp.getCurrUserProfile().getCurrUser();
		carTable.setItems(currUser.getCars());
		userName.setText(currUser.getName());
	}
}
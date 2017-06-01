/*
 * bja90
 * 46376139
 */
package controllers;

import src.MainApp;
import src.model.Profile;
import src.model.Trip;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;


// TODO: Auto-generated Javadoc
/**
 * The Class TripController.
 */
public class RideController { /** The trip table. */
	// NO_UCD (use default)
	@FXML
	private TableView<Trip> rideTable;

	/** The shared column. */
	@FXML
	private TableColumn<Trip, String> dateColumn;

	/** The route name column. */
	@FXML
	private TableColumn<Trip, String> roleColumn;

	/** The direction column. */
	@FXML
	private TableColumn<Trip, String> timeColumn;

	/** The car column. */
	@FXML
	private TableColumn<Trip, String> costColumn;

	/** The recurring column. */
	@FXML
	private TableColumn<Trip, String> statusColumn;


	/** The main app. */
	// Reference to the main application.
	private MainApp mainApp;

	/** The curr user. */
	private Profile currUser;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public RideController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDayOfTrip().toString()));
		roleColumn.setCellValueFactory(cellData -> cellData.getValue().getRole(currUser));
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTripDirection());
		costColumn.setCellValueFactory(cellData -> cellData.getValue().getRideToBeUsed());
	}


	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		currUser = mainApp.getCurrUserProfile();
		//tripTable.setItems(currUser.getTrips());
	}
}
/*
 * bja90
 * 46376139
 */
package controllers;

import src.MainApp;
import src.model.Profile;
import src.model.Trip;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;


// TODO: Auto-generated Javadoc
/**
 * The Class TripController.
 */
public class TripController { /** The trip table. */
	// NO_UCD (use default)
	@FXML
	private TableView<Trip> tripTable;

	/** The shared column. */
	@FXML
	private TableColumn<Trip, Boolean> sharedColumn;

	/** The route name column. */
	@FXML
	private TableColumn<Trip, String> routeNameColumn;

	/** The direction column. */
	@FXML
	private TableColumn<Trip, String> directionColumn;

	/** The car column. */
	@FXML
	private TableColumn<Trip, String> carColumn;

	/** The recurring column. */
	@FXML
	private TableColumn<Trip, Boolean> recurringColumn;


	/** The main app. */
	// Reference to the main application.
	private MainApp mainApp;

	/** The curr user. */
	private Profile currUser;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public TripController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		sharedColumn.setCellValueFactory(cellData -> cellData.getValue().getTripSharedProperty());
		routeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getRoute().getNameProperty());
		directionColumn.setCellValueFactory(cellData -> cellData.getValue().getTripDirection());
		carColumn.setCellValueFactory(cellData -> cellData.getValue().getRideToBeUsed());
		recurringColumn.setCellValueFactory(cellData -> cellData.getValue().getRecurrencyProperty());
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewTrip() {
		Trip tempTrip = new Trip(currUser.getCurrUser(), null, null, null, false);
		boolean okClicked = mainApp.mainHelper.showTripEditDialog(tempTrip);
		if (okClicked) {
			MainApp.addTrip(tempTrip);
			tripTable.setItems(FXCollections.observableArrayList(getUsersTrips()));
		}
	}

	/**
	 * Handle edit trip.
	 */
	@FXML
	private void handleEditTrip() {
		Trip selectedTrip = tripTable.getSelectionModel().getSelectedItem();
		if (selectedTrip != null) {
			mainApp.mainHelper.showTripEditDialog(selectedTrip);
			tripTable.setItems(FXCollections.observableArrayList(getUsersTrips()));

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Trip Selected");
			alert.setContentText("Please select a trip in the table.");

			alert.showAndWait();
		}
	}


	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteTrip() {
		int selectedIndex = tripTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			Trip tripToDel = tripTable.getItems().get(selectedIndex);
			MainApp.getCurrTrips().remove(tripToDel);
			tripTable.setItems(FXCollections.observableArrayList(getUsersTrips()));
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Trip Selected");
			alert.setContentText("Please select a trip to remove.");

			alert.showAndWait();
		}
	}

	public List<Trip> getUsersTrips(){
		ArrayList<Trip> tripsToDisplay = new ArrayList<Trip>();
		for (Trip trip : MainApp.getCurrTrips()){
			if (trip.getCreatingUser().equals(currUser.getCurrUser()))
			{
				tripsToDisplay.add(trip);
			}
		}
		return tripsToDisplay;
	}
	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		//TODO
		tripTable.setItems(FXCollections.observableArrayList(getUsersTrips()));
	}
}
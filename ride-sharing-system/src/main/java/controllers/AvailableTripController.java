package controllers;

import src.MainApp;
import src.model.StopPoint;
import src.model.Trip;

import java.time.format.TextStyle;
import java.util.Locale;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;


// TODO: Auto-generated Javadoc
/**
 * The Class AvailableTripController.
 */
public class AvailableTripController { /** The avail trip table. */
	// NO_UCD (use default)
	@FXML
	private TableView<Trip> availTripTable;

	/** The day column. */
	@FXML
	private TableColumn<Trip, String> dayColumn;

	/** The time column. */
	@FXML
	private TableColumn<Trip, String> timeColumn;

	/** The seats column. */
	@FXML
	private TableColumn<Trip, Integer> seatsColumn;

	/** The user column. */
	@FXML
	private TableColumn<Trip, String> userColumn;

	/** The dir chooser. */
	@FXML
	private ChoiceBox<String> dirChooser;

	/** The stop chooser. */
	@FXML
	private ChoiceBox<StopPoint> stopChooser;

	/** The direction. */
	private String direction = null;

	/** The selected stop. */
	private StopPoint selectedStop = null;

	/** The main app. */
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public AvailableTripController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.

		userColumn.setCellValueFactory(cellData -> cellData.getValue().getCreatingUser().getNameProperty());
		dayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDayOfTrip().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH)));
		timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStops().get(selectedStop)));
		seatsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAvailSeats()).asObject());
		dirChooser.setItems(FXCollections.observableArrayList("To UC", "From UC"));
		stopChooser.setItems(MainApp.getStopPoints());
		stopChooser.setConverter(new StringConverter<StopPoint>() {
			@Override
			public String toString(StopPoint stop) {
				if (stop== null){
					return null;
				} else {
					return stop.getAddress();
				}
			}

			@Override
			public StopPoint fromString(String string) {
				return null;
			}

		});

		// Handle Direction event.
		dirChooser.setOnAction((event) -> {
			if (dirChooser.getSelectionModel().getSelectedItem() != null){
				direction = dirChooser.getSelectionModel().getSelectedItem();
				populateTable();
			}
		});

		stopChooser.setOnAction((event) -> {
			if (stopChooser.getSelectionModel().getSelectedItem() != null){
				selectedStop = stopChooser.getSelectionModel().getSelectedItem();
				populateTable();
			}
		});
	}

	/**
	 * Populate table.
	 */
	private void populateTable() {
		if (direction != null && selectedStop != null){
			ObservableList<Trip> filteredTrips = FXCollections.observableArrayList();
			for (Trip trip: MainApp.getCurrTrips()){
				if (trip.getTripDirection().get().equals(direction) && trip.getStops().containsKey(selectedStop) && trip.getAvailSeats() > 0){
					filteredTrips.add(trip);
				}
			}
			availTripTable.setItems(filteredTrips);
			availTripTable.refresh();
		}	
	}


	/**
	 * Handle view trip.
	 */
	@FXML
	private void handleViewTrip() {
		Trip selectedTrip = availTripTable.getSelectionModel().getSelectedItem();
		if (selectedTrip != null) {
			mainApp.mainHelper.showTripDetails(selectedTrip);
			availTripTable.refresh();
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
	 * Handle view trip.
	 */
	@FXML
	private void handleStopPointSearch() {
		StopPoint searchedStop = null;
		searchedStop = mainApp.mainHelper.showStopPointSearch(selectedStop);
		if (searchedStop != null){
			stopChooser.setValue(searchedStop);
			selectedStop = searchedStop;

			populateTable();
		}
	}

	/**
	 * Handle book trip.
	 */
	@FXML
	private void handleBookTrip() {
		Trip selectedTrip = availTripTable.getSelectionModel().getSelectedItem();
		if (selectedTrip != null) {
			selectedTrip.bookRide(mainApp.getCurrUserProfile().getCurrUser());
			availTripTable.refresh();
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
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}
}
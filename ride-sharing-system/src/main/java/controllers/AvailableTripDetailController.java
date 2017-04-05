/*
 * bja90
 * 46376139
 */
package controllers;

import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.MainApp;
import src.StopTuple;
import src.model.StopPoint;
import src.model.Trip;


// TODO: Auto-generated Javadoc
/**
 * The Class AvailableTripDetailController.
 */
public class AvailableTripDetailController { // NO_UCD (use default)

	/** The driver name field. */
	@FXML
	private TextField driverNameField;

	/** The stop table. */
	@FXML
	private TableView<StopTuple<StopPoint, String>> stopTable; // NO_UCD (unused code)

	/** The stop column. */
	@FXML
	private TableColumn<StopTuple<StopPoint, String>, String> stopColumn;

	/** The time column. */
	@FXML
	private TableColumn<StopTuple<StopPoint, String>, String> timeColumn;

	/** The car model field. */
	@FXML
	private TextField carModelField;

	/** The car year field. */
	@FXML
	private TextField carYearField;

	/** The car colour field. */
	@FXML
	private TextField carColourField;

	/** The car seats avail field. */
	@FXML
	private TextField carSeatsAvailField;

	/** The route length field. */
	@FXML
	private TextField routeLengthField;

	/** The dialog stage. */
	private Stage dialogStage;

	/** The main app. */
	private MainApp mainApp;

	/** The ok clicked. */
	private boolean okClicked = false;

	/** The trip view. */
	private Trip tripView;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		stopColumn.setCellValueFactory(cellData -> cellData.getValue().getStop().getAddressProperty());
		timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));
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
	 * Sets the.
	 *
	 * @param tripToView the trip to view
	 * @param mainApp the main app
	 */
	public void set(Trip tripToView, MainApp mainApp) {
		this.mainApp = mainApp;
		this.tripView = tripToView;
		driverNameField.setText(tripToView.getCreatingUser().getName());
		carModelField.setText(tripToView.getCar().getModel());
		carYearField.setText(tripToView.getCar().getCarYear().toString());
		carColourField.setText(tripToView.getCar().getCarColour());
		carSeatsAvailField.setText(tripToView.getAvailSeats().toString());
		Integer routeLength = new Integer(tripToView.getRoute().getStops().size());
		routeLengthField.setText(routeLength.toString());

		ObservableList<StopTuple<StopPoint, String>> observableStops = FXCollections.observableArrayList();
		for (Map.Entry<StopPoint, String> entry : tripToView.getStops().entrySet())
		{
			StopPoint key = entry.getKey();
			String value = entry.getValue();
			observableStops.add(new StopTuple<StopPoint, String>(key, value));
		}

		stopTable.setItems(observableStops);
	}


	/**
	 * Checks if is ok clicked.
	 *
	 * @return true, if is ok clicked
	 */
	public boolean isOkClicked() {
		return okClicked ;
	}

	/**
	 * Handle book trip.
	 */
	@FXML
	private void handleBookTrip() {
		tripView.bookRide(mainApp.getCurrUserProfile().getCurrUser());
		okClicked = true;
		dialogStage.close();
	}

	/**
	 * Handle close.
	 */
	@FXML
	private void handleClose() {
		dialogStage.close();
	}


}








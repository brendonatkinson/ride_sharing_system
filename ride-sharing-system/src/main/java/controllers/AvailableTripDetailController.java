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


public class AvailableTripDetailController { // NO_UCD (use default)

	@FXML
	private TextField driverNameField;
	@FXML
	private TableView<StopTuple<StopPoint, String>> stopTable; // NO_UCD (unused code)
	@FXML
	private TableColumn<StopTuple<StopPoint, String>, String> stopColumn;
	@FXML
	private TableColumn<StopTuple<StopPoint, String>, String> timeColumn;
	@FXML
	private TextField carModelField;
	@FXML
	private TextField carYearField;
	@FXML
	private TextField carColourField;
	@FXML
	private TextField carSeatsAvailField;
	@FXML
	private TextField routeLengthField;
	
	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;
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
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * @param mainApp 
	 * 
	 * @param person
	 */
	public void set(Trip tripToView, MainApp mainApp) {
		this.mainApp = mainApp;
		this.tripView = tripToView;
		driverNameField.setText(tripToView.getCreatingUser().getName());
		carModelField.setText(tripToView.getCar().getModel());
		carYearField.setText(tripToView.getCar().getCarYear().toString());
		carColourField.setText(tripToView.getCar().getCarColour());
		carSeatsAvailField.setText(tripToView.getCar().getAvailSeats().toString());
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
		//nameField.setText(route.getNameProperty().get());
	}

	 /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked ;
    }
	
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleBookTrip() {
		tripView.getCar().bookRide(mainApp.getCurrUserProfile().getCurrUser());
		okClicked = true;
		dialogStage.close();
	}
	
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleClose() {
		dialogStage.close();
	}

		
}








package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.MainApp;
import src.model.StopPoint;
import src.model.Trip;


public class AvailableTripDetailController { // NO_UCD (use default)

	@FXML
	private TextField driverNameField;
	@FXML
	private TableView<StopPoint> stopTable;
	@FXML
	private TableColumn<StopPoint, String> stopColumn;
	@FXML
	private TableColumn<String, String> timeColumn;
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
		//stopColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
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
	 * 
	 * @param person
	 */
	public void set(Trip tripToView) {
		this.tripView = tripToView;
		driverNameField.setText(tripToView.getCreatingUser().getName());
		carModelField.setText(tripToView.getCar().getModel());
		carYearField.setText(tripToView.getCar().getCarYear().toString());
		carColourField.setText(tripToView.getCar().getCarColour());
		carSeatsAvailField.setText(tripToView.getCar().getAvailSeats().toString());
		//routeLengthField.setText(tripToView.getCar().getModel());
		
		//stopTable.setItems(route.getStops());
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








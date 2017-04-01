package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import src.MainApp;
import src.model.Route;
import src.model.StopPoint;
import src.model.Trip;


public class TripEditController {

	@FXML
	private TableView<Map.Entry<StopPoint,String>> stopTable;
	@FXML
	private TableColumn<Map.Entry<StopPoint,String>, String> stopColumn;
	@FXML
	private TableColumn<Map.Entry<StopPoint,String>, String> timeColumn;
	@FXML
	private TextField recurrDays;
	@FXML
	private CheckBox sharedCheck;
	@FXML
	private CheckBox recurringCheck;
	@FXML
	private ChoiceBox<Route> selectedRoute;
	@FXML
	private ChoiceBox<String> carToUse;
	@FXML
	private DatePicker expiryDate;
	
	private Stage dialogStage;
	private Route route;
	private MainApp mainApp;
	private Trip tripToEdit;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		//stopColumn.setCellValueFactory(cellData -> cellData.getValue().getStop().getAddressProperty());
		//timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeProperty());
		//selectedRoute.setItems(mainApp.getCurrUserProfile().getUserRoutes());
		//if (tripToEdit.getRecurrency()){
		//	recurrDays.setText(tripToEdit.getRecurrDays());
		//}
		
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
	public void setTrip(Trip trip) {
		//this.tripToEdit = trip;
		//stopTable.setItems((ObservableList<Tuple>) trip.getStops());

		//nameField.setText(route.getNameProperty().get());
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		//route.setName(nameField.getText());
		dialogStage.close();
	}


	/**
	 * Validates the user input in the text fields.
	 * @param items 
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid(String items) {


		String errorMessage = "";

		if (!items.matches("\\d{1,5}[\\s\\w+]+")) {
			errorMessage += "Not a valid address!\n"; 
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

	@FXML
	private void handleAddStop() {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Add Stop");
		dialog.setHeaderText("Please Enter a Stop Point");
		dialog.setContentText("Please Enter a Stop Point:");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent() && isInputValid(result.get())){
			List<String> items = new ArrayList<String>(Arrays.asList(result.get().split(" ")));
			Integer number = Integer.parseInt(items.remove(0));
			String road =  String.join(" ", items);
			route.add(new StopPoint(number, road));
		}
	}


	/**
	 * Called when the user clicks on the delete button.
	 */
	/*@FXML
	private void handleDeleteStop() {
		int selectedIndex = stopTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			StopPoint stopToDel = stopTable.getItems().get(selectedIndex);
			route.removeStop(stopToDel);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Car Selected");
			alert.setContentText("Please select a car in the table.");

			alert.showAndWait();
		}
	}*/
}





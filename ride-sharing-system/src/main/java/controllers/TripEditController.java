package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import src.MainApp;
import src.model.Route;
import src.model.StopPoint;


public class TripEditController {

	@FXML
	private TextField nameField;
	@FXML
	private TableView<StopPoint> stopTable;
	@FXML
	private TableColumn<StopPoint, String> stopColumn;
	private Stage dialogStage;
	private Route route;
	private MainApp mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		stopColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
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
	public void setRoute(Route route) {
		this.route = route;
		stopTable.setItems(route.getStops());

		nameField.setText(route.getNameProperty().get());
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		route.setName(nameField.getText());
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
	@FXML
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
	}
}





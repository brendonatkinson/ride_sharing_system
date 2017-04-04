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


// TODO: Auto-generated Javadoc
/**
 * The Class RouteEditController.
 */
public class RouteEditController { // NO_UCD (use default)

	/** The name field. */
 @FXML
	private TextField nameField;
	
	/** The stop table. */
	@FXML
	private TableView<StopPoint> stopTable;
	
	/** The stop column. */
	@FXML
	private TableColumn<StopPoint, String> stopColumn;
	
	/** The dialog stage. */
	private Stage dialogStage;
	
	/** The route. */
	private Route route;
	
	/** The main app. */
	private MainApp mainApp;
	
	/** The ok clicked. */
	private boolean okClicked = false;

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
	 * @param dialogStage the new dialog stage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 *
	 * @param route the new route
	 */
	public void setRoute(Route route) {
		this.route = route;
		stopTable.setItems(route.getStops());
		nameField.setText(route.getNameProperty().get());
	}

	 /**
 	 * Returns true if the user clicked OK, false otherwise.
 	 *
 	 * @return true, if is ok clicked
 	 */
    public boolean isOkClicked() {
        return okClicked ;
    }
	
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (verifyFields()){
			route.setName(nameField.getText());
			okClicked = true;
			dialogStage.close();
		}
		
	}


	/**
	 * Verify fields.
	 *
	 * @return the boolean
	 */
	private Boolean verifyFields() {
		Boolean nameFlag = false;
		Boolean stopsFlag = false;
		if (nameField.getText() != null && !nameField.getText().trim().isEmpty()){
			nameFlag = true;
		} else{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("No Name");
		alert.setContentText("Please enter a Route name.");

		alert.showAndWait();
		}
		
		if (!stopTable.getItems().isEmpty()){
			stopsFlag = true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("No Stops");
			alert.setContentText("Please enter at least one stop.");

			alert.showAndWait();
		}
		return nameFlag & stopsFlag;
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @param items the items
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

	/**
	 * Handle add stop.
	 */
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
			alert.setHeaderText("No Stop Selected");
			alert.setContentText("Please select a stop to remove.");

			alert.showAndWait();
		}
	}
}





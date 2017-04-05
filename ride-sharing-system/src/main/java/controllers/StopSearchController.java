/*
 * bja90
 * 46376139
 */
package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.MainApp;
import src.model.StopPoint;


// TODO: Auto-generated Javadoc
/**
 * The Class RouteEditController.
 */
public class StopSearchController { // NO_UCD (use default)

	/** The search field. */
	@FXML
	private TextField searchField;

	/** The stop table. */
	@FXML
	private TableView<StopPoint> stopTable;

	/** The stop column. */
	@FXML
	private TableColumn<StopPoint, String> stopColumn;

	/** The dialog stage. */
	private Stage dialogStage;

	/** The ok clicked. */
	private boolean okClicked = false;

	/** The stop. */
	private StopPoint stop;

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
	 * @param stop the new stop
	 */
	public void setStop(StopPoint stop) {
		this.stop = stop;
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
		StopPoint selectedStop = stopTable.getSelectionModel().getSelectedItem();
		if (selectedStop != null) {
			stop = selectedStop;
			okClicked = true;
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Stop Selected");
			alert.setContentText("Please select a stop in the table.");
			alert.showAndWait();
		}

		dialogStage.close();

	}

	/**
	 * Called when the user clicks on the search button.
	 */
	@FXML
	private void handleDoSearch() {
		ObservableList<StopPoint> foundStops = FXCollections.observableArrayList();
		if (!searchField.getText().isEmpty() || searchField.getText() != null){
			String searchString = searchField.getText();
			for (StopPoint stop: MainApp.getStopPoints()){
				if (stop.getAddress().contains(searchString)){
					foundStops.add(stop);
				}
			}
			if (foundStops.size() != 0){
				stopTable.setItems(foundStops);
			} else {
				foundStops.clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No Results");
				alert.setHeaderText("No Stop Points Found");
				alert.setContentText("Your search yielded 0 results.");
				alert.showAndWait();
			}
		} else {
			foundStops.clear();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Search field empty");
			alert.setHeaderText("Search field empty or invalid");
			alert.setContentText("Please enter a valid search term.");
			alert.showAndWait();
		}


	}

	/**
	 * Gets the stop.
	 *
	 * @return the stop
	 */
	public StopPoint getStop() {
		return stop;
	}
}





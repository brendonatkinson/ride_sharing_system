package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import javafx.util.StringConverter;
import src.model.Car;
import src.model.Profile;
import src.model.Route;
import src.model.StopPoint;
import src.model.Trip;


public class TripEditController {

	@FXML
	private TableView<StopTuple<StopPoint, String>> stopTable;
	@FXML
	private TableColumn<StopTuple<StopPoint, String>, String> stopColumn;
	@FXML
	private TableColumn<StopTuple<StopPoint, String>, String> timeColumn;
	@FXML
	private TextField recurrDays;
	@FXML
	private CheckBox sharedCheck;
	@FXML
	private CheckBox recurringCheck;
	@FXML
	private ChoiceBox<Route> selectedRoute;
	@FXML
	private ChoiceBox<Car> carToUse;
	@FXML
	private DatePicker expiryDate;
	
	private Stage dialogStage;
	private Route route;
	private Trip tripToEdit;
	private Profile currUser;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		//System.out.println(tripToEdit);
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
	 * 
	 * @param person
	 */
	public void setTrip(Trip trip, Profile currUser) {
		this.currUser = currUser;
		this.tripToEdit = trip;
		ObservableList<StopTuple<StopPoint, String>> observableStops = FXCollections.observableArrayList();
		
		for (Map.Entry<StopPoint, String> entry : this.tripToEdit.getStops().entrySet())
		{
		  StopPoint key = entry.getKey();
		  String value = entry.getValue();
		  observableStops.add(new StopTuple<StopPoint, String>(key, value));
		}

		stopTable.setItems((ObservableList<StopTuple<StopPoint, String>>)observableStops);
		if (tripToEdit.getRecurrency()){
			recurrDays.setText(tripToEdit.getRecurrDays());
		}
		if (tripToEdit.getTripShared()){
			sharedCheck.setSelected(tripToEdit.getTripShared());
		}
		if (tripToEdit.getRoute() != null){
		selectedRoute.setValue(tripToEdit.getRoute());}
		selectedRoute.setItems(currUser.getUserRoutes());
		selectedRoute.setConverter(new StringConverter<Route>() {
            @Override
            public String toString(Route route) {
              if (route== null){
                return null;
              } else {
                return route.getNameProperty().get();
              }
            }

			@Override
			public Route fromString(String string) {
				return null;
			}

    	});
    	
    	/*// Handle Direction event.
    	dirChooser.setOnAction((event) -> {
    	    direction = dirChooser.getSelectionModel().getSelectedItem();
    	    populateTable();
    		})*/
		if (tripToEdit.getCar() != null){
			carToUse.setValue(tripToEdit.getCar());
		}
		carToUse.setItems(currUser.getCurrUser().getCars());
		carToUse.setConverter(new StringConverter<Car>() {
            @Override
            public String toString(Car car) {
              if (car == null){
                return null;
              } else {
                return car.toString();
              }
            }

			@Override
			public Car fromString(String string) {
				return null;
			}

    	});

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

class StopTuple<X, Y> { 
	  private StopPoint stop; 
	  private String time; 
	  public StopTuple(StopPoint stop, String time) { 
	    this.stop = stop; 
	    this.time = time; 
	  } 
	  public StopPoint getStop(){
		  return stop;
	  }
	  public String getTime(){
		  return time;
	  }
	} 




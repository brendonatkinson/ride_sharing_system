package controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import javafx.stage.Stage;
import javafx.util.StringConverter;
import src.StopTuple;
import src.model.Car;
import src.model.Profile;
import src.model.Route;
import src.model.StopPoint;
import src.model.Trip;


public class TripEditController {

	@FXML
	private TableView<StopTuple<StopPoint, String>> stopTable; // NO_UCD (unused code)
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
	private ChoiceBox<String> dirChooser;
	@FXML
	private ChoiceBox<Car> carToUse;
	@FXML
	private ChoiceBox<Integer> numAvailSeats;
	@FXML
	private DatePicker expiryDate;

	private Stage dialogStage;
	private Trip tripToEdit;
	@SuppressWarnings("unused")
	private boolean okClicked;
	private ObservableList<StopTuple<StopPoint, String>> observableStops;

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
		this.tripToEdit = trip;
		if (tripToEdit.getRoute() != null){
			populateTable();
		}

		if (tripToEdit.getRecurrency()){
			recurrDays.setText(tripToEdit.getRecurrDays());
		}
		if (tripToEdit.getTripShared()){
			sharedCheck.setSelected(tripToEdit.getTripShared());
		}
		if (tripToEdit.getRoute() != null){
			selectedRoute.setValue(tripToEdit.getRoute());}
		dirChooser.setItems(FXCollections.observableArrayList("To UC", "From UC"));
		dirChooser.setValue(tripToEdit.getTripDirection().get());
		recurringCheck.setOnAction((event) -> {
			recurrDays.setDisable(false);
			expiryDate.setDisable(false);	
		});

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

		// Handle Direction event.
		selectedRoute.setOnAction((event) -> {
			Route newRoute = selectedRoute.getSelectionModel().getSelectedItem();
			this.tripToEdit.setRoute(newRoute);
			this.tripToEdit.getStops().clear();
			populateTable();
		});

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

		carToUse.setOnAction((event) -> {
			//Integer numSeats = carToUse.getSelectionModel().getSelectedItem().getNumSeats();
			//System.out.println(numSeats);
			List<Integer> range = IntStream.range(0, 5).boxed().collect(Collectors.toList());
			ObservableList<Integer> observedNums = FXCollections.observableArrayList(range);
			numAvailSeats.setItems(observedNums);	
		});

		if (tripToEdit.getCar() != null){
			carToUse.setValue(tripToEdit.getCar());
			List<Integer> range = IntStream.range(0, tripToEdit.getCar().getNumSeats()).boxed().collect(Collectors.toList());
			ObservableList<Integer> observedNums =FXCollections.observableArrayList(range);
			numAvailSeats.setItems(observedNums);
		}

	}

	private void populateTable() {
		observableStops = FXCollections.observableArrayList();

		if (!this.tripToEdit.getStops().isEmpty()){
			for (Map.Entry<StopPoint, String> entry : this.tripToEdit.getStops().entrySet())
			{
				StopPoint key = entry.getKey();
				String value = entry.getValue();
				observableStops.add(new StopTuple<StopPoint, String>(key, value));
			}
		} else {
			for (StopPoint stop: this.tripToEdit.getRoute().getStops()) {
				observableStops.add(new StopTuple<StopPoint, String>(stop, ""));
			}

		}

		stopTable.setItems((ObservableList<StopTuple<StopPoint, String>>)observableStops);

	}

	@FXML
	private void handleAddTime() {
		StopTuple<StopPoint, String> selectedStop = stopTable.getSelectionModel().getSelectedItem();
		TextInputDialog dialog = new TextInputDialog(selectedStop.getTime());
		dialog.setTitle("Add Stopping Time");
		dialog.setHeaderText("Please Enter a Stop Point");
		dialog.setContentText("Please Enter time to stop at: " + selectedStop.getStop().getAddress());

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()){
			selectedStop.setTime(result.get());
		}
		stopTable.refresh();
	}

	@FXML
	private void handleOk() {
		if (isInputValid()){
			tripToEdit.setTripShared(sharedCheck.isSelected());

			tripToEdit.setCar(carToUse.getSelectionModel().getSelectedItem());
			if (!recurrDays.getText().isEmpty()){
				tripToEdit.setRecurrDays(recurrDays.getText());
			}
			tripToEdit.setExpiryDate(expiryDate.getValue());
			tripToEdit.setRoute(selectedRoute.getSelectionModel().getSelectedItem());
			tripToEdit.setRecurrency(recurringCheck.isSelected());
			tripToEdit.setTripDirection(dirChooser.getSelectionModel().getSelectedItem());
			tripToEdit.getCar().setAvailSeats(numAvailSeats.getValue());

			for (StopTuple<StopPoint, String> stops: observableStops){
				if (!tripToEdit.getStops().containsValue(stops.getStop())){
					tripToEdit.addStop(stops.getStop(), stops.getTime());
				}
			}

			okClicked = true;
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Trip creation success");
			alert.setContentText("The trip was successfully created.");

			alert.showAndWait();
			dialogStage.close();
		}

	}
	/**
	 * Validates the user input in the text fields.
	 * @param items 
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		if (observableStops != null){
			for (StopTuple<StopPoint, String> stops: observableStops){
				if (stops.getTime().equals("")){
					errorMessage += "All Stops need an arrival time!\n";
				}
			}
		}

		if (recurringCheck.isSelected()){
			if (expiryDate.getValue() == null || recurrDays.getText().equals("")){
				errorMessage += "Recurrance date and days is required!\n";
			}
		}

		if (selectedRoute.getSelectionModel().getSelectedItem() == null){
			errorMessage += "A route needs to be selected!\n";
		}

		if (dirChooser.getSelectionModel().getSelectedItem() == null){
			errorMessage += "A direction needs to be selected!\n";
		}
		if (carToUse.getSelectionModel().getSelectedItem() == null){
			errorMessage += "A car needs to be selected!\n";
		}
		if (numAvailSeats.getSelectionModel().getSelectedItem() == null){
			errorMessage += "A number of seats need to be selected!\n";
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

}
package controllers;

import src.MainApp;
import src.model.StopPoint;
import src.model.Trip;
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


public class AvailableTripController { // NO_UCD (use default)
    @FXML
    private TableView<Trip> availTripTable;
    @FXML
    private TableColumn<Trip, String> dayColumn;
    @FXML
    private TableColumn<Trip, String> timeColumn;
    @FXML
    private TableColumn<Trip, Integer> seatsColumn;
    @FXML
    private TableColumn<Trip, String> userColumn;
    @FXML
    private ChoiceBox<String> dirChooser;
    @FXML
    private ChoiceBox<StopPoint> stopChooser;
    
    private String direction = null;
    private StopPoint selectedStop = null;

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
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStops().get(selectedStop)));
        seatsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCar().getAvailSeats()).asObject());
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
    	    direction = dirChooser.getSelectionModel().getSelectedItem();
    	    populateTable();
    		});
    	
    	stopChooser.setOnAction((event) -> {
    	    selectedStop = stopChooser.getSelectionModel().getSelectedItem();
    	    populateTable();
    	});
    }


    private void populateTable() {
		if (direction != null && selectedStop != null){
			ObservableList<Trip> filteredTrips = FXCollections.observableArrayList();
			for (Trip trip: MainApp.getCurrTrips()){
				if (trip.getTripDirection().get().equals(direction) && trip.getStops().containsKey(selectedStop) && trip.getCar().getAvailSeats() > 0){
					filteredTrips.add(trip);
				}
			}
			availTripTable.setItems(filteredTrips);
			availTripTable.refresh();
		}
		
		
	}

	/**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleViewTrip() {
        Trip selectedTrip = availTripTable.getSelectionModel().getSelectedItem();
        if (selectedTrip != null) {
        	mainApp.mainHelper.showTripDetails(selectedTrip);

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Route Selected");
            alert.setContentText("Please select a route in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //currUser = mainApp.getCurrUserProfile();
        //availTripTable.setItems(currUser.getUserRoutes());
    }
}
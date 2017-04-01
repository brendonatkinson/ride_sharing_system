package controllers;

import src.MainApp;
import src.model.Profile;
import src.model.Trip;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TripController {
    @FXML
    private TableView<Trip> tripTable;
    @FXML
    private TableColumn<Trip, Boolean> sharedColumn;
    @FXML
    private TableColumn<Trip, String> routeNameColumn;
    @FXML
    private TableColumn<Trip, String> directionColumn;
    @FXML
    private TableColumn<Trip, String> carColumn;
    @FXML
    private TableColumn<Trip, Boolean> recurringColumn;


    // Reference to the main application.
    @SuppressWarnings("unused")
	private MainApp mainApp;
	private Profile currUser;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TripController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	sharedColumn.setCellValueFactory(cellData -> cellData.getValue().getTripSharedProperty());
        routeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getRoute().getNameProperty());
        directionColumn.setCellValueFactory(cellData -> cellData.getValue().getTripDirection());
        carColumn.setCellValueFactory(cellData -> cellData.getValue().getRideToBeUsed());
        recurringColumn.setCellValueFactory(cellData -> cellData.getValue().getRecurrencyProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

     // Add observable list data to the table
        currUser = mainApp.getCurrUserProfile();
        System.out.println(currUser.getTrips().get(0));
        System.out.println(currUser.getTrips().get(0).getRecurrency());
        System.out.println(currUser.getTrips().get(0).getRideToBeUsed().get());
        System.out.println(currUser.getTrips().get(0));
        tripTable.setItems(currUser.getTrips());
    }
}
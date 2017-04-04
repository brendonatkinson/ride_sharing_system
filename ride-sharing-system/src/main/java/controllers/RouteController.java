package controllers;

import src.MainApp;
import src.model.Profile;
import src.model.Route;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;


public class RouteController { // NO_UCD (use default)
    @FXML
    private TableView<Route> routeTable;
    @FXML
    private TableColumn<Route, String> nameColumn;
    @FXML
    private TableColumn<Route, String> stopColumn;
    
    private Profile currUser;

    // Reference to the main application.
	private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RouteController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        stopColumn.setCellValueFactory(cellData -> cellData.getValue().getStopsString());
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteRoute() {
        int selectedIndex = routeTable.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
        	Route routeToDel = routeTable.getItems().get(selectedIndex);
        	mainApp.getCurrUserProfile().removeRoute(routeToDel);
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
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewRoute() {
        Route tempRoute = new Route("");
        boolean okClicked = mainApp.mainHelper.showRouteEditDialog(tempRoute);
        if (okClicked) {
            currUser.addRoute(tempRoute);
            }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditRoute() {
        Route selectedRoute = routeTable.getSelectionModel().getSelectedItem();
        if (selectedRoute != null) {
        	mainApp.mainHelper.showRouteEditDialog(selectedRoute);
            routeTable.refresh();
        	

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
        currUser = mainApp.getCurrUserProfile();
        routeTable.setItems(currUser.getUserRoutes());
    }
}
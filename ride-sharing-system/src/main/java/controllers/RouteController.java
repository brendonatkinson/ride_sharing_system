/*
 * bja90
 * 46376139
 */
package controllers;

import src.MainApp;
import src.model.Profile;
import src.model.Route;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;


// TODO: Auto-generated Javadoc
/**
 * The Class RouteController.
 */
public class RouteController { /** The route table. */
	// NO_UCD (use default)
	@FXML
	private TableView<Route> routeTable;

	/** The name column. */
	@FXML
	private TableColumn<Route, String> nameColumn;

	/** The stop column. */
	@FXML
	private TableColumn<Route, String> stopColumn;

	/** The curr user. */
	private Profile currUser;

	/** The main app. */
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * Instantiates a new route controller.
	 */
	public RouteController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		stopColumn.setCellValueFactory(cellData -> cellData.getValue().getStopsString());
	}


	/**
	 * Handle delete route.
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
	 * Handle new route.
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
	 * Handle edit route.
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
	 * @param mainApp the new main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		currUser = mainApp.getCurrUserProfile();
		routeTable.setItems(currUser.getUserRoutes());
	}
}
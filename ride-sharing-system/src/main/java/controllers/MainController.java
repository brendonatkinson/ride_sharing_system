/*
 * bja90
 * 46376139
 */
package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import src.MainApp;
import src.model.Car;
import src.model.Route;
import src.model.StopPoint;
import src.model.Trip;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
public class MainController {

	/** The main app. */
	private MainApp mainApp;

	/** The dialog stage. */
	private Stage dialogStage;

	/** The primary stage. */
	private Window primaryStage;

	/**
	 * Show car edit dialog.
	 *
	 * @param car the car
	 * @return true, if successful
	 */
	public boolean showCarEditDialog(Car car) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controllers/CarEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Car");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			CarEditController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCar(car);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Show route edit dialog.
	 *
	 * @param selectedRoute the selected route
	 * @return true, if successful
	 */
	public boolean showRouteEditDialog(Route selectedRoute) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controllers/RouteEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			dialogStage = new Stage();
			dialogStage.setTitle("Edit Route");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			RouteEditController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setRoute(selectedRoute);
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Show trip details.
	 *
	 * @param selectedTrip the selected trip
	 * @return true, if successful
	 */
	public boolean showTripDetails(Trip selectedTrip) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controllers/AvailableTripDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			dialogStage = new Stage();
			dialogStage.setTitle("Trip Details");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			AvailableTripDetailController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.set(selectedTrip, mainApp);
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Show trip edit dialog.
	 *
	 * @param selectedTrip the selected trip
	 * @return true, if successful
	 */
	public boolean showTripEditDialog(Trip selectedTrip) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controllers/TripEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			dialogStage = new Stage();
			dialogStage.setTitle("Edit Trip");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			TripEditController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTrip(selectedTrip, mainApp.getCurrUserProfile());
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Show user overview.
	 *
	 * @return the titled pane
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TitledPane showUserOverview() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/controllers/UserOverview.fxml"));
		AnchorPane userOverview = (AnchorPane) loader.load();
		final TitledPane tp = new TitledPane("User Details", userOverview);

		// Give the controller access to the main app.
		UserOverviewController controller = loader.getController();
		controller.setMainApp(mainApp);

		return tp;
	}

	/**
	 * Show route overview.
	 *
	 * @return the titled pane
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TitledPane showRouteOverview() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/controllers/RouteOverview.fxml"));
		AnchorPane routeOverview = (AnchorPane) loader.load();
		final TitledPane tp = new TitledPane("My Routes", routeOverview);

		// Give the controller access to the main app.
		RouteController controller = loader.getController();
		controller.setMainApp(mainApp);
		return tp;

	}

	/**
	 * Show trip overview.
	 *
	 * @return the titled pane
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TitledPane showTripOverview() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/controllers/TripOverview.fxml"));
		AnchorPane tripOverview = (AnchorPane) loader.load();
		final TitledPane tp = new TitledPane("My Trips", tripOverview);

		// Give the controller access to the main app.
		TripController controller = loader.getController();
		controller.setMainApp(this.mainApp);
		return tp;
	}

	/**
	 * Show avail trip overview.
	 *
	 * @return the titled pane
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public TitledPane showAvailTripOverview() throws IOException {
		// Load person overview.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/controllers/AvailableTripOverview.fxml"));
		AnchorPane availTripOverview = (AnchorPane) loader.load();
		final TitledPane tp = new TitledPane("Available Trips", availTripOverview);

		// Give the controller access to the main app.
		AvailableTripController controller = loader.getController();
		controller.setMainApp(this.mainApp);
		return tp;

	}


	/**
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	/**
	 * Show stop point search.
	 *
	 * @param selectedStop the selected stop
	 * @return the stop point
	 */
	public StopPoint showStopPointSearch(StopPoint selectedStop) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controllers/StopSearchDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Car");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			StopSearchController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStop(selectedStop);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.getStop();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;


	}
}
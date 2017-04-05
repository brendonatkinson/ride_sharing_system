/*
 * bja90
 * 46376139
 */
package src;

import src.model.Profile;
import src.model.RideOverview;
import src.model.User;
import src.model.StopPoint;
import src.model.Trip;
import java.io.IOException;
import controllers.MainController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class MainApp.
 */
public class MainApp extends Application {

	/** The primary stage. */
	private Stage primaryStage;

	/** The root layout. */
	private BorderPane rootLayout;

	/** The layout accord. */
	private Accordion layoutAccord = new Accordion();

	/** The curr user. */
	private Profile currUser;

	/** The main helper. */
	public MainController mainHelper = new MainController();

	/** The Ride and Stop Containers */
	private static RideOverview rideContainer = new RideOverview();

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("UC RSS");

		initRootLayout();
		mainHelper.setMainApp(this);
		try {
			layoutAccord.getPanes().add(mainHelper.showUserOverview());
			layoutAccord.getPanes().add(mainHelper.showRouteOverview());
			layoutAccord.getPanes().add(mainHelper.showTripOverview());
			layoutAccord.getPanes().add(mainHelper.showAvailTripOverview());
		} catch (IOException e) {
			e.printStackTrace();
		}
		rootLayout.setCenter(layoutAccord);
	}


	/**
	 * Constructor.
	 */
	public MainApp() {
		User newUser = new User(null, false);
		currUser = new Profile(newUser);
	}

	/**
	 * Inits the root layout.
	 */
	public void initRootLayout() { // NO_UCD (use private)
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/controllers/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds the stop point.
	 *
	 * @param newStopPoint the new stop point
	 * @return the boolean
	 */
	public static Boolean addStopPoint(StopPoint newStop) {
		return rideContainer.addStop(newStop);
	}

	/**
	 * Gets the stop points.
	 *
	 * @return the stop points
	 */
	public static ObservableList<StopPoint> getStopPoints() {
		return rideContainer.getStopList();
	}

	/**
	 * Gets the curr trips.
	 *
	 * @return the curr trips
	 */
	public static ObservableList<Trip> getCurrTrips() {
		return rideContainer.getTripList();
	}

	/**
	 * Gets the curr user profile.
	 *
	 * @return the curr user profile
	 */
	public Profile getCurrUserProfile() {
		return currUser;
	}

	/**
	 * Gets the primary stage.
	 *
	 * @return the primary stage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Removes the stop point.
	 *
	 * @param stopToDel the stop to del
	 */
	public static void removeStopPoint(StopPoint stopToDel) {
		rideContainer.removeStop(stopToDel);
	}

	/**
	 * Adds the trip.
	 *
	 * @param tempTrip the temp trip
	 */
	public static Boolean addTrip(Trip newTrip) {
		return rideContainer.addTrip(newTrip);
	}

}
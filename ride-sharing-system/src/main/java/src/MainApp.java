package src;

import src.model.Car;
import src.model.Profile;
import src.model.Route;
import src.model.User;
import src.model.StopPoint;
import src.model.Trip;

import java.io.IOException;

import controllers.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
    
    /** The stop point container. */
    private static ObservableList<StopPoint> stopPointContainer;
	
	/** The trip container. */
	private static ObservableList<Trip> tripContainer;
    
    /** The main helper. */
    public MainController mainHelper = new MainController();
   
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
        // Sample Data, can remove this
    	stopPointContainer = FXCollections.observableArrayList();
    	tripContainer = FXCollections.observableArrayList();
    	User testUser = new User("Bruno", false);
    	currUser = new Profile(testUser);
    	currUser.getCurrUser().addCar(new Car("Sedan", "Honda", "Blue", "FLZ111", 0, 5));
    	currUser.getCurrUser().addCar(new Car("PeopleMover", "Maserati", "Blue", "ABC123", 0, 5));
    	currUser.getCurrUser().addCar(new Car("Van", "Mercedes", "Blue", "BYM922", 0, 5));
    	StopPoint stop1 = new StopPoint(15, "Creyke Road");
    	StopPoint stop2 = new StopPoint(12, "Main St");
    	StopPoint stop3 = new StopPoint(999, "Grassmere St");
    	Route routed = new Route("New Route");
    	routed.add(stop1);
    	routed.add(stop2);
    	routed.add(stop3);
    	currUser.addRoute(routed);
    	//Trip newTrip = new Trip(testUser, currUser.getCurrUser().getCars().get(0), routed, "To UC", false);
    	//System.out.print(newTrip);
    	//newTrip.addStop(stop1, "11:00");
    	//newTrip.getCar().setAvailSeats(2);
    	//currUser.addTrip(newTrip);
    	//tripContainer.add(newTrip);
    
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
    public static Boolean addStopPoint(StopPoint newStopPoint) {
    	if (!stopPointContainer.contains(newStopPoint)){
    		stopPointContainer.add(newStopPoint);
    		return true;
    	}
		return false;
	}
    
    /**
     * Gets the stop points.
     *
     * @return the stop points
     */
    public static ObservableList<StopPoint> getStopPoints() {
		return stopPointContainer;
	}

	/**
	 * Gets the curr trips.
	 *
	 * @return the curr trips
	 */
	public static ObservableList<Trip> getCurrTrips() {
		return tripContainer;
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
		stopPointContainer.remove(stopToDel);
	}
	
	/**
	 * Adds the trip.
	 *
	 * @param tempTrip the temp trip
	 */
	public static void addTrip(Trip tempTrip) {
		tripContainer.add(tempTrip);
	}

}
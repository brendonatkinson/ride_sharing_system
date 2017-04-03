package src;

import java.io.IOException;
import src.model.Car;
import src.model.Profile;
import src.model.Route;
import src.model.User;
import src.model.StopPoint;
import src.model.Trip;
import controllers.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Accordion layoutAccord = new Accordion();
    private Profile currUser;
    private static ObservableList<StopPoint> stopPointContainer;
	private static ObservableList<Trip> tripContainer;
    public MainController mainHelper = new MainController();
   
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        rootLayout.setCenter(layoutAccord);
    }


    /**
     * Constructor
     */
    public MainApp() {
        // Sample Data, can remove this
    	stopPointContainer = FXCollections.observableArrayList();
    	tripContainer = FXCollections.observableArrayList();
    	User testUser = new User("Bruno", false);
    	currUser = new Profile(testUser);
    	Car honda = new Car("Sedan", "Honda", "Blue", "FLZ111", 0, 0);
    	currUser.getCurrUser().addCar(new Car("Sedan", "Honda", "Blue", "FLZ111", 0, 0));
    	currUser.getCurrUser().addCar(new Car("PeopleMover", "Maserati", "Blue", "ABC123", 0, 0));
    	currUser.getCurrUser().addCar(new Car("Van", "Mercedes", "Blue", "BYM922", 0, 0));
    	StopPoint stop1 = new StopPoint(15, "Creyke Road");
    	StopPoint stop2 = new StopPoint(12, "Main St");
    	StopPoint stop3 = new StopPoint(999, "Grassmere St");
    	Route routed = new Route("New Route");
    	routed.add(stop1);
    	routed.add(stop2);
    	routed.add(stop3);
    	currUser.addRoute(routed);
    	Trip newTrip = new Trip(testUser, honda, routed, false, false);
    	newTrip.getCar().setAvailSeats(2);
    	currUser.addTrip(newTrip);
    	tripContainer.add(newTrip);
    	
    }
    
    public void initRootLayout() {
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

    public static Boolean addStopPoint(StopPoint newStopPoint) {
		return stopPointContainer.add(newStopPoint);
	}
    
    public static ObservableList<StopPoint> getStopPoints() {
		return stopPointContainer;
	}

	public static ObservableList<Trip> getCurrTrips() {
		return tripContainer;
	}
    
	public Profile getCurrUserProfile() {
        return currUser;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

	public static void removeStopPoint(StopPoint stopToDel) {
		stopPointContainer.remove(stopToDel);
	}

}
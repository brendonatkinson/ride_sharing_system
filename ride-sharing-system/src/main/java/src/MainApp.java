package src;

import java.io.IOException;
import src.model.Car;
import src.model.Profile;
import src.model.Route;
import src.model.User;
import src.model.StopPoint;
import src.model.Trip;
import controllers.CarEditController;
import controllers.RouteController;
import controllers.RouteEditController;
import controllers.TripController;
import controllers.UserOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private Stage dialogStage;
    private BorderPane rootLayout;
    private Accordion layoutAccord = new Accordion();
    private Profile currUser;
    private static ObservableList<StopPoint> stopPointContainer;
   
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UC RSS");

        initRootLayout();

        showUserOverview();
        showTripOverview();
        showRouteOverview();
        
        rootLayout.setCenter(layoutAccord);
    }


    /**
     * Constructor
     */
    public MainApp() {
        // Sample Data, can remove this
    	stopPointContainer = FXCollections.observableArrayList();
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
    	currUser.addTrip(newTrip);
    	
    	//Try to read in saved user profile
    	//else create blank currUser = new User("", false);
    	
    	
    }

    public static Boolean addStopPoint(StopPoint newStopPoint) {
		return stopPointContainer.add(newStopPoint);
	}
    
    public static ObservableList<StopPoint> getStopPoints() {
		return stopPointContainer;
	}


	public Profile getCurrUserProfile() {
        return currUser;
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

    public void showUserOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/controllers/UserOverview.fxml"));
            AnchorPane userOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            final TitledPane tp = new TitledPane("User Details", userOverview);
            layoutAccord.getPanes().add(tp);
            //rootLayout.setCenter(layoutAccord);
            
         // Give the controller access to the main app.
            UserOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showRouteOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/controllers/RouteOverview.fxml"));
            AnchorPane routeOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            final TitledPane tp = new TitledPane("My Routes", routeOverview);
            layoutAccord.getPanes().add(tp);
            
         // Give the controller access to the main app.
            RouteController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTripOverview() {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/controllers/TripOverview.fxml"));
            AnchorPane tripOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            final TitledPane tp = new TitledPane("My Trips", tripOverview);
            layoutAccord.getPanes().add(tp);
            
         // Give the controller access to the main app.
            TripController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showCarEditDialog(Car car) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/controllers/CarEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Car");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }


	 public boolean showRouteEditDialog(Route selectedRoute) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/controllers/RouteEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            dialogStage = new Stage();
            dialogStage.setTitle("Edit Route");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RouteEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRoute(selectedRoute);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


	public static void removeStopPoint(StopPoint stopToDel) {
		stopPointContainer.remove(stopToDel);
	}
	 
}
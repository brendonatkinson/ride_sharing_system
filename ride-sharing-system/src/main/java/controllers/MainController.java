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
import src.model.Trip;

public class MainController {
	private MainApp mainApp;
	private Stage dialogStage;
	private Window primaryStage;

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
 
public boolean showTripEditDialog(Trip selectedTrip) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/controllers/TripEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            dialogStage = new Stage();
            dialogStage.setTitle("Edit Route");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TripEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTrip(selectedTrip, mainApp.getCurrUserProfile());

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

public TitledPane showUserOverview() throws IOException {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/controllers/UserOverview.fxml"));
        AnchorPane userOverview = (AnchorPane) loader.load();

        final TitledPane tp = new TitledPane("User Details", userOverview);
        
     // Give the controller access to the main app.
        UserOverviewController controller = loader.getController();
        controller.setMainApp(mainApp);
        
        return tp;
}

public TitledPane showRouteOverview() throws IOException {
   
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/controllers/RouteOverview.fxml"));
        AnchorPane routeOverview = (AnchorPane) loader.load();

        // Set person overview into the center of root layout.
        final TitledPane tp = new TitledPane("My Routes", routeOverview);
        
     // Give the controller access to the main app.
        RouteController controller = loader.getController();
        controller.setMainApp(mainApp);
        return tp;

}

public TitledPane showTripOverview() throws IOException {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/controllers/TripOverview.fxml"));
        AnchorPane tripOverview = (AnchorPane) loader.load();

        // Set person overview into the center of root layout.
        final TitledPane tp = new TitledPane("My Trips", tripOverview);
        
     // Give the controller access to the main app.
        TripController controller = loader.getController();
        controller.setMainApp(this.mainApp);
        return tp;
}

public TitledPane showAvailTripOverview() throws IOException {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/controllers/AvailableTripOverview.fxml"));
        AnchorPane availTripOverview = (AnchorPane) loader.load();

        // Set person overview into the center of root layout.
        final TitledPane tp = new TitledPane("Available Trips", availTripOverview);
        //layoutAccord.getPanes().add(tp);
        //rootLayout.setCenter(layoutAccord);
        
     // Give the controller access to the main app.
        AvailableTripController controller = loader.getController();
        controller.setMainApp(this.mainApp);
        return tp;

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

public void setMainApp(MainApp mainApp) {
	this.mainApp = mainApp;
	
}
}
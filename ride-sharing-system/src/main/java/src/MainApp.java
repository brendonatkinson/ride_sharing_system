/*
 * bja90
 * 46376139
 */
package src;

import src.model.DriversLicense;
import src.model.Profile;
import src.model.RideOverview;
import src.model.User;
import src.model.StopPoint;
import src.model.Trip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.hildan.fxgson.FxGson;

import controllers.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


// TODO: Auto-generated Javadoc
/**
 * The Class MainApp.
 */
public class MainApp extends Application {

	private static Gson gson;
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
	private static RideOverview rideContainer;

	private static HashMap<String, Profile> userAccounts;

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
			layoutAccord.getPanes().add(mainHelper.showRidesOverview());
		} catch (IOException e) {
			e.printStackTrace();
		}
		rootLayout.setCenter(layoutAccord);
	}


	/**
	 * Constructor.
	 */
	public MainApp() {
		
		initUser();

		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		builder.enableComplexMapKeySerialization();
		gson = FxGson.addFxSupport(builder).create();

		//setCurrUser(new Profile(new User("", "", "", 123, "", 0, 0, new DriversLicense("",0)),"test"));
		rideContainer = new RideOverview();
		//If file exists, load
		/**File ride = new File("src/main/resources/ridedata.json");
		File user = new File("src/main/resources/userdata.json");
		if (ride.exists() && user.exists())
		{
			try {
				currUser = load();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			currUser = new Profile(new User("", ""));
			rideContainer = new RideOverview();
		}
		if (currUser == null){
			System.out.print(":(");
		} else {
			System.out.println(currUser.getUserRoutes());
		} */
	}


	private void initUser() {
		// Try load User accounts
		//TODO
		
		userAccounts = new HashMap<String, Profile>();
		Profile testProfile = new Profile(new User("Hello", "Driver", "bob@uclive.ac.nz", 123, "5 A Street", 0, 0, new DriversLicense("",0)),"test");
		userAccounts.put(testProfile.getUserName(), testProfile);

		
		// Display splash, option to login or register
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog with Custom Actions");
		alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");
		alert.setContentText("Choose your option.");

		ButtonType btnLogin = new ButtonType("Login");
		ButtonType btnRegister = new ButtonType("Register");
		ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(btnLogin, btnRegister, btnClose);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == btnLogin){
			Boolean loginResult = false;
			while (!loginResult)
			{
				loginResult = showLoginDialog();
			}
			//showNotifications();
		} else if (result.get() == btnRegister) {
			Profile tempUser = new Profile(new User("", "", "", 0, "",
					0, 0, new DriversLicense("", 0)),"");
			boolean okClicked = mainHelper.showUserEditDialog(tempUser);
			if (okClicked) {
				userAccounts.put(tempUser.getUserName(), tempUser);
				setCurrUser(tempUser);
				//showNotifications();
			}
		} else {
			System.exit(0);
		}

	}


	private void showNotifications() {
		if (getCurrUserProfile().processNotifications()){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Notifications");
			alert.setHeaderText("Notifiation Window");
			alert.setContentText(getCurrUserProfile().getNotifications());
			Optional<ButtonType> result = alert.showAndWait();
		}
		
	}


	public static Profile load() throws UnsupportedEncodingException {
		rideContainer = new RideOverview();
		//Reader reader = new InputStreamReader(MainApp.class.getResourceAsStream("/ridedata.json"), "UTF-8");
		//rideContainer = gson.fromJson(reader, RideOverview.class);
		Reader reader = new InputStreamReader(MainApp.class.getResourceAsStream("/userdata.json"), "UTF-8");
		return gson.fromJson(reader, Profile.class);
	}

	public static void save(Profile userData) throws IOException {
		Writer writer = new OutputStreamWriter(new FileOutputStream("src/main/resources/ridedata.json"), "UTF-8");
		gson.toJson(rideContainer, writer);
		writer = new OutputStreamWriter(new FileOutputStream("src/main/resources/userdata.json"), "UTF-8");
		gson.toJson(userData, writer);
		writer.close();
	}

	private boolean showLoginDialog(){
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Look, a Custom Login Dialog");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a user name was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the user name field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a user name-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			} 
			return null;
		});
		
		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(usernamePassword -> {
			if (userAccounts.containsKey(usernamePassword.getKey())){
				Profile userAccount = userAccounts.get(usernamePassword.getKey());
				if (userAccount.login(usernamePassword.getValue())){
					setCurrUser(userAccount);	
				}
			}
		});

		if (getCurrUserProfile() == null){
			return false;
		} else {
			return true;
		}

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


	public void setCurrUser(Profile tempUser) {
		currUser = tempUser;
		
	}

}
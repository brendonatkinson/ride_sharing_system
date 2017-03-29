package controllers;

import src.MainApp;
import src.model.Car;
import src.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class UserOverviewController {
    @FXML
    private TableView<Car> carTable;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> platesColumn;
    @FXML
    private TextField userName;
    private User currUser;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public UserOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().getModelProperty());
        platesColumn.setCellValueFactory(cellData -> cellData.getValue().getLicensePlateProperty());
    }

    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteCar() {
        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
        	Car carToDel = carTable.getItems().get(selectedIndex);
            mainApp.getCurrUserProfile().getCurrUser().removeCar(carToDel);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Car Selected");
            alert.setContentText("Please select a car in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewCar() {
        Car tempCar = new Car("Sedan", "", "", "", 0, 0);
        boolean okClicked = mainApp.showCarEditDialog(tempCar);
        if (okClicked) {
            currUser.addCar(tempCar);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditCar() {
        Car selectedCar = carTable.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
        	mainApp.showCarEditDialog(selectedCar);

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

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
        currUser = mainApp.getCurrUserProfile().getCurrUser();
        carTable.setItems(currUser.getCars());
        userName.setText(currUser.getName());
    }
}
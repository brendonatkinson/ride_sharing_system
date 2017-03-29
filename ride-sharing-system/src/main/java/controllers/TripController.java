package controllers;

import src.MainApp;
import src.model.Car;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class TripController {
    @FXML
    private TableView<Car> carTable;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> platesColumn;
    @FXML
    private TextField userName;

    // Reference to the main application.
    @SuppressWarnings("unused")
	private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TripController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        //modelColumn.setCellValueFactory(cellData -> cellData.getValue().getModel());
        //platesColumn.setCellValueFactory(cellData -> cellData.getValue().getLicensePlate());
        //userName.setText("YOYO");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //carTable.setItems(mainApp.getCarData());
    }
}
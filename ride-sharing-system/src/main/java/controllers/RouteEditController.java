package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.model.Route;
import src.model.StopPoint;


public class RouteEditController {

    @FXML
    private TextField nameField;
    @FXML
    private TableView<StopPoint> stopTable;
    @FXML
    private TableColumn<StopPoint, String> stopColumn;



    private Stage dialogStage;
    private boolean okClicked = false;
	@SuppressWarnings("unused")
	private Route route;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	stopColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setRoute(Route route) {
        this.route = route;
        stopTable.setItems(route.getStops());

        nameField.setText(route.getNameProperty().get());
        /**typeField.setText(car.getCarType().toString());
        yearField.setText(car.getCarYear().toString());
        plateField.setText(car.getLicensePlate());
        colourField.setText(car.getCarColour());
        seatsField.setText(car.getNumSeats().toString());**/
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	/*car.setModel(modelField.getText());
        	car.setType(typeField.getText());
        	car.setColour(colourField.getText());
        	car.setNumSeats(Integer.parseInt(seatsField.getText()));
        	car.setPlate(plateField.getText());
        	car.setYear(Integer.parseInt(yearField.getText()));*/
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        /*
        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "No valid model!\n"; 
        }
        if (colourField.getText() == null || colourField.getText().length() == 0) {
            errorMessage += "No valid colour!\n"; 
        }
        if (plateField.getText() == null || plateField.getText().length() == 0) {
            errorMessage += "No valid number plates!\n"; 
        }
        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid type!\n"; 
        }
        if (seatsField.getText() == null || seatsField.getText().length() == 0) {
            errorMessage += "No valid seat number!\n"; 
        } else {
            // try to parse the seats into an int.
            try {
                Integer.parseInt(seatsField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid seat number (must be an integer)!\n"; 
            }
        }
        if (yearField.getText() == null || yearField.getText().length() == 0) {
            errorMessage += "No valid year!\n"; 
        } else {
            // try to parse the year into an int.
            try {
                Integer.parseInt(yearField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid year (must be an integer)!\n"; 
            }
        }*/

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
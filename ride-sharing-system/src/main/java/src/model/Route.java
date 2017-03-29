package src.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.MainApp;

public class Route {
	
	private StringProperty name;
	private ObservableList<StopPoint> stopPoints;

	public Route(String routename) {
		this.name = new SimpleStringProperty(routename);
		this.stopPoints = FXCollections.observableArrayList();
	}

	public void add(StopPoint stop) {
		MainApp.addStopPoint(stop);
		stopPoints.add(stop);
	}

	
	public ObservableList<StopPoint> getStops() {
        return stopPoints;
    }
	
	public StringProperty getStopsString() {
		String concat = "";
		for (StopPoint stop: stopPoints) {
			concat += stop.getAddress() + ", ";
			}
		return new SimpleStringProperty(concat);

	}
	public StringProperty getNameProperty() {
		return name;
	}
	
}

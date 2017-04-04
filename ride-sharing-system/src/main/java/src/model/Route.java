package src.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.MainApp;

// TODO: Auto-generated Javadoc
/**
 * The Class Route.
 */
public class Route {
	
	/** The name. */
	private StringProperty name;
	
	/** The stop points. */
	private ObservableList<StopPoint> stopPoints;

	/**
	 * Instantiates a new route.
	 *
	 * @param routename the routename
	 */
	public Route(String routename) {
		this.name = new SimpleStringProperty(routename);
		this.stopPoints = FXCollections.observableArrayList();
	}

	/**
	 * Adds the.
	 *
	 * @param stop the stop
	 */
	public void add(StopPoint stop) {
		MainApp.addStopPoint(stop);
		stopPoints.add(stop);
	}

	
	/**
	 * Gets the stops.
	 *
	 * @return the stops
	 */
	public ObservableList<StopPoint> getStops() {
        return stopPoints;
    }
	
	/**
	 * Gets the stops string.
	 *
	 * @return the stops string
	 */
	public StringProperty getStopsString() {
		String concat = "";
		for (StopPoint stop: stopPoints) {
			concat += stop.getAddress() + ", ";
			}
		return new SimpleStringProperty(concat);

	}
	
	/**
	 * Gets the name property.
	 *
	 * @return the name property
	 */
	public StringProperty getNameProperty() {
		return name;
	}

	/**
	 * Removes the stop.
	 *
	 * @param stopToDel the stop to del
	 */
	public void removeStop(StopPoint stopToDel) {
		this.stopPoints.remove(stopToDel);
		MainApp.removeStopPoint(stopToDel);
		
	}

	/**
	 * Sets the name.
	 *
	 * @param text the new name
	 */
	public void setName(String text) {
		this.name.set(text);
		
	}
	
}
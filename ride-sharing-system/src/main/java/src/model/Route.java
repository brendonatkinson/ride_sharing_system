package src.model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Route {
	
	private StringProperty name;
	private Collection<StopPoint> stopPoints;

	public Route(String routename) {
		this.name = new SimpleStringProperty(routename);
		this.stopPoints = new ArrayList<StopPoint>();
	}

	public void add(StopPoint stop) {
		this.stopPoints.add(stop);
	}

	
	public Collection<StopPoint> getStops() {
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

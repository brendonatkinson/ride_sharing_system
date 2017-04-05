/*
 * bja90
 * 46376139
 */
package src.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class StopPoint.
 */
public class StopPoint {

	/** The number. */
	private Integer number;

	/** The street. */
	private String street;

	/**
	 * Instantiates a new stop point.
	 *
	 * @param stopNumber the stop number
	 * @param stopAddress the stop address
	 */
	public StopPoint(int stopNumber, String stopAddress){
		this.number = stopNumber;
		this.street = stopAddress;
	}

	/**
	 * Gets the address property.
	 *
	 * @return the number
	 */
	public StringProperty getAddressProperty() {
		return new SimpleStringProperty(this.number + " " + this.street);
	}

	/**
	 * Gets the address.
	 *
	 * @return the number
	 */
	public String getAddress() {
		return (this.number + " " + this.street);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StopPoint other = (StopPoint) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}


}

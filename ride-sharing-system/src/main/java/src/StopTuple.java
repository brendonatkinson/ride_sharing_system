/*
 * bja90
 * 46376139
 */
package src;

import src.model.StopPoint;

// TODO: Auto-generated Javadoc
/**
 * The Class StopTuple.
 *
 * @param <X> the generic type
 * @param <Y> the generic type
 */
public class StopTuple<X, Y> { 

	/** The stop. */
	private StopPoint stop; 

	/** The time. */
	private String time; 

	/**
	 * Instantiates a new stop tuple.
	 *
	 * @param stop the stop
	 * @param time the time
	 */
	public StopTuple(StopPoint stop, String time) { 
		this.stop = stop; 
		this.time = time; 
	} 

	/**
	 * Gets the stop.
	 *
	 * @return the stop
	 */
	public StopPoint getStop(){
		return stop;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime(){
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time){
		this.time = time;
	}
} 
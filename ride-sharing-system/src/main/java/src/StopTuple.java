package src;

import src.model.StopPoint;

public class StopTuple<X, Y> { 
	private StopPoint stop; 
	private String time; 
	public StopTuple(StopPoint stop, String time) { 
		this.stop = stop; 
		this.time = time; 
	} 
	public StopPoint getStop(){
		return stop;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time = time;
	}
} 
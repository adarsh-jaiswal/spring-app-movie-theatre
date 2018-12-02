package com.adarsh.udaan.vo;

import java.util.Set;

public class SeatRowInfoRequestVO {
	private int numberOfSeats;
	private Set<Integer> aisleSeats;
	
	
	public SeatRowInfoRequestVO() {
		super();
	}
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Set<Integer> getAisleSeats() {
		return aisleSeats;
	}

	public void setAisleSeats(Set<Integer> aisleSeats) {
		this.aisleSeats = aisleSeats;
	}

}

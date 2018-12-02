package com.adarsh.udaan.vo;

import java.util.Map;
import java.util.Set;

public class SeatRequestVO {
	private Map<String, Set<Integer>> seats;

	public Map<String, Set<Integer>> getSeats() {
		return seats;
	}

	public void setSeats(Map<String, Set<Integer>> seats) {
		this.seats = seats;
	}
	
}

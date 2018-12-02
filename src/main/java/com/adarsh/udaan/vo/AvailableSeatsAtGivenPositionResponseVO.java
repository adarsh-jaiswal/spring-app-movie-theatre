package com.adarsh.udaan.vo;

import java.util.Map;
import java.util.Set;

public class AvailableSeatsAtGivenPositionResponseVO {
	private Map<String, Set<Integer>> availableSeats;

	public Map<String, Set<Integer>> getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Map<String, Set<Integer>> availableSeats) {
		this.availableSeats = availableSeats;
	}
}

package com.adarsh.udaan.vo;

import java.util.Map;

public class ScreenRequestVO {
	private String name;
	private Map<String, SeatRowInfoRequestVO> seatInfo;
	
	public ScreenRequestVO() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Map<String, SeatRowInfoRequestVO> getSeatInfo() {
		return seatInfo;
	}

	public void setSeatInfo(Map<String, SeatRowInfoRequestVO> seatInfo) {
		this.seatInfo = seatInfo;
	}
	
}

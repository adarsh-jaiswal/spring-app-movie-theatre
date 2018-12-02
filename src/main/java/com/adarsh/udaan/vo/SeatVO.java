package com.adarsh.udaan.vo;

import com.adarsh.udaan.entity.Seats;
import com.adarsh.udaan.entity.SeatsPk;


public class SeatVO {
	private String rowName;
	private int rowNumber;
	private String screenName;
	private boolean isAisle;
	private boolean isBooked;
	
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public boolean isAisle() {
		return isAisle;
	}
	public void setAisle(boolean isAisle) {
		this.isAisle = isAisle;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	public Seats convertToEntity() {
		Seats entity = new Seats();
		SeatsPk pk = new SeatsPk();
		entity.setAisle(this.isAisle);
		entity.setBooked(this.isBooked);
		pk.setRowName(this.rowName);
		pk.setRowNumber(this.rowNumber);
		pk.setScreenName(this.screenName);
		entity.setSeatsPk(pk);
		return entity;
	}
	
}

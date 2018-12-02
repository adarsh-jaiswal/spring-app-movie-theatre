package com.adarsh.udaan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SeatsPk implements Serializable {

	private static final long serialVersionUID = 1L;
	private String rowName;
	private int rowNumber;
	private String screenName;
	
	@Column(name = "ROW_NAME")
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	@Column(name = "ROW_NUM")
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	@Column(name = "SCREEN_NAME")
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

}

package com.adarsh.udaan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.adarsh.udaan.vo.SeatVO;

@Entity
@Table(name = "SEATS")
public class Seats {
	private SeatsPk seatsPk;
	private boolean isAisle;
	private boolean isBooked;
	
	public Seats() {
		super();
	}
	
	@Id
	public SeatsPk getSeatsPk() {
		return seatsPk;
	}
	public void setSeatsPk(SeatsPk seatsPk) {
		this.seatsPk = seatsPk;
	}
	@Column(name = "AISLE")
	public boolean isAisle() {
		return isAisle;
	}
	public void setAisle(boolean isAisle) {
		this.isAisle = isAisle;
	}
	@Column(name = "BOOKED")
	public boolean isBooked() {
		return isBooked;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	public SeatVO convertToVO() {
		SeatVO vo = new SeatVO();
		vo.setAisle(this.isAisle);
		vo.setBooked(this.isBooked);
		vo.setRowName(this.seatsPk.getRowName());
		vo.setRowNumber(this.seatsPk.getRowNumber());
		vo.setScreenName(this.seatsPk.getScreenName());
		return vo;
	}
	
}

package com.adarsh.udaan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCREENS")
public class Screen {
	
	private String name;
	private int totalSeats;
	
	@Id
	@Column(name = "SCREEN_NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "TOTAL_SEATS")
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
}

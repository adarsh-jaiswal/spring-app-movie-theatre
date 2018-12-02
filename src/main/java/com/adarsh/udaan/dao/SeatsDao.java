package com.adarsh.udaan.dao;

import java.util.List;

import com.adarsh.udaan.entity.Seats;
import com.adarsh.udaan.vo.SeatVO;

public interface SeatsDao {
	public boolean saveSeat(Seats seat);
	public void updateSeat(Seats seat);
	public List<SeatVO> getAvailableSeats(String screenName, String status);
	public List<SeatVO> getAvailableSeatsAtGivenPosition(String screenName, int numberOfSeats, String rowName, int rowNumber);
}

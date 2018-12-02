package com.adarsh.udaan.service;

import java.util.List;

import com.adarsh.udaan.vo.AvailableSeatsAtGivenPositionResponseVO;
import com.adarsh.udaan.vo.SeatRequestVO;
import com.adarsh.udaan.vo.SeatVO;

public interface SeatService {

	public boolean updateSeats(List<SeatVO> vo);
	public boolean saveSeats(List<SeatVO> vos);
	public boolean reserveSeats(String screenName, SeatRequestVO vo);
	public SeatRequestVO getAvailableSeats(String screenName, String status);
	public List<AvailableSeatsAtGivenPositionResponseVO> getAvailableSeatsAtGivenPosition(String screenName, String numberOfSeats, String choice);
}

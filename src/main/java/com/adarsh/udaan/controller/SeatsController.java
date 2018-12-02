package com.adarsh.udaan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adarsh.udaan.service.SeatService;
import com.adarsh.udaan.utils.BusinessException;
import com.adarsh.udaan.vo.AvailableSeatsAtGivenPositionResponseVO;
import com.adarsh.udaan.vo.SeatRequestVO;

@Controller
@RequestMapping(value="/screens")
public class SeatsController {
	
	@Autowired
	private SeatService seatService;

	@ResponseBody
	@RequestMapping(value="/{screen-name}/reserve", method=RequestMethod.POST)
	public HttpStatus reserveSeat(@PathVariable(value = "screen-name") String screenName,
			@RequestBody SeatRequestVO seatRequestVO) {
		try {
			seatService.reserveSeats(screenName, seatRequestVO);
		} catch(BusinessException ex) {
			return HttpStatus.BAD_REQUEST;
		} catch (Exception ex) {
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.OK;
	}
	
	@ResponseBody
	@RequestMapping(value="/{screen-name}/seats", method=RequestMethod.GET)
	public ResponseEntity<?> getAvailableSeats(@PathVariable(value = "screen-name") String screenName,
			@RequestParam("status") String status) {
		try {
			return new ResponseEntity<SeatRequestVO>(seatService.getAvailableSeats(screenName, status), HttpStatus.OK);
		} catch(BusinessException ex) {
			return new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST); 
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/{screen-name}/seats", params={"numSeats", "choice"}, method=RequestMethod.GET)
	public ResponseEntity<?> getAvailableSeatsAtGivenPosition(@PathVariable(value = "screen-name") String screenName,
			@RequestParam("numSeats") String numberOfSeats,
			@RequestParam("choice") String choice) {
		try {
			return new ResponseEntity<List<AvailableSeatsAtGivenPositionResponseVO>>(seatService.getAvailableSeatsAtGivenPosition(screenName, numberOfSeats, choice), HttpStatus.OK); 
		} catch(BusinessException ex) {
			return new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.BAD_REQUEST); 
		} catch (Exception ex) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST); 
		}
	}
}

package com.adarsh.udaan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adarsh.udaan.service.ScreenService;
import com.adarsh.udaan.utils.BusinessException;
import com.adarsh.udaan.vo.ScreenRequestVO;

@Controller
public class ScreensController {
	
	@Autowired
	private ScreenService screenService;
	
	@ResponseBody
	@RequestMapping(value="/screens",method=RequestMethod.POST)
	public ResponseEntity<?> saveScreenInfo(@RequestBody ScreenRequestVO screenRequestVO) {
		try {
			screenService.saveScreen(screenRequestVO);
		} catch(BusinessException ex) {
			return new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.CONFLICT); 
		} catch (Exception ex) {
			return new ResponseEntity<String>("Request Failed", HttpStatus.CONFLICT); 
		}
		return new ResponseEntity<String>("success", HttpStatus.OK); 
	}
	@RequestMapping(value="/screens",method=RequestMethod.GET)
	public ResponseEntity<?> displayHomePage() {
		List<String> screens = screenService.getScreens();
		return new ResponseEntity<List<String>>(screens, HttpStatus.OK);
	}
	
}

package com.adarsh.udaan.service;

import java.util.List;

import com.adarsh.udaan.vo.ScreenRequestVO;

public interface ScreenService {

	public boolean saveScreen(ScreenRequestVO vo);
	public List<String> getScreens();
}

package com.adarsh.udaan.dao;

import java.util.List;

import com.adarsh.udaan.entity.Screen;

public interface ScreenDao {
	public boolean saveScreen(Screen screen);
	public List<String> getScreens();
}

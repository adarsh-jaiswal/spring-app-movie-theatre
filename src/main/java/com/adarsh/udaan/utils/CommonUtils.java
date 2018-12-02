package com.adarsh.udaan.utils;

public class CommonUtils {

	public static boolean isEmpty (String data) {
		if (data == null || data.trim().isEmpty()) {
			return true;
		}
		return false;
	}
}

package com.testapp.utils;

public class BaseUtils {
	public static String formatTime(int time){
		int hour = time / 3600;
		int minute = time % 3600 / 60;
		int second = time % 60;
		return hour + ":" + minute + ":" + second;
	}
}

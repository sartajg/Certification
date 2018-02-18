package com.lift.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResHelper {

	private static Properties properties = null;
	static {
		try(InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");) {
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String property) {
		return properties.getProperty(property);
	}
	
	public static String get(String property, String defaultVal) {
		return properties.getProperty(property, defaultVal);
	}
}

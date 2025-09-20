package org.automation.pom.utils;

import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ConfigLoader {
	private final Properties properties;
	private static ConfigLoader configloader;

	private ConfigLoader() {
		properties = PropertyUtils.propertyLoader("/src/test/resources/config.properties");
	}

	public static ConfigLoader getInstance() {
		if (configloader == null) {
			configloader = new ConfigLoader();
		}
		return configloader;
	}

	public String getBaseUrl() {
		String prop = properties.getProperty("baseUrl");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property baseurl is not specified in the config.properties.file");
	}
	
	public String getUserName() {
		String prop = properties.getProperty("username");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property baseurl is not specified in the config.properties.file");
	}
	
	public String getPassword() {
		String prop = properties.getProperty("password");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property baseurl is not specified in the config.properties.file");
	}
}

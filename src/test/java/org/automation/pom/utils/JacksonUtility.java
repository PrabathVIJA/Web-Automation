package org.automation.pom.utils;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtility {

	public static <T> T deserializeJson(String str, Class<T> T) throws IOException {
		InputStream is = JacksonUtility.class.getClassLoader().getResourceAsStream(str);
		if (is == null) {
			throw new IOException("File not found in resources: " + str);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(is, T);

	}

}

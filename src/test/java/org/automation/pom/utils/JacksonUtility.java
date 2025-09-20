package org.automation.pom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtility {
	private static final ObjectMapper mapper = new ObjectMapper();
	public static <T> T deserializeJson(String str, Class<T> T) throws IOException {
		InputStream is = JacksonUtility.class.getClassLoader().getResourceAsStream(str);
		if (is == null) {
			throw new IOException("File not found in resources: " + str);
		}
		
		return mapper.readValue(is, T);

	}
	
	 // ✅ This reads a LIST of objects (NEW METHOD)
	public static <T> List<T> deserializeJsonList(String fileName, Class<T> clazz) throws IOException {
	    InputStream is = JacksonUtility.class.getClassLoader().getResourceAsStream(fileName);
	    if (is == null) {
	        throw new IOException("File not found in resources: " + fileName);
	    }

	    return mapper.readValue(
	        is,
	        mapper.getTypeFactory().constructCollectionType(List.class, clazz) // <-- This ensures List<BillingData>
	    );
	}

}

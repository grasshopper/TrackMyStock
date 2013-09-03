package com.coolisland.trackmystocks.utils;

import java.util.Map;
import java.util.Set;

import org.springframework.ui.Model;

public class ControllerUtils {

	static public void logModelAttributes(Model model) {
		if (model != null) {
			Map<String, Object> attribMap = model.asMap();
			System.out.println("number of model attributes: " + attribMap.size());
			
			Set<String> modelAttribeKeys = attribMap.keySet();
			for (String key : modelAttribeKeys) {
				Object value = attribMap.get(key);
				
				System.out.println("Key: " + key + ", value: " + value.toString());
			}
		} 
	}
	
}

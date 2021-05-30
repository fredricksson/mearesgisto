package com.mea.api.misc;


import java.util.LinkedHashMap;
import java.util.Map;

public class ApiResponseObject {
	
	Map<String, Object> data = new LinkedHashMap<String, Object>();
	
	public ApiResponseObject (Boolean error, Object message, Object object) {
		this.data.put("error", error);
		this.data.put("message", message);
		this.data.put("object", object);
		
	}
	
	public ApiResponseObject() {
	}
	public Map<String, Object> response (Boolean error, Object message, Object object) {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("error", error);
		data.put("message", message);
		data.put("data", object);
		return data;
	}
}

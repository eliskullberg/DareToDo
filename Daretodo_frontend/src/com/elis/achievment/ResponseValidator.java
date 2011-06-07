package com.elis.achievment;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseValidator {
	private ResponseValidator() {}
	public static void validate(JSONObject obj) throws InvalidResponseException
	{
		if (!obj.has("status")) {
			throw new InvalidResponseException("Missing status field");
		}
		
		try {
			String status = obj.getString("status");
				
			if (status.equals("ok")) {
				if (!obj.has("result")) {
					throw new InvalidResponseException("Missing result field");
				}
			} else if (status.equals("fail")) {
				String errorMessage = obj.has("errorMessage") ? obj.getString("errorMessage") : "Unknown error"; 
				throw new InvalidResponseException(errorMessage);
			} else {
				throw new InvalidResponseException("Invalid status value");
			}
		} catch (JSONException e) {
			throw new InvalidResponseException("WTF!!!");
		}
	}

}

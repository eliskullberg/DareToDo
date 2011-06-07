package com.elis.achievment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Uplink {
	private HttpClient client;
	private JSONObject response;
	
	
	public Uplink() 
	{
		client = new HttpClient();
	}
	
	boolean authenticate(String username, String password) throws InvalidResponseException{
		//POST
		//Result: en boolean 
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	    pairs.add(new BasicNameValuePair("username", username));
	    pairs.add(new BasicNameValuePair("password", password));
	    
	    try {response = client.post("/android/authenticate", pairs);
		ResponseValidator.validate(response);
		JSONArray authentication = response.getJSONArray("result");
		if (authentication.getBoolean(0)) {return true;}
		} catch (JSONException e) {
			throw new InvalidResponseException("Invalid formatted response");
		} 
		return false;
	}
	
	
	public String[] getAllUsers() throws InvalidResponseException{		
		// GET
		// Result: en String-array
		HttpClient client = new HttpClient();
		JSONObject response;
		ArrayList<String> outList = new ArrayList<String>();
		try {response = client.get("/android/getAllUsers");
		ResponseValidator.validate(response);
		JSONArray userList = response.getJSONArray("result");
		for (int i = 0; i < userList.length(); i++) {
			outList.add(userList.getString(i));
			}
		} catch (JSONException e) {
			throw new InvalidResponseException("Invalid formatted response");
		} catch (IOException e) {
			throw new InvalidResponseException("Connection error");
		}
		
		return (String[])outList.toArray();		
	}
		
	public String[] getCategories() throws InvalidResponseException{
		// GET
		// Result: en String-array
		HttpClient client = new HttpClient();
		JSONObject response;
		ArrayList<String> outList = new ArrayList<String>();
		try {response = client.get("/android/getCategories");
		ResponseValidator.validate(response);
		JSONArray categoryList = response.getJSONArray("result");
		for (int i = 0; i < categoryList.length(); i++) {
			outList.add(categoryList.getString(i));
			}
		} catch (JSONException e) {
			throw new InvalidResponseException("Invalid formatted response");
		} catch (IOException e) {
			throw new InvalidResponseException("Connection error");
		}
		
		return (String[])outList.toArray();		
		}
	
	public String[] getAchievmentList(String category) throws InvalidResponseException{
		// POST
		// Result: en String-array
		ArrayList<String> outList = new ArrayList<String>();
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	    pairs.add(new BasicNameValuePair("category", category));
	    try {response = client.post("/android/getAchievments", pairs);
		ResponseValidator.validate(response);
		JSONArray achievmentList = response.getJSONArray("result");
		for (int i = 0; i < achievmentList.length(); i++) {
			outList.add(achievmentList.getString(i));
			}
		} catch (JSONException e) {
			throw new InvalidResponseException("Invalid formatted response");
		} 
		
		return (String[])outList.toArray();		
		}
		
	
	
	public String[] getCompletedAchievmentList(String category, String username) throws InvalidResponseException{
		// POST
		// Result: en String-array
		ArrayList<String> outList = new ArrayList<String>();
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	    pairs.add(new BasicNameValuePair("category", category));
	    pairs.add(new BasicNameValuePair("username", username));
	    try {response = client.post("/android/getompletedAchievments", pairs);
		ResponseValidator.validate(response);
		JSONArray achievmentList = response.getJSONArray("result");
		for (int i = 0; i < achievmentList.length(); i++) {
			outList.add(achievmentList.getString(i));
			}
		} catch (JSONException e) {
			throw new InvalidResponseException("Invalid formatted response");
		} 
		
		return (String[])outList.toArray();		
		}
	
	public String getAchievmentDescription(String category, String achievment) throws InvalidResponseException{
		// POST
		// Result: en String-array
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	    pairs.add(new BasicNameValuePair("category", category));
	    pairs.add(new BasicNameValuePair("achievment", achievment));
	    try {response = client.post("/android/getAchievmentDescription", pairs);
		ResponseValidator.validate(response);
		JSONArray description = response.getJSONArray("result");
		String  output = (String) description.get(0);
		return output;		
		} catch (JSONException e) {
			throw new InvalidResponseException("Invalid formatted response");
		} 
		}
	
	
	public boolean submitCompletedAchievment(String username, String category, String missionname) throws InvalidResponseException{
		// POST
		// Result: en String-array
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	    pairs.add(new BasicNameValuePair("username", username));
	    pairs.add(new BasicNameValuePair("missionname", missionname));	
	    pairs.add(new BasicNameValuePair("category", category));
	    try {response = client.post("/android/submitCompletedAchievment", pairs);
		ResponseValidator.validate(response);
		JSONArray description = response.getJSONArray("result");
		boolean  output = description.getBoolean(0);
	    return output;
	    } catch (JSONException e) {
			throw new InvalidResponseException("Invalid formatted response");
		} 
	}
	
}

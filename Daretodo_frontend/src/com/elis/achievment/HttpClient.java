package com.elis.achievment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpClient {
		
    
    public  HttpClient()
	{
	}
	
	
	public JSONObject get(String res) throws IOException, InvalidResponseException
	{	
		final String Serveraddress = "elis.glhf.eu";
	    BufferedReader in = null;
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			
			URI uri = URIUtils.createURI("http", Serveraddress, 80, res, null, null);
			HttpGet httpget = new HttpGet(uri);
	        HttpResponse response = client.execute(httpget);
	        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	        StringBuffer sb = new StringBuffer();
	        String line = "";			

	        while ((line = in.readLine()) != null) {
	            sb.append(line );}
			try {
				JSONObject back = new JSONObject(sb.toString());
		        return back;
			} catch (JSONException e) {
				throw new InvalidResponseException("Invalid JSON");
			}
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		return new JSONObject(); //fangas senare som tom
		
	}
	
	
	public JSONObject post(String res, List<NameValuePair> pairs)
	{		    // Create a new HttpClient and Post Header
		final String Serveraddress = "elis.glhf.eu";
	    BufferedReader in = null;
		try {
		      DefaultHttpClient client = new DefaultHttpClient();
			  URI uri = URIUtils.createURI("http", Serveraddress, 80, res, null, null);
		      HttpPost post = new HttpPost(uri);
		      //List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		      //pairs.add(new BasicNameValuePair("sender", ""));
		      post.setEntity(new UrlEncodedFormEntity(pairs));
		      HttpResponse response = client.execute(post);
		      in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        StringBuffer sb = new StringBuffer();
		        String line = "";			
		        while ((line = in.readLine()) != null) {
		            sb.append(line );}
				try {
					JSONObject back = new JSONObject(sb.toString());
			        return back;
				} catch (JSONException e) {
					throw new InvalidResponseException("Invalid JSON");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return new JSONObject(); //fangas senare som tom
	}
	
}
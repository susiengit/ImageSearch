package com.thecodepath.example.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResults {
	
	private String fullUrl;
	private String thumbUrl;
	
	public ImageResults(JSONObject json) {
		try {
			this.fullUrl = json.getString("url");
			this.thumbUrl = json.getString("tbUrl");
		} catch (JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
	}
	
	public String getFullUrl() {
		return fullUrl;
		
	}
	
	public String getThumbUrl() {
		return thumbUrl;
		
	}
	
	public String toString() {
		return this.thumbUrl;
		
	}

	public static ArrayList<ImageResults> fromJSONArray(
			JSONArray array) {
		ArrayList<ImageResults> results = new ArrayList<ImageResults>();
		for (int x=0; x < array.length(); x++){
			try {
				results.add(new ImageResults(array.getJSONObject(x)));
			} catch (JSONException e){
				e.printStackTrace();
			}
			
		}
		return null;
	}
}

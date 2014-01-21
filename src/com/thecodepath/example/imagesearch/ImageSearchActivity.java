package com.thecodepath.example.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class ImageSearchActivity extends Activity {
	
	EditText etQuery;
	GridView gvResults;
	Button btnSubmit;
	ArrayList<ImageResults> imageResults = new ArrayList<ImageResults>();
	ImageResultArrayAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        setupViews();
        imageAdapter = new ImageResultArrayAdapter(this, imageResults);
        gvResults.setAdapter(imageAdapter);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_search, menu);
        return true;
    }
    

    public void onPreference (MenuItem mi) {
        // handle click here
    	Toast.makeText(this, "You got it!", Toast.LENGTH_SHORT).show();
    	
    	Intent i = new Intent(this, SecondActivity.class);
    	startActivity(i);
     } 
    
    public void setupViews(){
    	etQuery = (EditText) findViewById(R.id.etQuery);
    	gvResults = (GridView) findViewById(R.id.gvResults);
    	btnSubmit = (Button) findViewById(R.id.btnSubmit);
    
    }
    
    public void onImageSearch(View v) {
    	String query = etQuery.getText().toString();
    	Toast.makeText(this, "Searching for "+ query, Toast.LENGTH_SHORT).show();
    	
    	AsyncHttpClient client = new AsyncHttpClient();
  
		//https://ajax.googleapis.com/ajax/services/search/images?q=Android&v=1.0
    	client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&" + 
    			   "start=" + 0 + "&v=1.0&q=" + Uri.encode(query), 
    				new JsonHttpResponseHandler() {
    	
    		public void onSuccess(JSONObject response){
    			JSONArray imageJsonResults = null;
    			try {
    				imageJsonResults = response.getJSONObject(
    						"responseData").getJSONArray("results");
    				imageResults.clear();
    				imageAdapter.addAll(ImageResults.fromJSONArray(imageJsonResults));
    				Log.d("DEBUG", imageResults.toString());
    			} catch (JSONException e) {
    				e.printStackTrace();
    			}
    		}
    	});
    	
    	}
    
}

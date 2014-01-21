package com.thecodepath.example.imagesearch;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResults> {
	public ImageResultArrayAdapter(Context context, List<ImageResults> images){
		super(context, android.R.layout.simple_list_item_1, images);	
	}

}

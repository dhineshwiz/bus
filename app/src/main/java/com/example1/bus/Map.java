package com.example1.bus;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Map extends FragmentActivity {

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  System.out.println("mmmappp");
	  setContentView(R.layout.map_activity);
	  GoogleMap mMap;
	  	  mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	  	
	  	    // Do a null check to confirm that we have not already instantiated the map.
	  	    if (mMap == null) {
	  	    	System.out.println("notverfied");
	  	        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
	  	                            .getMap();
	  	        // Check if we were successful in obtaining the map.
	  	        if (mMap != null) {
	  	        	System.out.println("verfied");
	  	            // The Map is verified. It is now safe to manipulate the map.
	  	        }
	  	    }
	 }
	}
/*public class Map extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        SupportMapFragment fragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, fragment).commit();
    }
}*/
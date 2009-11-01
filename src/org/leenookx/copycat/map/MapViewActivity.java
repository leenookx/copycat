package org.leenookx.copycat.map;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapViewActivity extends MapActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		MapView mapView = new MapView(this, "");
		setContentView(mapView);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}

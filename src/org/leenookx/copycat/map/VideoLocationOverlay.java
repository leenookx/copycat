package org.leenookx.copycat.map;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class VideoLocationOverlay extends Overlay {
	private Context context;
	private HashMap<String, Location> buddyLocations;
	private Location location;
	private GeoPoint locationPoint;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;

		Double latitude = location.getLatitude()*1E6;
		Double longitude = location.getLongitude()*1E6;

		locationPoint = new GeoPoint(latitude.intValue(), longitude.intValue());
	}

	public VideoLocationOverlay(Context _context) {
		super();
		context = _context;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		if (shadow == false) {
			// draw stuff here
		}
		
		super.draw(canvas, mapView, shadow);
	}

	@Override
	public boolean onTap(GeoPoint point, MapView mapView) {
		return false;
	}
}

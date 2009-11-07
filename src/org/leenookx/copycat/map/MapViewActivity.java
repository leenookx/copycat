package org.leenookx.copycat.map;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.googlenav.map.Map;
import com.google.map.Zoom;

public class MapViewActivity extends MapActivity {
	
	private MapView mapView = null;
	private MapController mapController = null;
	private LocationManager locMan = null;
	private Criteria criteria = null;
	private String provider = "";
	private final LocationListener locationUpdateListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			updateWithLocation( location );
		}

		public void onProviderDisabled(String provider) {}
		public void onProviderEnabled(String provider) {}
		public void onStatusChanged(String provider, int status, Bundle extras) {}		
	};
	private List<Overlay> overlays = null;
	private MyLocationOverlay myLocationOverlay = null;
	private BuddyLocationOverlay buddyOverlay = null;
	private OthersLocationOverlay othersOverlay = null;
	/** Whether or not the satellite view is switched on or not. */
	private boolean satelliteState = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mapView = new MapView(this, "");
		setContentView(mapView);
		
		mapController = mapView.getController();
		mapController.setZoom( 17 );
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onStart() {
		
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		overlays.add( myLocationOverlay );
		myLocationOverlay.enableMyLocation();
		
		locMan = (LocationManager)getSystemService(LOCATION_SERVICE);
		 
		criteria = new Criteria();
		criteria.setAccuracy( Criteria.ACCURACY_FINE );
		criteria.setAltitudeRequired( false );
		criteria.setBearingRequired( false );
		criteria.setCostAllowed( true );
		criteria.setPowerRequirement( Criteria.POWER_LOW );
		criteria.setSpeedRequired( false );
		 
		provider = locMan.getBestProvider(criteria, true);
		
		locMan.requestLocationUpdates(provider,
				 						60000, // 1 minute 
				 						10,    // 100 metres
				 						locationUpdateListener);
		
		buddyOverlay = new BuddyLocationOverlay( getApplicationContext() );
		overlays.add( buddyOverlay );
		
		othersOverlay = new OthersLocationOverlay( getApplicationContext() );
		overlays.add( othersOverlay );
		
		// Get the current location now...
		Location location = locMan.getLastKnownLocation( provider );
		updateWithLocation( location );
	}
	
	@Override
	public void onStop() {
		
	}
	
	private void updateWithLocation(Location location) {
		 
	}

	private void updateWithNewLocation(Location location) {
		buddyOverlay.setLocation(location);
		othersOverlay.setLocation(location);
		mapView.invalidate();

		// Recenter the map.
		Double geoLat = location.getLatitude()*1E6;
		Double geoLng = location.getLongitude()*1E6;
		GeoPoint point = new GeoPoint(geoLat.intValue(), geoLng.intValue());

		mapController.animateTo(point);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_I) {
			// Zoom not closer than possible
			mapController.setZoom(Math.min(Zoom.MAX_ZOOM, mapView.getZoomLevel() + 1));
			return true;
		} 
		else if (keyCode == KeyEvent.KEYCODE_O) {
			mapController.setZoom(Math.min(Zoom.MIN_ZOOM, mapView.getZoomLevel() - 1));
			return true;
		} 
		else if (keyCode == KeyEvent.KEYCODE_T) {
			// Switch to satellite view
			satelliteState = !satelliteState;
			mapView.setSatellite( satelliteState );
			return true;
		}
		
		return false;
	} 
}

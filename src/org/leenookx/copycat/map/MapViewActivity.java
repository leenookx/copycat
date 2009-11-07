package org.leenookx.copycat.map;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MapViewActivity extends MapActivity {
	
	private MapView mapView = null;
	private MapController mapController = null;
//	private LocationManager locMan = null;
//	private Criteria criteria = null;
//	private String provider = "";
//	private final LocationListener locationUpdateListener = new LocationListener() {
//
//		public void onLocationChanged(Location location) {
//			updateWithLocation( location );
//		}
//
//		public void onProviderDisabled(String provider) {}
//		public void onProviderEnabled(String provider) {}
//		public void onStatusChanged(String provider, int status, Bundle extras) {}		
//	};
	private List<Overlay> overlays = null;
	private MyLocationOverlay myLocationOverlay = null;
	
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
		
//		locMan = (LocationManager)getSystemService(LOCATION_SERVICE);
//		 
//		criteria = new Criteria();
//		criteria.setAccuracy( Criteria.ACCURACY_FINE );
//		criteria.setAltitudeRequired( false );
//		criteria.setBearingRequired( false );
//		criteria.setCostAllowed( true );
//		criteria.setPowerRequirement( Criteria.POWER_LOW );
//		criteria.setSpeedRequired( false );
//		 
//		provider = locMan.getBestProvider(criteria, true);
//		 
//		// Get the current location now...
//		Location location = locMan.getLastKnownLocation( provider );
//		updateWithLocation( location );
//		
//		locMan.requestLocationUpdates(provider,
//				 						60000, // 1 minute 
//				 						10,    // 100 metres
//				 						locationUpdateListener);
	}
	
	@Override
	public void onStop() {
		
	}
	
	private void updateWithLocation(Location location) {
		 
	}
}

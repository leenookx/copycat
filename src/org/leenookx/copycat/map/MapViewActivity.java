package org.leenookx.copycat.map;

import java.util.List;

import org.leenookx.copycat.R;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MapViewActivity extends MapActivity {
	
	private MapView mapView = null;
	private MapController mapController = null;
	private LocationManager locMan = null;
	private Criteria criteria = null;
	private String provider = "";
	private final LocationListener locationUpdateListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			updateWithNewLocation( location );
		}

		public void onProviderDisabled(String provider) {}
		public void onProviderEnabled(String provider) {}
		public void onStatusChanged(String provider, int status, Bundle extras) {}		
	};
	private List<Overlay> overlays = null;
	private MyLocationOverlay myLocationOverlay = null;
	private BuddyLocationOverlay buddyOverlay = null;
	private OthersLocationOverlay othersOverlay = null;
	private NoteLocationOverlay notesOverlay = null;
	private ImageLocationOverlay imagesOverlay = null;
	private VideoLocationOverlay videosOverlay = null;
	/** Whether or not the satellite view is switched on or not. */
	private boolean satelliteState = false;
	/** Whether or not the street view is switched on or not. */
	private boolean streetState = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		mapView = (MapView)findViewById(R.id.mapview);
		
		mapController = mapView.getController();
		overlays = mapView.getOverlays();
		
		mapView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.e("copycat", "You clicked the map");
			}
		});
		
		myLocationOverlay = new MyLocationOverlay(getApplicationContext(), mapView);
		myLocationOverlay.enableMyLocation();
		overlays.add( myLocationOverlay );
		
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

		notesOverlay = new NoteLocationOverlay( getApplicationContext() );
		overlays.add( notesOverlay );
		
		imagesOverlay = new ImageLocationOverlay( getApplicationContext() );
		overlays.add( imagesOverlay );

		videosOverlay = new VideoLocationOverlay( getApplicationContext() );
		overlays.add( videosOverlay );

		// Get the current (or last known) location now...
		Location location = locMan.getLastKnownLocation( provider );
		GeoPoint p = new GeoPoint((int)location.getLatitude() * 1000000, 
									(int)location.getLongitude() * 1000000);
		mapController.setZoom( 9 );
		mapController.setCenter( p );
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void updateWithNewLocation(Location location) {
		buddyOverlay.setLocation( location );
		othersOverlay.setLocation( location );
		notesOverlay.setLocation( location );
		imagesOverlay.setLocation( location );
		videosOverlay.setLocation( location );
		mapView.invalidate();

		// Recenter the map.
		Double geoLat = location.getLatitude()*1E6;
		Double geoLng = location.getLongitude()*1E6;
		GeoPoint point = new GeoPoint(geoLat.intValue(), geoLng.intValue());

		mapController.animateTo(point);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_I:
				mapController.zoomIn();
				break;
			case KeyEvent.KEYCODE_O:
				mapController.zoomOut();
				break;
			case KeyEvent.KEYCODE_S:
				streetState = !streetState;
				mapView.setStreetView( streetState );
				break;
			case KeyEvent.KEYCODE_T:
				// Switch to satellite view
				satelliteState = !satelliteState;
				mapView.setSatellite( satelliteState );
				break;
		}
		
		return super.onKeyDown(keyCode, event);
	} 
}

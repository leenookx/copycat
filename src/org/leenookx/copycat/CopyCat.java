package org.leenookx.copycat;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.google.android.maps.MapActivity;

public class CopyCat extends MapActivity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost mTabHost = (TabHost) findViewById(R.id.main_tabhost);
		mTabHost.setup();
		
		// Get all of the tabs...
		TabSpec mapTabSpecs = mTabHost.newTabSpec("MapViewTab");
		TabSpec statusTabSpecs = mTabHost.newTabSpec("StatusViewTab");

		mapTabSpecs.setIndicator("", getResources().getDrawable(R.drawable.globemap));
		mapTabSpecs.setContent(R.id.maptabview);		
		
		statusTabSpecs.setIndicator("Status");
		statusTabSpecs.setContent(R.id.statustabview);
		
		mTabHost.addTab(mapTabSpecs);
		mTabHost.addTab(statusTabSpecs);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
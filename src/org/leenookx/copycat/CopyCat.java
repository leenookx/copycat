package org.leenookx.copycat;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.google.android.maps.MapActivity;

public class CopyCat extends MapActivity {
    private TabHost mTabHost;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTabHost = (TabHost) findViewById(R.id.main_tabhost);
		mTabHost.setup();
		
		// Get all of the tabs...
		TabSpec mapTabSpecs = mTabHost.newTabSpec("MapViewTab");

		mapTabSpecs.setIndicator(getText(R.string.mapview_tab_name), getResources().getDrawable(R.drawable.globemap));
		mapTabSpecs.setContent(R.id.maptabview);		
		
		mTabHost.addTab(mapTabSpecs);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
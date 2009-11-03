package org.leenookx.copycat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		 super.onCreateOptionsMenu(menu);
	
		 menu.add(0, 0, 0, R.string.menu_settings).setIcon(android.R.drawable.ic_menu_preferences);
		 menu.add(0, 1, 0, R.string.menu_about).setIcon(android.R.drawable.ic_menu_info_details);
	
		 return true;
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		    case 0: /* .. start settings activity .. */ break;
		    case 1: /* .. start about activity .. */ break;
		 }
		 
		 return true;
	 }
}
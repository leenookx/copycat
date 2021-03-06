package org.leenookx.copycat;

import org.leenookx.copycat.map.MapViewActivity;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class CopyCat extends TabActivity {
	
	private static AlertDialog m_AlertDlg;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost mTabHost = getTabHost();
		mTabHost.setup();
		
		// Get all of the tabs...
		TabSpec mapTabSpecs = mTabHost.newTabSpec("MapViewTab");
		TabSpec buddyTabSpecs = mTabHost.newTabSpec("BuddyViewTab");
		TabSpec othersTabSpecs = mTabHost.newTabSpec("OthersViewTab");
		TabSpec statusTabSpecs = mTabHost.newTabSpec("StatusViewTab");

//		mapTabSpecs.setIndicator("", getResources().getDrawable(R.drawable.globemap));
//		mapTabSpecs.setContent(R.id.maptabview);	
		mapTabSpecs.setIndicator("Map", getResources().getDrawable(R.drawable.globemap));
		Context ctx = getApplicationContext();
	    Intent i = new Intent(ctx, MapViewActivity.class); 
	    mapTabSpecs.setContent(i);
		
		buddyTabSpecs.setIndicator("Buddies");
		buddyTabSpecs.setContent(R.id.buddytabview);
		
		othersTabSpecs.setIndicator("Others");
		othersTabSpecs.setContent(R.id.otherstabview);
		
		statusTabSpecs.setIndicator("Status");
		statusTabSpecs.setContent(R.id.statustabview);
		
		mTabHost.addTab(mapTabSpecs);
		mTabHost.addTab(buddyTabSpecs);
		mTabHost.addTab(othersTabSpecs);
		mTabHost.addTab(statusTabSpecs);
		
		mTabHost.setCurrentTab(0);
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
		boolean result = super.onOptionsItemSelected(item);
		Intent intent = null;
			
		 switch (item.getItemId()) {
		    case 0: { /* .. start settings activity .. */
				try {
					intent = new Intent(this, org.leenookx.copycat.Settings.class);
					startActivity(intent);
				} 
				catch (ActivityNotFoundException e) {
					Log.e("copycat", e.getMessage());
				}
				}
		    	break;
		    case 1: /* .. start about activity .. */ 
				if (m_AlertDlg != null) {
					m_AlertDlg.cancel();
				}
				m_AlertDlg = new AlertDialog.Builder(this)
								.setMessage(getString(R.string.about).replace("\\n","\n").replace("${VERSION}", Utils.getVersion(this)))
								.setTitle(getString(R.string.menu_about))
								.setIcon(R.drawable.icon)
								.setCancelable(true)
								.show();
				break;
		 }
		 
		 return result;
	 }
}
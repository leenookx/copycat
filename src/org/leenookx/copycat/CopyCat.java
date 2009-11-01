package org.leenookx.copycat;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class CopyCat extends TabActivity {
    private TabHost mTabHost;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTabHost = getTabHost();
        
        mTabHost.addTab(mTabHost.newTabSpec("map").setIndicator("TAB 1").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("other").setIndicator("TAB 2").setContent(R.id.tab2));
        
        mTabHost.setCurrentTab(0);
    }
}
package com.elis.achievment;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

public class AchievmentOverview extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uplink ul = new Uplink();
        setContentView(R.layout.achievmentoverview);
        TextView title = (TextView) findViewById(R.id.overview_title);
        TabHost tabHost = getTabHost(); 
        TabHost.TabSpec spec; 
        Intent intent; 
        Intent tempintent = getIntent();
        
        final String activeUser = tempintent.getExtras().getString("username");
        final String[] categories = (String[]) tempintent.getExtras().get("categories");
        title.setText(tempintent.getExtras().getString("title"));
        
        
        for (int i = 0; i<categories.length; i++ ){
        	
        	intent = new Intent().setClass(this, AchievmentList.class);
        	String [] ach = null;
        	try{ach = ul.getAchievmentList(categories[i]);}
        	catch (InvalidResponseException e) {System.err.print(e.getMessage());}
        	intent.putExtra("achievments", ach);
        	intent.putExtra("category",categories[i]);
        	intent.putExtra("username", activeUser);
        	intent.putExtra("editable", tempintent.getExtras().getString("editable"));
        	spec = tabHost.newTabSpec(categories[i])
        		.setIndicator(categories[i])
        		.setContent(intent);
        	tabHost.addTab(spec);
        }
        

        
        
 }
    
    
    
}
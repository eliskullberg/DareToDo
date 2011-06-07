package com.elis.achievment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class UserOverview extends Activity {
	Uplink ul = new Uplink();
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_overview);

		final TextView splash = (TextView) findViewById(R.id.user_overview_TextView01);
		final Button button = (Button) findViewById(R.id.user_overview_Button01);
		final Button button2 = (Button) findViewById(R.id.user_overview_Button02);

		// Get all registered users from API
		String[] userData = null;
		try {userData = ul.getAllUsers();} 
		catch (InvalidResponseException e) {
			System.err.println(e.getMessage());
		}
		final String[] otherUsers = userData;
		
		// Get achievment categories from API
		String[] categoryData = null;
		try {categoryData = ul.getAllUsers();} 
		catch (InvalidResponseException e) {
			System.err.println(e.getMessage());
		}
		final String[] categories = categoryData;	
		

		
		//Set welcome textfield
		Intent intent = getIntent();
		final String activeUser = intent.getExtras().getString("username");
		splash.setText("Welcome, " + activeUser +"!");
		
		//Set spinner (static atm)
		final Spinner spinner = (Spinner) findViewById(R.id.user_overview_Spinner01);
		ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
		        android.R.layout.simple_spinner_dropdown_item,
		        otherUsers);
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(spinnerArrayAdapter);
	    
	    
	    // Initiate button for viewing own profile
	    button.setText("View your own achievments");
	    button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(UserOverview.this, AchievmentOverview.class);
            	myIntent.putExtra("username", activeUser);
            	myIntent.putExtra("categories", categories);
            	myIntent.putExtra("editable", "yes");
            	myIntent.putExtra("title", "Personal achievments:"); 
            	UserOverview.this.startActivity(myIntent);
            }
        });
	    
	    // Initiate button for viewing other profiles
	    button2.setText("View this persons achievments");
	    button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(UserOverview.this, AchievmentOverview.class);
            	myIntent.putExtra("username", otherUsers[spinner.getSelectedItemPosition()]);
            	myIntent.putExtra("categories", categories);
            	myIntent.putExtra("editable", "no"); 
            	myIntent.putExtra("title", "" + otherUsers[spinner.getSelectedItemPosition()] + "'s" + " achievments:"); 
            	UserOverview.this.startActivity(myIntent);
            }
        });
	   
	}
	
}

package com.elis.achievment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class AchievmentDetails extends Activity {
    @Override
   public void onCreate(Bundle savedInstanceState) {
    
   super.onCreate(savedInstanceState);
   setContentView(R.layout.achievmentdetails);
   final Uplink ul = new Uplink();
   
   final TextView one = (TextView) findViewById(R.id.details_textView0);
   final TextView two = (TextView) findViewById(R.id.details_textView1);
   final CheckBox check = (CheckBox) findViewById(R.id.details_checkBox0);
   final Button button = (Button) findViewById(R.id.details_button0);
   	
   Intent intent = getIntent();
   final String achievment = (String) intent.getExtras().get("achievment");
   final String username = (String) intent.getExtras().get("username");
   final String details = (String) intent.getExtras().get("details");
   final String category = intent.getExtras().getString("category");
   
	
   one.setText(achievment);
   //one.setTextAppearance(this, R.style.Bold);  
   two.setText(details);
   button.setText("submit changes");
   button.setOnClickListener(new View.OnClickListener() {
       public void onClick(View v) {
    	   boolean status = false;
    	   try {status = ul.submitCompletedAchievment(username, category, achievment);}
    	   catch (InvalidResponseException e){System.err.print(e.getMessage());}
           if(status) {button.setText("submitted for: " + username);}
           else {button.setText("Upload failed");}
       }
   });
	
    }

	 
}

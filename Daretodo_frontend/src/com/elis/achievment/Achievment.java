package com.elis.achievment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Achievment extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    	final Uplink ul = new Uplink();
        
        final EditText text1 = (EditText) findViewById(R.id.EditText01);
        final EditText text2 = (EditText) findViewById(R.id.EditText02);
        final TextView view1 = (TextView) findViewById(R.id.TextView01);
        final TextView view2 = (TextView) findViewById(R.id.TextView02);
        final Button button = (Button) findViewById(R.id.Button01);

        
        view1.setText("Username");
        view2.setText("Password");
        text1.setText("");
        text2.setText("");
        button.setText("Login!");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String password = text2.getText().toString();
                String username = text1.getText().toString();
                
                boolean loginStatus = false;
        		try {loginStatus = ul.authenticate(username, password);} 
        		catch (InvalidResponseException e) {
        			System.err.println(e.getMessage());
        		}
        		
                if(loginStatus)
                {
            	Intent myIntent = new Intent(Achievment.this, UserOverview.class);
            	myIntent.putExtra("username", username);
            	Achievment.this.startActivity(myIntent);
                }
                else {text1.setText("Username of password incorrect!");}
            	
            }
        });
        
    }
}
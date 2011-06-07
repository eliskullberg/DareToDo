package com.elis.achievment;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
// Not used ATM, see reusing AchievmentOverview.class instead
public class OtherProfile extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  Intent intent = getIntent();
		  
		  final String[] achievments = (String[]) intent.getExtras().get("category");
		  setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, achievments));

		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);

		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		      //Toast.makeText(getApplicationContext(), "" + achievments[position] + ": "+ Uplink.getAchievmentDescription("example"),
		      //    Toast.LENGTH_LONG).show();
		    }
		  });
		}
}

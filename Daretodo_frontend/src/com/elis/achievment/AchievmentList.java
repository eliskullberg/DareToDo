package com.elis.achievment;

import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AchievmentList extends ListActivity {
    private boolean[] itemToggled;
    public String[] completed;
    public String[] achievments;
    public String editable;
    Uplink ul = new Uplink();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  Intent intent = getIntent();
	  editable = intent.getExtras().getString("editable");
      final String activeUser = intent.getExtras().getString("username"); 
	  achievments = (String[]) intent.getExtras().get("achievments");
	  final String category = (String) intent.getExtras().get("category");
	  // Get data from API
	  try{completed = ul.getCompletedAchievmentList(category, activeUser);}
	  catch (InvalidResponseException e){System.err.print(e.getMessage());}
      itemToggled = new boolean[achievments.length];
      Arrays.fill(itemToggled, false);
      
	  setListAdapter(new ModdedAdapter<String>(this, R.layout.list_item, R.id.listtext ,Arrays.asList(achievments)));

	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);

	  lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
	        int position, long id) {
	    	String description = "";
	    	//Get data from API
	    	try{description = ul.getAchievmentDescription(category, achievments[position]);}
	    	catch (InvalidResponseException e) {System.err.print(e.getMessage());}
	    	Intent myIntent = new Intent(AchievmentList.this, AchievmentDetails.class);
        	myIntent.putExtra("username", activeUser);
        	myIntent.putExtra("achievment", achievments[position]);
        	myIntent.putExtra("category", category);
        	myIntent.putExtra("details", description);
        	if (editable.equals("yes")) {AchievmentList.this.startActivity(myIntent);}
	    }
	  });
	    	  
	}

	public class ModdedAdapter<T> extends ArrayAdapter<T>
    {
    	
        public ModdedAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = super.getView(position, convertView, parent);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.icon);
            //imageView.setImageResource(itemToggled[position] ? R.drawable.ok : R.drawable.cross);
            TextView textview = (TextView) itemView.findViewById(R.id.listtext);
            if (Arrays.asList(completed).contains(textview.getText())) {imageView.setImageResource(R.drawable.ok);}
            else {imageView.setImageResource(R.drawable.cross);}
           
            
            
            return itemView;
        }
    }

	
}

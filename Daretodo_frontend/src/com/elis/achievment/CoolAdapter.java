package com.elis.achievment;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


    public class CoolAdapter<T> extends ArrayAdapter<T>
    {
        private boolean[] itemToggled;

    	
        public CoolAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = super.getView(position, convertView, parent);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.icon);
            imageView.setImageResource(itemToggled[position] ? R.drawable.ok : R.drawable.cross);
            return itemView;
        }
    }

    
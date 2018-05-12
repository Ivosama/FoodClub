package com.example.ivand.foodclub;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class roleListAdapter extends ArrayAdapter<Role> {

    public roleListAdapter(Context context, ArrayList<Role> roles){
        super(context,0,roles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get data item for position
        Role role = getItem(position);


        // Check if an existing view is being reused, otherwise inflate a new view from custom row layout
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.role_list_layout, parent, false);
        }

        // Get  references of views from layout .xml file, so that they can be populated with row data
        TextView roleTitle = (TextView) convertView.findViewById(R.id.list_itemRoleTitle);
        ImageView takenOrNot = (ImageView) convertView.findViewById(R.id.takenOrNot);

        // Fill views with specific data
        roleTitle.setText(role.getTitle());

        if(role.isTaken == true){
            takenOrNot.setImageResource(R.drawable.taken_icon);
            convertView.setBackgroundColor(Color.RED);
        }else if(role.isTaken == false){
            takenOrNot.setImageResource(R.drawable.free_icon);
            convertView.setBackgroundColor(Color.GREEN);
        }

        // Return the new view so it can be shown
        return convertView;
    }
}

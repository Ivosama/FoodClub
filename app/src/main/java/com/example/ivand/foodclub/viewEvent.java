package com.example.ivand.foodclub;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class viewEvent extends AppCompatActivity {

    private TextView title;
    private TextView food;
    private TextView place;
    private TextView time;
    private TextView price;
    private TextView description;

    Role[] roles = new Role[10];

    ListView listView;
    List list;
    ArrayAdapter adapter;

    public ArrayList<Role> eventRoleList = new ArrayList<Role>();
    {
        list = new ArrayList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView)findViewById(R.id.eventTitleText);
        food = (TextView)findViewById(R.id.eventFoodText);
        place = (TextView)findViewById(R.id.eventPlaceText);
        time = (TextView)findViewById(R.id.eventTimeText);
        price = (TextView)findViewById(R.id.eventPriceText);
        description = (TextView)findViewById(R.id.eventDescriptionText);


        Event receivedEvent = new Event();
        Bundle bundle = getIntent().getExtras();

        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
            title.setText(receivedEvent.name);
            food.setText(receivedEvent.menu);
            place.setText(receivedEvent.place);
            time.setText(receivedEvent.time);
            price.setText("" + receivedEvent.price);
            description.setText(receivedEvent.description);

        } catch (Exception e) {

        }


        for (int i = 0; i < receivedEvent.roles.size(); i++) {
            int size = receivedEvent.roles.size();
            //Toast.makeText(getApplicationContext(),"" + size, Toast.LENGTH_LONG).show();
            try {
                Role tempRole = (Role) receivedEvent.roles.get(i);
                Toast.makeText(getApplicationContext(), tempRole.title, Toast.LENGTH_LONG).show();
                eventRoleList.add(tempRole);
            } catch (Exception e) {

            }
        }

        listView = (ListView) findViewById(R.id.eventRolesList);
        for (int i = 0; i < eventRoleList.size(); i++) {
            int size = eventRoleList.size();
            Toast.makeText(getApplicationContext(),"" + size, Toast.LENGTH_LONG).show();
            Role tempRole = (Role) eventRoleList.get(i);
            String tempName = tempRole.getTitle();
            list.add(tempName);

        }

        adapter = new ArrayAdapter(viewEvent.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Object[] roleList = new Object[list.size()];
        roleList = list.toArray(roleList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Clicked this " + position, Toast.LENGTH_LONG);
                //Toast.makeText(Map_and_List.this, eventArrayList[6].name, Toast.LENGTH_SHORT).show();


                //Intent intent = new Intent(viewEvent.this, viewEvent.class); // Create intent to send Parcel to Map and List
                //Role role = eventRoleList.get(position);

                Toast.makeText(getApplicationContext(), "position = " + position + " name = " + eventRoleList.get(position).title, Toast.LENGTH_LONG).show();
                //startActivity(intent);
                //openViewEvent();
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Messages not yet implemented", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

     /*                             ***WIP***
            CODE FOR GETTING POP-UP CONFIRMATION OF JOINING EVENT AS A ROLE

        join_event_button = (Button) findViewById(R.id.join_event_button);
        join_event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmJoin();
            }
        });

    } private void confirmJoin(){
        final AlertDialog.Builder confirmJ = new AlertDialog.Builder(this);
        confirmJ.setMessage("Do you want to join the event as a " + //event.role// + "?");
        confirmJ.setCancelable(false);

        confirmJ.setPositiveButton("yeah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //DO LATER - Functionality for accepting to join event as "role"
            }
        });
        confirmJ.setNegativeButton("nah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmJ.setCancelable(true);
            }
        });
        confirmJ.create().show();
    }
            *** END OF POP-UP CODE ***
    */

}
}

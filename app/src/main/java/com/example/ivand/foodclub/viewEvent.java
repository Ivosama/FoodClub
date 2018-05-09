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

    User user = new User();
    Event receivedEvent = new Event();

    Role[] roles = new Role[10];

    ListView listView;
    List list;
    ArrayAdapter adapter;

    public ArrayList<Role> eventRoleList = new ArrayList<Role>();
    public ArrayList<User> userArrayList = new ArrayList<>();
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



        Bundle bundle = getIntent().getExtras();


        try {
            User tempUser = bundle.getParcelable("com.package.userObject");
            user = tempUser;
            Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

        }
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

        try {
            userArrayList = bundle.getParcelableArrayList("com.package.userArray");
        } catch (Exception e) {

        }

        // Assign roles from received event to actual list, if roles exist
        if (receivedEvent.roles != null) {
            for (int i = 0; i < receivedEvent.roles.size(); i++) {
                int size = receivedEvent.roles.size();
                //Toast.makeText(getApplicationContext(),"" + size, Toast.LENGTH_LONG).show();
                try {
                    Role tempRole = (Role) receivedEvent.roles.get(i);
                    Toast.makeText(getApplicationContext(), tempRole.title, Toast.LENGTH_SHORT).show();
                    eventRoleList.add(tempRole);
                } catch (Exception e) {

                }
            }
        }

        // Populate the graphical Role listview with roles from the event
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

                //Intent intent = new Intent(viewEvent.this, viewEvent.class); // Create intent to send Parcel to Map and List
                Role role = eventRoleList.get(position);
                confirmJoin(role);

                //Toast.makeText(getApplicationContext(), "position = " + position + " name = " + eventRoleList.get(position).title, Toast.LENGTH_LONG).show();
                //startActivity(intent);
                //openViewEvent();
            }


        });

        Button deleteButton = (Button) findViewById(R.id.deleteButt);
        Button leaveButton = (Button) findViewById(R.id.leaveButt);

        // Visibility of delete button
        if (user.getId() == receivedEvent.ownerID) {
            deleteButton.setVisibility(View.VISIBLE);
            leaveButton.setVisibility(View.GONE);
        } else {
            deleteButton.setVisibility(View.GONE);

            // Visibility of leave button
            for(int i = 0; i < receivedEvent.roles.size(); i++){
                if(receivedEvent.roles.get(i).getHolderID() == user.getId() && receivedEvent.roles.get(i).isTaken == true){
                    leaveButton.setVisibility(View.VISIBLE);
                }
            }
        }

        // OnClickListener for deleting event
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });

        // OnClickListener for leaving event
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
}


    private void confirmDelete() {
        final AlertDialog.Builder confirmDelete = new AlertDialog.Builder(this);
        confirmDelete.setMessage("Are you sure you want to create this event?");
        confirmDelete.setCancelable(false);

        confirmDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                receivedEvent.ownerID = 0;
                Intent intent = new Intent(viewEvent.this, MainActivity.class);
                MainActivity.userIsHosting = false;
                Map_and_List.userIsHosting = false;
                Host.userIsHosting = false;
                intent.putExtra("com.package.eventObject", receivedEvent);
                intent.putExtra("com.package.userObject", user);
                startActivity(intent);
            }
        });
        confirmDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmDelete.setCancelable(true);
            }
        });
        confirmDelete.create().show();
    }


    private void confirmJoin(final Role role){
        final AlertDialog.Builder confirmJ = new AlertDialog.Builder(this);
        confirmJ.setMessage("Do you want to join the event as a " + role.title + "?");
                confirmJ.setCancelable(false);

        confirmJ.setPositiveButton("yeah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(viewEvent.this , MainActivity.class);
                role.holderID = user.getId();
                role.isTaken = true;
                MainActivity.userApplied = true;
                Map_and_List.userApplied = true;
                Host.userApplied = true;
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "you are now signed up as " + role.title, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "role.isTaken is now " + role.isTaken, Toast.LENGTH_SHORT).show();
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

}

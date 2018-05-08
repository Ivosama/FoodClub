package com.example.ivand.foodclub;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class Host extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    // Initial variables
    String eventName, whatCooking, place, description, time;
    int price = 0;
    int ID = 0;
    int dist = 5;

    // Text fields
    EditText eventName_input;
    EditText whatCooking_input;
    EditText place_input;
    EditText description_input;
    EditText price_input;
    TextView time_input;

    // Buttons
    Button post_event_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_host);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout = findViewById(R.id.drawer_layout);

        getSupportActionBar().setTitle("Host an event");

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        time_input = (TextView)findViewById(R.id.time_input);

        eventName_input= (EditText) findViewById(R.id.eventName_input);
        whatCooking_input= (EditText) findViewById(R.id.whatCooking_input);
        place_input= (EditText) findViewById(R.id.place_input);
        description_input= (EditText) findViewById(R.id.description_input);
        price_input= (EditText) findViewById(R.id.price_input);

        post_event_button = (Button) findViewById(R.id.post_event_button);
        post_event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    eventName = eventName_input.getText().toString();
                    whatCooking = whatCooking_input.getText().toString();
                    place = place_input.getText().toString();
                    description = description_input.getText().toString();
                    time = time_input.getText().toString();

                if (price != 0) {
                    price = Integer.valueOf(price_input.getText().toString());
                } else {
                    price = 0;
                }

                    Event event = new Event(ID, dist, eventName, whatCooking, place, description, time, price);

                    confirmPost(event);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.aboutUs_id:
                Toast.makeText(this, "About us clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_id:
                Toast.makeText(this,"go to chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addRole_id:
//Send this to poul and paste here the popUp code
                //startActivity(new Intent(Map_and_List.this, PopUpRole.class));
                Toast.makeText(this,"Open AddRole PopUp", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);

    }

    //Code for listening to buttons in the drawer menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.logout_id:
                Toast.makeText(this, "log out code", Toast.LENGTH_SHORT).show();
                break;
            case R.id.goToRoom_id:
                // Toast.makeText(this, "Go to room test", Toast.LENGTH_SHORT).show();
                openTehSignup();
                break;
            case R.id.profile_id:
                openProfile();
                //Toast.makeText(this, "Go to profile", Toast.LENGTH_SHORT).show();
                break;

        }
        return false;
    }

    public void openProfile() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void openTehSignup() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void confirmPost(final Event event) {
        final AlertDialog.Builder confirm = new AlertDialog.Builder(this);
        confirm.setMessage("Are you sure you want to create this event?");
        confirm.setCancelable(false);

        confirm.setPositiveButton("Aye", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Host.this, MainActivity.class); // Create intent to send Parcel to Map and List
                intent.putExtra("com.package.eventObject", event);
                startActivity(intent);
            }
        });
        confirm.setNegativeButton("Nah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirm.setCancelable(true);
            }
        });
        confirm.create().show();
    }

    public void showTimePicker(View v) {
        DialogFragment newFragment = new TimePickerFragment(time_input);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    /* Class for Time Picker Dialog */
    public class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        TextView frag_time_input;

        public TimePickerFragment(TextView textView) {
            frag_time_input = textView;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        /*Function for getting time from the time picker dialog,
        then printing it in the textView
         */
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            /* Turn the time integers into strings, and then split it up
            to add zeroes in front of 1 digit time (eg. 8:2 should be 08:02)
             */
            String timeTemp = valueOf(hourOfDay).toString() + ":" + valueOf(minute).toString();
            String[] tempTime = timeTemp.split(":");

            if(tempTime[0].length() < 2){
                tempTime[0] = "0"+tempTime[0];
            } else {
                tempTime[0] = tempTime[0];
            }
            if (tempTime[1].length() < 2){
                tempTime[1] = "0"+tempTime[1];
            } else {
                tempTime[1] = tempTime[1];
            }

            //Assemble new string with necessary zeroes, and print to textView
            String time = tempTime[0] + ":" + tempTime[1];
            frag_time_input.setText(time);
        }
    }

}




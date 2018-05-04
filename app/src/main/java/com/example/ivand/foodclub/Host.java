package com.example.ivand.foodclub;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class Host extends AppCompatActivity {



    String eventName, whatCooking, place, description, time;
    int price = 0;
    int ID = 0;
    int dist = 5;
    //missing input and variables for the time


    EditText eventName_input;
    EditText whatCooking_input;
    EditText place_input;
    EditText description_input;
    EditText price_input;

    TextView time_input;

    Button post_event_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

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
                //DO LATER - Functionality for accepting the event creation
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




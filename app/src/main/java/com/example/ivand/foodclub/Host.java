package com.example.ivand.foodclub;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Host extends AppCompatActivity {



    String eventName, whatCooking, place, description;
    int price;
    int ID = 0;
    int dist = 5;
    //missing input and variables for the time

    EditText eventName_input;
    EditText whatCooking_input;
    EditText place_input;
    EditText description_input;
    EditText price_input;

    Button post_event_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

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

                    if(price != 0) {
                        price = Integer.valueOf(price_input.getText().toString());
                    }else{
                        price = 0;
                    }

                    Event event = new Event(ID, dist, eventName, whatCooking, place, description, price);

                    confirmPost(event);
            }
        });

    } private void confirmPost(final Event event){
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


}




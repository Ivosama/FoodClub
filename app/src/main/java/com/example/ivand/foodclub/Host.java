package com.example.ivand.foodclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Host extends AppCompatActivity {

    String eventName, whatCooking, place, description;
    int price;
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
                price = Integer.valueOf(price_input.getText().toString());

            }
        });
    }
}

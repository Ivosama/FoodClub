package com.example.ivand.foodclub;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class viewEvent extends AppCompatActivity {

    private TextView title = (TextView)findViewById(R.id.eventTitleText);
    private TextView food = (TextView)findViewById(R.id.eventFoodText);
    private TextView place = (TextView)findViewById(R.id.eventPlaceText);
    private TextView time = (TextView)findViewById(R.id.eventTimeText);
    private TextView price = (TextView)findViewById(R.id.eventTitleText);
    private TextView description = (TextView)findViewById(R.id.eventTitleText);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Event receivedEvent = new Event();
        Bundle bundle = getIntent().getExtras();
        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
        } catch (Exception e) {

        }

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

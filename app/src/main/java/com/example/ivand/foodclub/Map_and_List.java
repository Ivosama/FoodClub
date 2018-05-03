package com.example.ivand.foodclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Map_and_List extends AppCompatActivity {

    ListView listView;
    List list;
    ArrayAdapter adapter;

    public ArrayList<Event> eventArrayList = new ArrayList<Event>();

    {
        list = new ArrayList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and__list);


        Bundle bundle = getIntent().getExtras();


        try {
            eventArrayList = bundle.getParcelableArrayList("com.package.eventObjectList");
        } catch (Exception e) {

        }

        listView = (ListView) findViewById(R.id.eventList);
        for (int i = 0; i < eventArrayList.size(); i++) {
            Event tempEvent = eventArrayList.get(i);

            String tempName = tempEvent.getName();
            list.add(tempName);
        }

        adapter = new ArrayAdapter(Map_and_List.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Object[] eventList = new Object[list.size()];
        eventList = list.toArray(eventList);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Clicked this " + position, Toast.LENGTH_LONG);
                //Toast.makeText(Map_and_List.this, eventArrayList[6].name, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(Map_and_List.this, viewEvent.class); // Create intent to send Parcel to Map and List
                Event event = eventArrayList.get(position);
                intent.putExtra("com.package.eventObject", event);
                //Toast.makeText(getApplicationContext(), "position = " + position + " name = " + event.name, Toast.LENGTH_LONG).show();
                startActivity(intent);
                //openViewEvent();
            }


        });

    }

    public void openViewEvent(){
        Intent intent = new Intent(this, viewEvent.class);
        startActivity(intent);
    }
}

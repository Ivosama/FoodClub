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

        Event fakeEvent1 = new Event("Event number1");
        Event fakeEvent2 = new Event("Event number2 motherfucker");
        eventArrayList.add(fakeEvent1);
        eventArrayList.add(fakeEvent2);
        Event receivedEvent = new Event();
        Bundle bundle = getIntent().getExtras();
        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
            eventArrayList.add(receivedEvent);
        } catch (Exception e) {

        }

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
        /*
        list.add("Fuck off");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        list.add("Fuck off some more");
        */
        adapter = new ArrayAdapter(Map_and_List.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Object[] eventList = new Object[list.size()];
        eventList = list.toArray(eventList);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Map_and_List.this, eventList[position], Toast.LENGTH_SHORT).show();
                openViewEvent();
            }


        });

    }

    public void openViewEvent(){
        Intent intent = new Intent(this, viewEvent.class);
        startActivity(intent);
    }
}

package com.example.ivand.foodclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Map_and_List extends AppCompatActivity {

    ListView listView;
    List list;
    ArrayAdapter adapter;

    {
        list = new ArrayList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and__list);

        listView = (ListView) findViewById(R.id.eventList);
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

        adapter = new ArrayAdapter(Map_and_List.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }
}

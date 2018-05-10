package com.example.ivand.foodclub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Map_and_List extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    List list;
    ArrayAdapter adapter;
    User user = new User();

    public static boolean userApplied, userInEvent, userIsHosting, userExist;

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    Button btnSort;

    public ArrayList<Event> eventArrayList = new ArrayList<Event>();
    public ArrayList<User> userArrayList = new ArrayList<>();
    public ArrayList<Event> sortedEvents = new ArrayList<>();

    {
        list = new ArrayList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_map);

// start of implementation of toolbar

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

        if (MainActivity.userApplied || Host.userApplied || Map_and_List.userApplied) {
            getSupportActionBar().setTitle("Awaiting confirmation...");
        } else if (MainActivity.userIsHosting || Host.userIsHosting || Map_and_List.userIsHosting) {
            getSupportActionBar().setTitle("Waiting for users...");
        } else {
            getSupportActionBar().setTitle("Food Club");
        }

        Bundle bundle = getIntent().getExtras();

        try {
            eventArrayList = bundle.getParcelableArrayList("com.package.eventObjectList");
        } catch (Exception e) {

        }
        try {
            user = bundle.getParcelable("com.package.userObject");
        } catch (Exception e) {

        }
        try {
            userArrayList = bundle.getParcelableArrayList("com.package.userArray");
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

        Button btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                for (int i = 0; i < eventArrayList.size(); i++) {
                    Event tempEvent = eventArrayList.get(i);
                    for (int j = 1; j < i; j++) {
                        Event tempEvent2 = eventArrayList.get(j);

                        if (tempEvent.price > tempEvent2.price) {
                            String tempName = tempEvent.getName();
                            list.add(tempName);
                        } else {
                            String tempName2 = tempEvent2.getName();
                            list.add(tempName2);
                        }
                    }
//                Comparator<Event> compare = new Comparator<Event>() {
//                    @Override
//                    public int compare(Event o1, Event o2) {
//                        if (o1.price < o2.price)
//                            list.add(o2);
//                        return o1.price;
//
//                    }
//                };
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Clicked this " + position, Toast.LENGTH_LONG);
                //Toast.makeText(Map_and_List.this, eventArrayList[6].name, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(Map_and_List.this, viewEvent.class); // Create intent to send Parcel to Map and List
                Event event = eventArrayList.get(position);
                intent.putExtra("com.package.eventObject", event);
                User tempUser = user;
                intent.putExtra("com.package.userObject", tempUser);
                intent.putExtra("com.package.userArray", userArrayList);
                Toast.makeText(getApplicationContext(), "position = " + position + " name = " + event.name, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu_map, menu);
//
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.aboutUs_id:
                Toast.makeText(this, "About us clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_id:
                userInEvent = true;
                openChat();
                Toast.makeText(this, "go to chat", Toast.LENGTH_SHORT).show();
                break;
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

    public void openChat() {
        if (userInEvent) {
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You haven't join an event", Toast.LENGTH_SHORT).show();
        }
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openTehSignup() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}

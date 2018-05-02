package com.example.ivand.foodclub;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    ImageButton imgBtnEat;
    ImageButton imgBtnHost;
    ImageButton imageProfile;

    public ArrayList<Event> eventArrayListMain = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ARRAY STUFF
        Event fakeEvent1 = new Event("Zephan's event");
        Event fakeEvent2 = new Event("Poul's event");

        eventArrayListMain.add(fakeEvent1);
        eventArrayListMain.add(fakeEvent2);

        Event receivedEvent = new Event();
        Bundle bundle = getIntent().getExtras();
        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
            eventArrayListMain.add(receivedEvent);
        } catch (Exception e) {

        }


        // END OF ARRAY STUFF

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        imageProfile = (ImageButton) findViewById(R.id.imageProfile);
        imgBtnEat = (ImageButton) findViewById(R.id.img_btn_eat);
        imgBtnHost = (ImageButton) findViewById(R.id.img_btn_host);

        imgBtnEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapAndList();
            }
        });

        imgBtnHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHost();
            }
        });

    ////this is changed
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based  on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        mDrawerLayout.addDrawerListener(


                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened

                        openProfile();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }


                }


        );

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMapAndList(){
        Intent intent = new Intent(MainActivity.this, Map_and_List.class); // Create intent to send Parcel to Map and List
        intent.putExtra("com.package.eventObjectList", eventArrayListMain);
        startActivity(intent);
    }

    public void openHost(){
        Intent intent = new Intent(this, Host.class);
        startActivity(intent);
    }
    public void openProfile() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }



}

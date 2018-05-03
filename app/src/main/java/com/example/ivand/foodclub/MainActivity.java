package com.example.ivand.foodclub;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    public boolean userApplied, userIsHosting;

    ImageButton imgBtnEat;
    ImageButton imgBtnHost;
    ImageButton imageProfile;

    public ArrayList<Event> eventArrayListMain = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ARRAY STUFF

        Event fakeEvent1 = new Event(0, 1, "Poul's Event motherfuckers!", "Pasta ala Poul", "Poul's place, which is very nice and large and good and the windows are oh so fine. The place is located in Nørresundby which is kindof not 10 minutes from basis", "Hey all! Come eat some of my delicious pasta. Oh, and btw - I am wild!", 5);
        Event fakeEvent2 = new Event("Zephan's event is this one");

        Role fakeRole1 = new Role("DishWasher");
        Role fakeRole2 = new Role("Musician");
        fakeRole1.holderID = "Poul";
        fakeEvent1.roles[0] = fakeRole1;
        fakeEvent2.roles[0] = fakeRole2;

        eventArrayListMain.add(fakeEvent1);
        eventArrayListMain.add(fakeEvent2);
        File file = new File(getApplicationContext().getFilesDir(),"userEvent");
        if(file.exists()) {
            String[] separated = loadSave().split("`");
            Event userEvent = new Event(Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), separated[2], separated[3], separated[4], separated[5], Integer.valueOf(separated[6]));
            eventArrayListMain.add(userEvent);
        }


        Event receivedEvent = new Event();
        Bundle bundle = getIntent().getExtras();
        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
            eventArrayListMain.add(receivedEvent);
        } catch (Exception e) {

        }

        // END OF ARRAY STUFF

        setContentView(R.layout.navigation_drawer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout = findViewById(R.id.drawer_layout);

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
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();


/*
        drawerLayout.addDrawerListener(

                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                        openTehSignup();
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

        );*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
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
   // public void openProfile() {
    //    Intent intent = new Intent(this,ProfileActivity.class);
   //     startActivity(intent);
   // }
   public void openTehSignup() {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

   public String loadSave(){
       String temp = readFromFile(getBaseContext());
       return temp;
       }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("userEvent");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
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


        }
        return super.onOptionsItemSelected(item);
    }

}

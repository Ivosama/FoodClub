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
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        Event fakeEvent1 = new Event(0, 1, "Poul's Event motherfuckers!", "Pasta ala Poul", "Poul's place, which is very nice and large and good and the windows are oh so fine. The place is located in Nørresundby which is kindof not 10 minutes from basis", "Hey all! Come eat some of my delicious pasta. Oh, and btw - I am wild!","12:30", 5);
        Event fakeEvent2 = new Event(1, 1, "BASISBAR TODAY!", "Beers, en masse!", "BasisBar, of course!", "Fucking Basisbar, what more is there to say?!?!?", "14:00",  10);

        Role fakeRole1 = new Role("DishWasher");
        Role fakeRole2 = new Role("Musician");

        //fakeEvent1.roles.add(fakeRole1);
        fakeEvent2.roles.add(fakeRole1);

        eventArrayListMain.add(fakeEvent1);
        eventArrayListMain.add(fakeEvent2);


        File file = new File(getApplicationContext().getFilesDir(),"userEvent");
        if(file.exists()) {
            String[] separated = loadSave().split("`");
            Event userEvent = new Event(Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), separated[2], separated[3], separated[4], separated[5], separated[6], Integer.valueOf(separated[7]));
            if(!eventArrayListMain.contains(userEvent)) {
                eventArrayListMain.add(userEvent);
            }

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

        getSupportActionBar().setTitle("Food Club");

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
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

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
    public void openProfile() {
       Intent intent = new Intent(this,ProfileActivity.class);
       startActivity(intent);
    }
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

    public void saveEvent(String ID, String dist, String eventName, String whatCooking, String place, String description, String price){
        File file = new File(getFilesDir(), "userEvent");
        String frank = "";
        frank += (ID + "`");
        frank += (dist + "`");
        frank += (eventName + "`");
        frank += (whatCooking + "`");
        frank += (place + "`");
        frank += (description + "`");
        frank += (price + "`");
        writeToFile(frank, getBaseContext());
    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("userEvent", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
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
            case R.id.addRole_id:
//Send this to poul and paste here the popUp code
                eventArrayListMain.get(0).addRole(new Role());
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.logout_id:
                Toast.makeText(this, "log out code", Toast.LENGTH_SHORT).show();
                break;
            case R.id.goToRoom_id:
                Toast.makeText(this, "Go to room test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_id:
                openProfile();
                //Toast.makeText(this, "Go to profile", Toast.LENGTH_SHORT).show();
                break;

        }
        return false;
    }
}

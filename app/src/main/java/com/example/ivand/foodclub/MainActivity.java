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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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

    private User user;
    private boolean isLoggedIn;

    public boolean userApplied, userInEvent, userIsHosting, userExist;

    ImageButton imgBtnEat;
    ImageButton imgBtnHost;
    ImageButton imageProfile;

    public ArrayList<Event> eventArrayListMain = new ArrayList<Event>();
    public ArrayList<User> userArrayListMain = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Event receivedEvent = new Event();

        Bundle bundle = getIntent().getExtras();
        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
            //eventArrayListMain.add(receivedEvent);
            saveEvent(receivedEvent);
        } catch (Exception e) {

        }
        try {
            user = bundle.getParcelable("com.package.userObject");
            Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            user = new User(1);
        }




        try {

        } catch (Exception e) {

        }

        //PROFILE STUFF

        /*
        if (user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }*/


        // ARRAY STUFF

        Event fakeEvent1 = new Event(1, 1,5,  "Poul's Event motherfuckers!", "Pasta ala Poul", "Poul's place, which is very nice and large and good and the windows are oh so fine. The place is located in Nørresundby which is kindof not 10 minutes from basis", "Hey all! Come eat some of my delicious pasta. Oh, and btw - I am wild!","12:30", 5);
        //fakeEvent1.ownerID = 1;
        Event fakeEvent2 = new Event(1, 2, 1, "BASISBAR TODAY!", "Beers, en masse!", "BasisBar, of course!", "Fucking Basisbar, what more is there to say?!?!?", "14:00",  10);
        //fakeEvent2.ownerID = 2;

        User fakeUser = new User(1,"abc","abc","1234","no","2@2.com","caca");
        Role fakeRole1 = new Role("DishWasher");
        Role fakeRole2 = new Role("Musician");

        fakeEvent1.roles.add(fakeRole1);
        fakeEvent1.roles.add(fakeRole2);
        fakeEvent2.roles.add(fakeRole1);

        eventArrayListMain.add(fakeEvent1);
        eventArrayListMain.add(fakeEvent2);

        for (int i = 0; i < eventArrayListMain.size(); i++) {
            int removalID;
            if (eventArrayListMain.get(i).getOwnerID() == 0) {
                removalID = eventArrayListMain.get(i).getID();
                for (int j = 0; j < eventArrayListMain.size(); j++) {
                    if (eventArrayListMain.get(i).getID() == removalID) {
                        eventArrayListMain.remove(i);
                    }
                }
            }
        }

        File file = new File(getApplicationContext().getFilesDir(),"userEvent");
        if(file.exists()) {
            String[] separated = loadSave().split("`");
            Event userEvent = new Event(Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Integer.valueOf(separated[2]), separated[3], separated[4], separated[5], separated[6], separated[7], Integer.valueOf(separated[8]));
            //Event userEvent = new Event(Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Integer.valueOf(separated[2]), separated[3], separated[4], separated[5], separated[6], separated[7], Integer.valueOf(separated[8]));
            if(!eventArrayListMain.contains(userEvent)) {
                eventArrayListMain.add(userEvent);
                int roleCount = 0;  //new int for counting how many roles in this saved event
                roleCount = separated.length - 9;   //roleCount becomes the remaining elements in the array after event-related elements are removed
                if (roleCount % 4 == 0)     //makes sure it's not fucked
                roleCount = roleCount / 4;      //Divides itself by 4, because each role has 4 elements.
                for(int i = 0; i < roleCount; i++){
                    Role tempRole = new Role(Integer.valueOf(separated[(10 + (i*4))]), separated[(11 + (i*4))], Integer.valueOf(separated[(12 + (i*4))]), Boolean.parseBoolean(separated[(13 + (i*4))]));     //ID, name, holderID, taken
                    fakeEvent1.roles.add(tempRole);
                }
            }

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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_main, menu);

        return true;
    }*/

    public void openMapAndList(){
        Intent intent = new Intent(MainActivity.this, Map_and_List.class); // Create intent to send Parcel to Map and List
        intent.putExtra("com.package.eventObjectList", eventArrayListMain);
        intent.putExtra("com.package.userObject", user);
        intent.putExtra("com.package.userArray", userArrayListMain);
        startActivity(intent);
    }

    public void openHost(){
        Intent intent = new Intent(this, Host.class);
        intent.putExtra("com.package.userObject", user);
        startActivity(intent);
    }
    public void openProfile() {
       Intent intent = new Intent(this,ProfileActivity.class);
       intent.putExtra("com.package.userObject", user);
       startActivity(intent);
    }
   public void openTehSignup() {
        Intent intent = new Intent(this,LoginActivity.class);
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

    public void saveEvent(Event event){
        File file = new File(getFilesDir(), "userEvent");
        String frank = "";
        frank += (event.ID + "`");
        frank += ("1`");
        frank += (event.dist + "`");
        frank += (event.name + "`");
        frank += (event.menu + "`");
        frank += (event.place + "`");
        frank += (event.description + "`");
        frank += (event.time + "`");
        frank += (event.price + "`");
        for(int i = 0; i < event.roles.size(); i++){
            frank += (Integer.toString(event.roles.get(i).getRoleID()) + "`");
            frank += (event.roles.get(i).getTitle() + "`");
            frank += (Integer.toString(event.roles.get(i).getHolderID()) + "`");    //converts IDs to strings and throws them at Frank
            frank += (Boolean.toString(event.roles.get(i).isTaken) + "`");  //gets isTaken bool, converts to string, gives to Frank
        }
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
//Code for listening to the menu bottons in tooldbar
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
}

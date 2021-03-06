package com.example.ivand.foodclub;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    private User user;
    private boolean isLoggedIn;

    public static boolean userApplied = false, userInEvent = false, userIsHosting = false;
    public static int eventJoinedID = 0;

    ImageButton imgBtnEat;
    ImageButton imgBtnHost;
    ImageButton imageProfile;

    int tempRoleID = 0;
    int tempEventID = 0;
    private  boolean isAccepted = true;

    public ArrayList<Event> eventArrayListMain = new ArrayList<Event>();
    public ArrayList<User> userArrayListMain;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Event receivedEvent = new Event();

        Bundle bundle = getIntent().getExtras();

        userArrayListMain = new ArrayList<User>();

        try {
            user = bundle.getParcelable("com.package.userObject");
            //Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            user = new User(1);
        }
        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
            //eventArrayListMain.add(receivedEvent);
            if (receivedEvent.ownerID != 0) {
                if (receivedEvent.ownerID == user.id) {
                    saveEvent(receivedEvent);
                }

            }

        } catch (Exception e) {

        }

        //PROFILE STUFF

        /*
        if (user == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }*/


        // ARRAY STUFF

        Event fakeEvent1 = new Event(2, 3, 5, "Italian Food", "Pasta", "Wharfroad 27", "Hey all! Come eat some of my delicious pasta. Oh, and btw - I am wild!", "18:20", 5);
        //fakeEvent1.ownerID = 1;
        /*if (receivedEvent.getOwnerID() == 0 && receivedEvent.ID == fakeEvent1.ID) {
            fakeEvent1.ownerID = 0;
        }*/
        Event fakeEvent2 = new Event(3, 4, 1, "The best dinner you'll ever go to", "Beers, en masse! Oh, and maybe some food", "Strettstreet 4", "Going to eat some food, but mostly drink beers", "14:00", 10);
        Event fakeEvent3 = new Event(4, 5, 1, "Andrei's Mititei Extravaganza", "Mititeis with a LOT of mustard", "Kanalstein 19", "There will be so much food you will even have extra to take home to your dog", "10:00", 7);
        Event fakeEvent4 = new Event(5, 10, 80, "Cool Mongolian Food", "Buuz", "Karl den stores gade 20", "We're going to eat some mongolian cuissine, primarily Buuz. Please join!", "20:00", 25);
        Event fakeEvent5 = new Event(6, 12, 50, "Simple quick pasta", "Pasta with meat sauce", "Bonnesensgade 9", "I made way too much sauce, come take some please", "17:00", 15);
        //fakeEvent2.ownerID = 2;

        User fakeUser = new User(2, "abc", "abc", "1234", "no", "2@2.com", "caca");
        User fakeUser2 = new User(5, "Analyn", "Krem", "1234", "Peanuts", "horny@slut.com", "wakadoodle");
        User fakeUser3 = new User(6, "Azucar", "Moreno", "1234", "Cold Water", "sweet@candy.com", "slurp slurp");
        User fakeUser4 = new User(7, "Bambi", "Nomom", "1234", "Poachers", "lost@jungle.com", "oh! boom");
        User fakeUser5 = new User(8, "Rabbit", "Ribbit", "1234", "Frogs and carrots", "nomnom@rr.com", "Supelhelo");
        User fakeUser6 = new User(9, "Tronald", "Dump", "1234", "Beans, noodles", "great@America.com", "I require additional walls");
//Adding users to the array list
        userArrayListMain.add(fakeUser);
        userArrayListMain.add(fakeUser2);
        userArrayListMain.add(fakeUser3);
        userArrayListMain.add(fakeUser4);
        userArrayListMain.add(fakeUser5);
        userArrayListMain.add(fakeUser6);

//testing size of array list user
        for (int i = 0; i < userArrayListMain.size(); i++) {
            Log.d(TAG, "onCreate: " + userArrayListMain.get(i));
        }


        Role fakeRole1 = new Role("Dishwasher");
        fakeRole1.isTaken = true;
        Role fakeRole2 = new Role("Cook helper");
        Role fakeRole3 = new Role("Guest");
        Role fakeRole4 = new Role("Dog");

        fakeEvent1.roles.add(fakeRole1);
        fakeEvent1.roles.add(fakeRole2);
        fakeEvent1.roles.add(fakeRole3);
        fakeEvent2.roles.add(fakeRole3);
        fakeEvent2.roles.add(fakeRole1);
        fakeEvent4.roles.add(fakeRole1);
        fakeEvent4.roles.add(fakeRole3);
        fakeEvent4.roles.add(fakeRole3);
        fakeEvent4.roles.add(fakeRole2);
        fakeEvent3.roles.add(fakeRole2);
        fakeEvent3.roles.add(fakeRole1);
        fakeEvent3.roles.add(fakeRole3);
        fakeEvent5.roles.add(fakeRole1);
        fakeEvent5.roles.add(fakeRole3);
        fakeEvent5.roles.add(fakeRole3);
        fakeEvent5.roles.add(fakeRole2);

        eventArrayListMain.add(fakeEvent1);
        eventArrayListMain.add(fakeEvent2);
        eventArrayListMain.add(fakeEvent3);
        eventArrayListMain.add(fakeEvent4);
        eventArrayListMain.add(fakeEvent5);
        if (receivedEvent.getOwnerID() == 0) {
            for (int i = 0; i < eventArrayListMain.size(); i++) {
                if (receivedEvent.ID == eventArrayListMain.get(i).ID) {
                    eventArrayListMain.get(i).ownerID = 0;
                }
            }
        }
        if (receivedEvent.ownerID != user.id) {
            for (int i = 0; i < eventArrayListMain.size(); i++) {
                if (receivedEvent.getID() == eventArrayListMain.get(i).getID()) {
                    eventArrayListMain.set(i, receivedEvent);
                }

            }
        }
        // Remove / Add stuff from eventArrayListMain
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

        File file = new File(getApplicationContext().getFilesDir(), "userEvent");
        if (file.exists()) {
            String[] separated = loadSave().split("`");
            Event userEvent = new Event(Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Integer.valueOf(separated[2]), separated[3], separated[4], separated[5], separated[6], separated[7], Integer.valueOf(separated[8]));
            //Event userEvent = new Event(Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Integer.valueOf(separated[2]), separated[3], separated[4], separated[5], separated[6], separated[7], Integer.valueOf(separated[8]));
            if (!eventArrayListMain.contains(userEvent)) {
                eventArrayListMain.add(userEvent);
                int roleCount = 0;  //new int for counting how many roles in this saved event
                roleCount = separated.length - 9;   //roleCount becomes the remaining elements in the array after event-related elements are removed
                if (roleCount % 4 == 0) {//makes sure it's not fucked
                    roleCount = roleCount / 4;      //Divides itself by 4, because each role has 4 elements.
                    for (int i = 0; i < roleCount; i++) {
                        Role tempRole = new Role(Integer.valueOf(separated[(9 + (i * 4))]), separated[(10 + (i * 4))], Integer.valueOf(separated[(11 + (i * 4))]), Boolean.parseBoolean(separated[(12 + (i * 4))]));     //ID, name, holderID, taken
                        userEvent.roles.add(tempRole);

                    }
                }
            }
            MainActivity.userIsHosting = true;
            Host.userIsHosting = true;
            Map_and_List.userIsHosting = true;

            // Countdown for users to join
            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {

                }

                Random r = new Random();
                int i = r.nextInt(userArrayListMain.size() - 0);
                public void onFinish() {

                    User randomUser = new User();
                    randomUser = userArrayListMain.get(i);
                    confirmUser(randomUser);
                }

            }.start();
            new CountDownTimer(21000, 1000) {
                public void onTick(long millisUntilFinished) {

                }
                Random r = new Random();
                int i = r.nextInt(userArrayListMain.size() - 0);


                public void onFinish() {

                    User randomUser = new User();
                    randomUser = userArrayListMain.get(i);
                    confirmUser(randomUser);
                }

            }.start();
        }

        if (!isAccepted) {

        }

        // END OF ARRAY STUFF

        setContentView(R.layout.navigation_drawer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        drawerLayout = findViewById(R.id.drawer_layout);

        if (MainActivity.userApplied || Host.userApplied) {
            getSupportActionBar().setTitle("Awaiting confirmation...");

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            userAccepted();
                            getSupportActionBar().setTitle("Food Club");
                            MainActivity.userApplied = false;
                            Host.userApplied = false;
                            Map_and_List.userApplied = false;
                        }
                    }, 5000);
        } else if (MainActivity.userIsHosting || Host.userIsHosting) {

            getSupportActionBar().setTitle("Waiting for users...");

            for (int k = 0; k < eventArrayListMain.size(); k++) {
                if (eventArrayListMain.get(k).getID() == user.getId()) {
                    tempEventID = k;
                    for (int m = 0; m < eventArrayListMain.get(k).roles.size(); m++) {
                        if (eventArrayListMain.get(k).roles.get(m).isTaken == true) {
                            getSupportActionBar().setTitle("Your event is full");
                            Map_and_List.userIsHosting = false;
                        }
                    }
                }
            }
        } else {
            getSupportActionBar().setTitle("Food Club");
        }


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
                if (userIsHosting == true) {  //puts user in their event if they already have one
                    Intent intent = new Intent(MainActivity.this, viewEvent.class); // Create intent to send Parcel to Map and List
                    Event event = eventArrayListMain.get(eventArrayListMain.size() - 1);  //last thing in the list
                    intent.putExtra("com.package.eventObject", event);
                    User tempUser = user;
                    intent.putExtra("com.package.userObject", tempUser);
                    intent.putExtra("com.package.userArray", userArrayListMain);
                    startActivity(intent);
                }
                else if (userApplied == true){
                    Toast toast = Toast.makeText(getApplicationContext(), "You've already joined an event!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    openHost();
                }
            }
        });

        ////this is changed
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);

        toggle.syncState();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_main, menu);

        return true;
    }

    public void openMapAndList() {
        Intent intent = new Intent(MainActivity.this, Map_and_List.class); // Create intent to send Parcel to Map and List
        intent.putExtra("com.package.eventObjectList", eventArrayListMain);
        intent.putExtra("com.package.userObject", user);
        intent.putExtra("com.package.userArray", userArrayListMain);
        startActivity(intent);
    }

    public void openHost() {
        Intent intent = new Intent(this, Host.class);
        intent.putExtra("com.package.userObject", user);
        startActivity(intent);
    }

    public void openProfile(User user) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("com.package.userObject", user);
        startActivity(intent);
    }

    public void openTehSignup() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public String loadSave() {
        String temp = readFromFile(getBaseContext());
        return temp;
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("userEvent");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public void saveEvent(Event event) {
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
        for (int i = 0; i < event.roles.size(); i++) {
            frank += (Integer.toString(event.roles.get(i).getRoleID()) + "`");
            frank += (event.roles.get(i).getTitle() + "`");
            frank += (Integer.toString(event.roles.get(i).getHolderID()) + "`");    //converts IDs to strings and throws them at Frank
            frank += (Boolean.toString(event.roles.get(i).isTaken) + "`");  //gets isTaken bool, converts to string, gives to Frank
        }
        writeToFile(frank, getBaseContext());
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("userEvent", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    //Code for listening to the menu bottons in tooldbar
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

    public void openChat() {
        if (userInEvent) {
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You haven't joined an event", Toast.LENGTH_SHORT).show();
        }
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
                if (userIsHosting == true) {  //puts user in their event if they already have one
                    Intent intent = new Intent(MainActivity.this, viewEvent.class); // Create intent to send Parcel to Map and List
                    Event event = eventArrayListMain.get(eventArrayListMain.size() - 1);  //last thing in the list
                    intent.putExtra("com.package.eventObject", event);
                    User tempUser = user;
                    intent.putExtra("com.package.userObject", tempUser);
                    intent.putExtra("com.package.userArray", userArrayListMain);
                    startActivity(intent);
                }
                else if (userApplied == true || userInEvent == true){
                    Intent intent = new Intent (MainActivity.this, viewEvent.class);
                    int tempEventID = 0;
                    for(int i = 0; i < eventArrayListMain.size(); i++){
                        if (eventArrayListMain.get(i).ID == eventJoinedID){
                            tempEventID = i;
                        }
                    }
                    Event event = eventArrayListMain.get(tempEventID);
                    intent.putExtra("com.package.eventObject", event);
                    User tempUser = user;
                    intent.putExtra("com.package.userObject", tempUser);
                    intent.putExtra("com.package.userArray", userArrayListMain);
                    startActivity(intent);
                }
                else {
                    openHost();
                }
                break;
            case R.id.profile_id:
                openProfile(userArrayListMain.get(0));
                //Toast.makeText(this, "Go to profile", Toast.LENGTH_SHORT).show();
                break;

        }
        return false;
    }

    // DIALOG bOX FOR CONFIRMING RANDOM USERS JOINING
    private void confirmUser(final User randomUser) {

        for (int k = 0; k < eventArrayListMain.size(); k++) {
            if (eventArrayListMain.get(k).getID() == user.getId()) {
                tempEventID = k;
                for (int m = 0; m < eventArrayListMain.get(k).roles.size(); m++) {
                    if (eventArrayListMain.get(k).roles.get(m).isTaken == false) {
                        final AlertDialog.Builder confirmUser = new AlertDialog.Builder(this);

                        Role tempRole = new Role();

                        tempRole.title = " ";
                        for (int i = 0; i < eventArrayListMain.size(); i++) {
                            if (eventArrayListMain.get(i).getID() == user.getId()) {
                                tempEventID = i;
                                for (int j = 0; j < eventArrayListMain.get(i).roles.size(); j++) {
                                    if (eventArrayListMain.get(i).roles.get(j).isTaken == false) {
                                        tempRole = eventArrayListMain.get(i).roles.get(j);
                                        tempRoleID = j;

                                    }
                                }
                            }
                        }

                        confirmUser.setMessage("User " + randomUser.firstName + " " + randomUser.lastName + " wants to join your event as a " + tempRole.title  );
                        confirmUser.setCancelable(false);



                        confirmUser.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                eventArrayListMain.get(tempEventID).roles.get(tempRoleID).setHolderID(randomUser.getId());
                                eventArrayListMain.get(tempEventID).roles.get(tempRoleID).isTaken = true;
                                saveEvent(eventArrayListMain.get(tempEventID));

                                for (int k = 0; k < eventArrayListMain.size(); k++) {
                                    if (eventArrayListMain.get(k).getID() == user.getId()) {
                                        tempEventID = k;
                                        for (int m = 0; m < eventArrayListMain.get(k).roles.size(); m++) {
                                            if (eventArrayListMain.get(k).roles.get(m).isTaken == true) {
                                                getSupportActionBar().setTitle("Your event is full");
                                                Map_and_List.userIsHosting = false;
                                            }
                                        }
                                    }
                                }

                            }
                        });

                        confirmUser.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                confirmUser.setCancelable(true);
                            }
                        });

                        confirmUser.setNeutralButton("View profile", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        final AlertDialog dialog = confirmUser.create();
                        dialog.show();
                        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean wantToCloseDialog = false;
                                if(wantToCloseDialog) {
                                    dialog.dismiss();
                                }
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                intent.putExtra("com.package.userObject", randomUser);
                                startActivity(intent);
                            }
                        });




                        //confirmUser.create().show();
                    }
                }
            }
        }
    }



    private void userAccepted() {
        final AlertDialog.Builder userAccepted = new AlertDialog.Builder(this);

        userAccepted.setMessage("You have been accepted in the event for which you applied!");
        userAccepted.setCancelable(false);
        userInEvent = true;
        userAccepted.setPositiveButton("Event Details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(MainActivity.this, viewEvent.class);
                for (int i = 0; i < eventArrayListMain.size(); i++) {
                    for (int j = 0; j < eventArrayListMain.get(i).roles.size(); j++) {
                        if (eventArrayListMain.get(i).roles.get(j).getHolderID() == user.getId()) {
                            tempEventID = i;
                        }
                    }
                }

                intent.putExtra("com.package.eventObject", eventArrayListMain.get(tempEventID));
                intent.putExtra("com.package.userObject", user);
                intent.putExtra("com.package.userArray", userArrayListMain);

                startActivity(intent);
                //Toast.makeText(MainActivity.this, "Take me to church", Toast.LENGTH_SHORT).show();
                //code with userInEvent;
            }
        });
        userAccepted.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userAccepted.setCancelable(true);
            }
        });
        userAccepted.create().show();
    }
    /*
    private void acceptUser() {
        final AlertDialog.Builder acceptUser = new AlertDialog.Builder(this);
        acceptUser.setMessage("You got a request to join your event.");
        acceptUser.setCancelable(false);

        acceptUser.setPositiveButton("Event Details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "The mtf was accepted", Toast.LENGTH_SHORT).show();
                //code with userInEvent;
            }
        });
        acceptUser.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "The user has been notified", Toast.LENGTH_SHORT).show();
                acceptUser.setCancelable(true);
            }
        });
        acceptUser.create().show();
    }*/
}


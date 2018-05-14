package com.example.ivand.foodclub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by PolleAnker
 */

public class PopUpRole extends Activity {

    Event receivedEvent = new Event();
    Role tempRole = new Role();

    Button finish_btn;

    String role1, role2, role3, role4, role5 = "";
    int amountRole1 = 0, amountRole2 = 0, amountRole3 = 0, amountRole4 = 0, amountRole5 = 0;
    int totalAmountRoles = 0;

    EditText role1_input, role2_input, role3_input, role4_input, role5_input;
    EditText amountRole1_input, amountRole2_input, amountRole3_input, amountRole4_input, amountRole5_input;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        try {
            receivedEvent = bundle.getParcelable("com.package.eventObject");
            Toast.makeText(getApplicationContext(), receivedEvent.toString(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

        }

        setContentView(R.layout.role_add_popup);

        finish_btn = (Button) findViewById(R.id.finish);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.widthPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * 1.1));


        role1_input = (EditText) findViewById(R.id.role1_input);

        role2_input = (EditText) findViewById(R.id.role2_input);
        role3_input = (EditText) findViewById(R.id.role3_input);
        role4_input = (EditText) findViewById(R.id.role4_input);
        role5_input = (EditText) findViewById(R.id.role5_input);


        amountRole1_input = (EditText) findViewById(R.id.amountRole1_input);

        amountRole2_input = (EditText) findViewById(R.id.amountRole2_input);
        amountRole3_input = (EditText) findViewById(R.id.amountRole3_input);
        amountRole4_input = (EditText) findViewById(R.id.amountRole4_input);
        amountRole5_input = (EditText) findViewById(R.id.amountRole5_input);


        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role1 = role1_input.getText().toString();

                role2 = role2_input.getText().toString();
                role3 = role3_input.getText().toString();
                role4 = role4_input.getText().toString();
                role5 = role5_input.getText().toString();

                amountRole1 = Integer.valueOf(amountRole1_input.getText().toString());
                amountRole2 = Integer.valueOf(amountRole2_input.getText().toString());
                amountRole3 = Integer.valueOf(amountRole3_input.getText().toString());
                amountRole4 = Integer.valueOf(amountRole4_input.getText().toString());
                amountRole5 = Integer.valueOf(amountRole5_input.getText().toString());
                /*
                if(amountRole1 != 0){
                    Toast.makeText(getApplicationContext(), "roles are more than 1", Toast.LENGTH_SHORT).show();
                    amountRole1 = Integer.valueOf(amountRole1_input.getText().toString());
                } else{
                    amountRole1 = 0;
                }*/



                totalAmountRoles = 0;
                int i;
                for (i = 0; i < amountRole1; i++) {
                    receivedEvent.addRole(new Role(i, role1, 0, false));
                    totalAmountRoles++;
                }

                for (i = 0; i < amountRole2; i++) {
                    receivedEvent.addRole(new Role(i + totalAmountRoles, role2, 0, false));
                    totalAmountRoles++;
                }
                for (i = 0; i < amountRole3; i++) {
                    receivedEvent.addRole(new Role(i + totalAmountRoles, role3, 0, false));
                    totalAmountRoles++;
                }
                for (i = 0; i < amountRole4; i++) {
                    receivedEvent.addRole(new Role(i + totalAmountRoles, role4, 0, false));
                    totalAmountRoles++;
                }
                for (i = 0; i < amountRole5; i++) {
                    receivedEvent.addRole(new Role(i + totalAmountRoles, role5, 0, false));
                    totalAmountRoles++;
                }
                /*
                for (int j = 0; j < totalAmountRoles; j++) {
                    Toast.makeText(getApplicationContext(), receivedEvent.roles.get(j).title, Toast.LENGTH_SHORT).show();
                }*/

                Intent intent = new Intent(PopUpRole.this, Host.class);
                intent.putExtra("com.package.eventObject", receivedEvent);
                startActivity(intent);

            }
        });

        role1_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                role1_input.setHint("");
                return false;
            }
        });

        role1_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    role1_input.setHint("Role Title");
                }
            }
        });

        role2_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                role2_input.setHint("");
                return false;
            }
        });

        role2_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    role2_input.setHint("Role Title");
                }
            }
        });

        role3_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                role3_input.setHint("");
                return false;
            }
        });

        role3_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    role3_input.setHint("Role Title");
                }
            }
        });

        role4_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                role4_input.setHint("");
                return false;
            }
        });

        role4_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    role4_input.setHint("Role Title");
                }
            }
        });

        role5_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                role5_input.setHint("");
                return false;
            }
        });

        role5_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    role5_input.setHint("Role Title");
                }
            }
        });
    }
}

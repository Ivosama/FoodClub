package com.example.ivand.foodclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileName;
    private TextView textUsername;
    private TextView textAllergies;
    private TextView textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = (TextView)findViewById(R.id.profileName);
        textUsername = (TextView)findViewById(R.id.textUserName);
        textAllergies = (TextView)findViewById(R.id.textAllergies);
        textDescription = (TextView)findViewById(R.id.textDescription);

        User receivedUser = new User();
        Bundle bundle = getIntent().getExtras();

        try {
            receivedUser = bundle.getParcelable("com.package.userObject");
            profileName.setText(receivedUser.getfirstName() + receivedUser.getLastName());
            textUsername.setText(receivedUser.getfirstName());
            textAllergies.setText(receivedUser.getAllergies());
            textDescription.setText(receivedUser.getDescription());
        }catch (Exception e){

            }
        }
    }


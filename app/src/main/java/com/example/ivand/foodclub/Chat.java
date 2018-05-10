package com.example.ivand.foodclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {

    ImageButton send;

    CircleImageView circleImageView;

    TextView chatName;
    String enterMessage, message1, message2, message3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        send = (ImageButton) findViewById(R.id.send_id);

        circleImageView = (CircleImageView) findViewById(R.id.imageProfile_chat);
        chatName = (TextView) findViewById(R.id.chat_name_id);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

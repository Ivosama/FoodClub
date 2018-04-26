package com.example.ivand.foodclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private Button button_eat;
    private Button button_host;
    ImageButton imgBtnEat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_eat = (Button) findViewById(R.id.Eat_button);
        button_host = (Button) findViewById(R.id.Host_button);

        imgBtnEat = (ImageButton) findViewById(R.id.img_btn_eat);

        imgBtnEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapAndList();
            }
        });

        button_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapAndList();
            }
        });

        button_host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHost();
            }
        });

    }

    public void openMapAndList(){
        Intent intent = new Intent(this, Map_and_List.class);
        startActivity(intent);
    }

    public void openHost(){
        Intent intent = new Intent(this, Host.class);
        startActivity(intent);
    }

}

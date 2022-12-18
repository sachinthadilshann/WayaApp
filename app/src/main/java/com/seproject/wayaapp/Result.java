package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Result extends AppCompatActivity {

    RelativeLayout llev1,llev2,llev3,llev4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        llev1 = (RelativeLayout) findViewById(R.id.lev1);
        llev2 = (RelativeLayout) findViewById(R.id.lev2);
        llev3 = (RelativeLayout) findViewById(R.id.lev3);
        llev4 = (RelativeLayout) findViewById(R.id.lev4);



        llev1.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Resultlevel1.class)));
        llev2.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Resultlevel2.class)));
        llev3.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Resultlevel3.class)));
        llev4.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Resultlevel4.class)));

    }
}
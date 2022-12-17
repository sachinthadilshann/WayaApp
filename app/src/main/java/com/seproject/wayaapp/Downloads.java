package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Downloads extends AppCompatActivity {

    RelativeLayout ll1,ll2,ll3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        ll1= (RelativeLayout) findViewById(R.id.lev1);
        ll3= (RelativeLayout) findViewById(R.id.lev3);



        ll1.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),level1.class)));
        ll3.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),level3.class)));


    }
}
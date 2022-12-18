package com.seproject.wayaapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.seproject.wayaapp.activities.SocietiesActivity;

public class MainActivity extends AppCompatActivity {
    RelativeLayout PProfile,DDownloads,NNotice,SSocieties,LLMS,AAbout,WWebsite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PProfile = (RelativeLayout) findViewById(R.id.profile);
        DDownloads =(RelativeLayout) findViewById(R.id.Downloads1);
        SSocieties =(RelativeLayout) findViewById(R.id.societies1);
        NNotice = (RelativeLayout) findViewById(R.id.notice1);
        LLMS = (RelativeLayout) findViewById(R.id.lms);
        WWebsite = (RelativeLayout) findViewById(R.id.website);
        AAbout= (RelativeLayout) findViewById(R.id.about1);



        PProfile.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Profile.class)));
        DDownloads.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Downloads.class)));
        SSocieties.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SocietiesActivity.class)));
        NNotice.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Notice.class)));
        LLMS.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),LMS.class)));
        WWebsite.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Website.class)));
        AAbout.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),About.class)));




    }
}
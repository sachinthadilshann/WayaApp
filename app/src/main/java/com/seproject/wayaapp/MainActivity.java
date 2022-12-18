package com.seproject.wayaapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.seproject.wayaapp.activities.SocietiesActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    RelativeLayout PProfile,DDownloads,NNotice,SSocieties,LLMS,AAbout,WWebsite,RResult,TTimetable;



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
        AAbout = (RelativeLayout) findViewById(R.id.about1);
        RResult= (RelativeLayout) findViewById(R.id.result);
        TTimetable= (RelativeLayout) findViewById(R.id.timetable);


        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });


        PProfile.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Profile.class)));
        DDownloads.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Downloads.class)));
        SSocieties.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SocietiesActivity.class)));
        NNotice.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Notice.class)));
        LLMS.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LMS.class)));
        WWebsite.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Website.class)));
        AAbout.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), About.class)));
        RResult.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Result.class)));
        TTimetable.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Timetable.class)));


    }
}
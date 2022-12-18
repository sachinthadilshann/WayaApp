package com.seproject.wayaapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.seproject.wayaapp.R;

public class SocietiesActivity extends AppCompatActivity {

    Button createBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_societies);

        createBtn = findViewById(R.id.create_society_btn);


        createBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),CreateSocietyActivity.class)));
    }
}
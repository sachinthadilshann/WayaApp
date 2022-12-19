package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class File_Upload extends AppCompatActivity {

    RelativeLayout level1uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        level1uploadBtn = (RelativeLayout) findViewById(R.id.Level_1_uplods);

        level1uploadBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Level1_upload.class)));
    }
}
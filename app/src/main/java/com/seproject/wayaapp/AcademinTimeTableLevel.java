package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class AcademinTimeTableLevel extends AppCompatActivity {

    RelativeLayout Academic_time_table_level_1_btn,Academic_time_table_level_2_btn,Academic_time_table_level_3_btn,Academic_time_table_level_4_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academin_time_table_level);

        Academic_time_table_level_1_btn = (RelativeLayout) findViewById(R.id.Level_1_Academic_Time_Table);
        Academic_time_table_level_2_btn = (RelativeLayout) findViewById(R.id.Level_2_Academic_Time_Table);
        Academic_time_table_level_3_btn = (RelativeLayout) findViewById(R.id.Level_3_Academic_Time_Table);
        Academic_time_table_level_4_btn = (RelativeLayout) findViewById(R.id.Level_4_Academic_Time_Table);


        Academic_time_table_level_1_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AcademicTimeTableLevel1.class)));
        Academic_time_table_level_2_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AcademicTimeTableLevel2.class)));
        Academic_time_table_level_3_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AcademicTimeTableLevel3.class)));
        Academic_time_table_level_4_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AcademicTimeTableLevel4.class)));
    }
}
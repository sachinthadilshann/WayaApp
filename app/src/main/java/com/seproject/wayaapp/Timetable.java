package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Timetable extends AppCompatActivity {

    RelativeLayout Academin_Time_Table_btn,Exam_time_table_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        Academin_Time_Table_btn = (RelativeLayout) findViewById(R.id.Academic_time_table);
        Exam_time_table_btn = (RelativeLayout) findViewById(R.id.Exam_time_Table);

        Academin_Time_Table_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),AcademinTimeTableLevel.class)));
        Exam_time_table_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Exam_TimeTable_All_Level.class)));
    }
}
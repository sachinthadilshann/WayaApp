package com.seproject.wayaapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


import mehdi.sakout.aboutpage.AboutPage;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_about);


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.fas)
                .setDescription("The Faculty of Applied Sciences of the Wayamba University of Sri Lanka was established with effect from 01st October, 1999 by the Government Notification in the Extraordinary Gazette No. 1093/8 of Tuesday, 17 August, 1999. The Faculty is located at Kuliyapitiya Premises of the University and was assigned with four Departments of Study namely, Computing & Information Systems, Electronics, Industrial Management and Mathematical Sciences.")

              //  .setCustomFont(String) // or Typeface

                .addGroup("Connect with us")
                .addEmail("sachinthadilshann@gmail.com")
                .addWebsite("https://fas.wab.ac.lk/")
                .addFacebook("Faculty-of-Applied-Sciences-Wayamba-University-Of-Sri-Lanka-319001208237089")


                .addYoutube("@user-kx7km7yd4u")
                .addPlayStore("")



                .create();
        setContentView(aboutPage);
    }
    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) final String copyrights = String.format(getString(R.string.app_name), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        ////copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
        copyRightsElement.setAutoApplyIconTint(true);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(About.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}
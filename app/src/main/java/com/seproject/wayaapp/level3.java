package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class level3 extends AppCompatActivity {

    RelativeLayout generalbtn,jointmajprbtn,specialbtn;
    DownloadManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);


        generalbtn = (RelativeLayout) findViewById(R.id.general);
        jointmajprbtn = (RelativeLayout) findViewById(R.id.jointmajor);
        specialbtn = (RelativeLayout)  findViewById(R.id.special);


        generalbtn.setOnClickListener(v -> {
            initDownload();
        });

        jointmajprbtn.setOnClickListener(v -> {
            initDownloadd();
        });

        specialbtn.setOnClickListener(v -> {
            initDownloadsp();
        });

    }


    private void initDownload() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2022/10/Application-Form-3-General.doc";
        download(getApplicationContext(), "Application-Form-3-General", ".doc", "Downloads", uri.trim());
    }

    private void initDownloadd() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2022/10/Application-Form-3-Joint-major.doc";
        download(getApplicationContext(), "Application-Form-3-General", ".doc", "Downloads", uri.trim());
    }

    private void initDownloadsp() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2022/10/Application-Form-3-Special.doc";
        download(getApplicationContext(), "Application-Form-3-General", ".doc", "Downloads", uri.trim());
    }

    private void download(Context context, String Filename, String FileExtension, String DesignationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, DesignationDirectory, Filename + FileExtension);

        assert downloadManager != null;
        downloadManager.enqueue(request);
        Snackbar snackbar = (Snackbar) Snackbar
                .make(findViewById(android.R.id.content), "Downloading...", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
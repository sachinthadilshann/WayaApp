package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class Exam_Applications extends AppCompatActivity {

    RelativeLayout properbtn,repetbtn,medicalbtn,verficationbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_applications);

        properbtn = (RelativeLayout) findViewById(R.id.Proper_Application);
        repetbtn = (RelativeLayout) findViewById(R.id.Repeat_Application);
        medicalbtn = (RelativeLayout) findViewById(R.id.medical_Application);
        verficationbtn = (RelativeLayout) findViewById(R.id.Verfication_form);

        properbtn.setOnClickListener(v -> {
            initDownloadproper();
        });

        repetbtn.setOnClickListener(v -> {
            initDownloadprepeat();
        });

        medicalbtn.setOnClickListener(v -> {
            initDownloadmedical();
        });

        verficationbtn.setOnClickListener(v -> {
            initDownloadverfication();
        });
    }

    private void initDownloadproper() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2021/01/Proper-Application.pdf";
        download(getApplicationContext(), "Proper-Application", ".pdf", "Downloads", uri.trim());
    }

    private void  initDownloadprepeat() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2021/01/Repeat-Application.pdf";
        download(getApplicationContext(), "/Repeat-Application", ".pdf", "Downloads", uri.trim());
    }

    private void initDownloadmedical() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2022/06/Medical-Application.pdf";
        download(getApplicationContext(), "Medical-Application", ".pdf", "Downloads", uri.trim());
    }

    private void initDownloadverfication() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2022/09/Verification-Form.pdf";
        download(getApplicationContext(), "Verification-Form", ".pdf", "Downloads", uri.trim());
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
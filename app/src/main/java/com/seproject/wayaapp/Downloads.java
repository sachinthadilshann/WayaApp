package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Downloads extends AppCompatActivity {

    RelativeLayout ll1,ll2,ll3,ll4,Repeat_btn,Exam_application_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        ll1= (RelativeLayout) findViewById(R.id.lev1);
        ll2= (RelativeLayout) findViewById(R.id.lev2);
        ll3= (RelativeLayout) findViewById(R.id.lev3);
        ll4= (RelativeLayout) findViewById(R.id.lev4);
        Repeat_btn = (RelativeLayout)  findViewById(R.id.repeat);
        Exam_application_btn = (RelativeLayout) findViewById(R.id.Exam);



        ll1.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),level1.class)));
        ll3.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),level3.class)));
        ll2.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),level2_Downloads.class)));
        ll4.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),level4_Downloads.class)));
        Exam_application_btn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Exam_Applications.class)));

        Repeat_btn.setOnClickListener(v -> {
            initDownloadd();
        });

    }

    private void initDownloadd() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2021/02/Registration-Renew-Applications.pdf";
        download(getApplicationContext(), "Registration-Renew-Applications", ".pdf", "Downloads", uri.trim());
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
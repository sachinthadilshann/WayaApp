package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class Prospectus extends AppCompatActivity {

    RelativeLayout p19_20,p18_19,p17_18,p16_17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospectus);

        p19_20 = (RelativeLayout) findViewById(R.id.p2019_2020);
        p18_19 = (RelativeLayout) findViewById(R.id.p2018_2019);
        p17_18 = (RelativeLayout) findViewById(R.id.p2017_2018);
        p16_17 = (RelativeLayout) findViewById(R.id.p2016_2017);

        p19_20.setOnClickListener(v -> {
            initDownloadp2019_2020();
        });

        p18_19.setOnClickListener(v -> {
            initDownloadpp2018_2019();
        });

        p17_18.setOnClickListener(v -> {
            initDownloadpp2017_2018();
        });

        p16_17.setOnClickListener(v -> {
            initDownloadpp2016_2017();
        });
    }



    private void  initDownloadp2019_2020() {
        String uri = "https://fas.wyb.ac.lk/wp-content/uploads/2022/11/Prospectus-2019-2020-with-front-back-Cover.pdf";
        download(getApplicationContext(), "Prospectus-2019-2020-with-front-back-Cover", ".pdf", "Downloads", uri.trim());
    }

    private void   initDownloadpp2018_2019() {
        String uri = "https://fas.wyb.ac.lk/download/1562/";
        download(getApplicationContext(), "Prospectus-2018-2019", ".pdf", "Downloads", uri.trim());
    }

    private void initDownloadpp2017_2018() {
        String uri = "https://fas.wyb.ac.lk/download/1563/";
        download(getApplicationContext(), "Prospectus-2017-2018", ".pdf", "Downloads", uri.trim());
    }

    private void  initDownloadpp2016_2017() {
        String uri = "https://fas.wyb.ac.lk/download/1559/";
        download(getApplicationContext(), "Prospectus-2016-2017", ".pdf", "Downloads", uri.trim());
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
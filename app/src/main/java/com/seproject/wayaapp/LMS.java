package com.seproject.wayaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class LMS extends AppCompatActivity {

    WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lms);

        webView = findViewById(R.id.lmsk);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://lmsk.wyb.ac.lk/login/index.php");

     /*   class WebAppInterface {
            Context mContext;


            WebAppInterface(Context c) {
                mContext = c;
            }


            @JavascriptInterface
            public void showToast(String toast) {
                Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
            }
        }


*/



    }
}
package com.example.nabux.projectgogo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class report extends AppCompatActivity {
    WebView webreport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        webreport= (WebView) findViewById(R.id.webreport);
        webreport.loadUrl("http://45.55.213.89/nabu_connect/score.html?score=87");
        webreport.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webreport.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}

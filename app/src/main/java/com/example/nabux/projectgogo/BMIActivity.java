package com.example.nabux.projectgogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {
    WebView webbmi;
    TextView tvtime,tvhigh,tvavg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        webbmi= (WebView) findViewById(R.id.webbmi);
        tvtime= (TextView) findViewById(R.id.tvtime);
        tvhigh= (TextView) findViewById(R.id.tvhigh);
        tvavg= (TextView) findViewById(R.id.tvavg);
        webbmi.loadUrl("http://45.55.213.89/nabu_connect/BMIActivity.html?jan=22&feb=23&mar=24&apr=21&may=25&jun=20&jul=23&aug=21&sep=22&oct=24&nov=23&dec=25");
        webbmi.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webbmi.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}

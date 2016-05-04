package com.example.nabux.projectgogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class hg extends AppCompatActivity {
    WebView webhg;
    TextView tvtime,tvhigh,tvavg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hg);
        webhg= (WebView) findViewById(R.id.webhg);
        tvtime= (TextView) findViewById(R.id.tvtime);
        tvhigh= (TextView) findViewById(R.id.tvhigh);
        tvavg= (TextView) findViewById(R.id.tvavg);
        webhg.loadUrl("http://45.55.213.89/nabu_connect/mmhg.html?hmon=200&htue=225&hwed=240&hthr=260&hfri=215&hsat=270&hsun=234&lmon=100&ltue=125&lwed=140&lthr=160&lfri=115&lsat=170&lsun=134");
        webhg.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webhg.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}

package com.example.nabux.projectgogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class BPActivity extends AppCompatActivity {
    WebView webhg;
    TextView tvtime,tvhigh,tvavg;

    double[] bp_sys_buffer;
    double[] bp_dia_buffer;
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/mmhg.html";
    String para = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hg);
        webhg= (WebView) findViewById(R.id.webhg);
        tvtime= (TextView) findViewById(R.id.tvtime);
        tvhigh= (TextView) findViewById(R.id.tvhigh);
        tvavg= (TextView) findViewById(R.id.tvavg);

        Intent in = getIntent();
        bp_sys_buffer = in.getDoubleArrayExtra("bp_sys_buffer");
        bp_dia_buffer = in.getDoubleArrayExtra("bp_dia_buffer");

        para = "hmon=" + bp_sys_buffer[0] + "&&" +
                "htue=" + bp_sys_buffer[1] + "&&" +
                "hwed=" + bp_sys_buffer[2] + "&&";

        para += "lmon=" + bp_dia_buffer[0] + "&&" +
                "ltue=" + bp_dia_buffer[1] + "&&" +
                "lwed=" + bp_dia_buffer[2];
        String url_ref = htmlurl + "?" + para;
        Log.d("STR URL REF", url_ref);

        //webhg.loadUrl("http://45.55.213.89/nabu_connect/mmhg.html?hmon=200&htue=225&hwed=240&hthr=260&hfri=215&hsat=270&hsun=234&lmon=100&ltue=125&lwed=140&lthr=160&lfri=115&lsat=170&lsun=134");
        webhg.loadUrl(url_ref);
        webhg.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webhg.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}

package com.example.nabux.projectgogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class step extends AppCompatActivity {
    WebView webstep;
    TextView tvtime,tvhigh,tvavg;
    int[] step_buffer;
    String htmlurl="http://home1.usc.edu.tw/a0261038/steps.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        webstep= (WebView) findViewById(R.id.webstep);
        tvtime= (TextView) findViewById(R.id.tvtime);
        tvhigh= (TextView) findViewById(R.id.tvhigh);
        tvavg= (TextView) findViewById(R.id.tvavg);

        Intent in = getIntent();
        step_buffer = in.getIntArrayExtra("step_buffer");
        Log.d("lalala", step_buffer[0]+"");
        String para = "aa="+step_buffer[0]+"&&"+
                        "bb="+step_buffer[1]+"&&"+
                        "cc="+step_buffer[2]+"&&"+
                        "dd="+step_buffer[3]+"&&"+
                        "ee="+step_buffer[4]+"&&"+
                        "ff="+step_buffer[5]+"&&"+
                        "gg="+step_buffer[6];


        htmlurl = htmlurl + "?" + para;
        webstep.loadUrl(htmlurl);
        webstep.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webstep.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}

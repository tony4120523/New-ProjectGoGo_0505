package com.example.nabux.projectgogo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class pulseActivity extends AppCompatActivity {
    WebView webpulse;
    TextView tvtime,tvavgpulse;
    int pulse_buffer[];
    int sumpulse,avgpulse;
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/pulse.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulse);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("脈搏變化圖");
        webpulse= (WebView) findViewById(R.id.webpulse);
        tvavgpulse=(TextView) findViewById(R.id.tvavgpulse);
        tvtime= (TextView) findViewById(R.id.tvtime);
        Intent in = getIntent();
        pulse_buffer = in.getIntArrayExtra("pulse_buffer");
        String para = "mon="+pulse_buffer[0]+"&&"+
                "tue="+pulse_buffer[1]+"&&"+
                "wed="+pulse_buffer[2]+"&&"+
                "thr="+pulse_buffer[3]+"&&"+
                "fri="+pulse_buffer[4]+"&&"+
                "sat="+pulse_buffer[5]+"&&"+
                "sun="+pulse_buffer[6];

        String url_ref = htmlurl + "?" + para;
        for(int i=0;i<7;i++){
            sumpulse+=pulse_buffer[i];
        }
        avgpulse=sumpulse/7;
        tvavgpulse.setText("本周脈搏平均值 為："+avgpulse+"  下/每分");
        webpulse.loadUrl(url_ref);
        webpulse.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webpulse.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);

    }
}

package com.example.nabux.projectgogo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class BSActivity extends AppCompatActivity {
    WebView webbs;
    TextView tvtime,tvavgbs;
    int[] bs_buffer;
    int sumbs,avgbs;
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/bs.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bs);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("血糖變化圖");
        webbs= (WebView) findViewById(R.id.webbs);
        tvavgbs= (TextView) findViewById(R.id.tvavgsys);
        tvtime= (TextView) findViewById(R.id.tvtime);
        Intent in = getIntent();
        bs_buffer = in.getIntArrayExtra("bs_buffer");
        String para = "mon="+bs_buffer[0]+"&&"+
                "tue="+bs_buffer[1]+"&&"+
                "wed="+bs_buffer[2]+"&&"+
                "thr="+bs_buffer[3]+"&&"+
                "fri="+bs_buffer[4]+"&&"+
                "sat="+bs_buffer[5]+"&&"+
                "sun="+bs_buffer[6];

        String url_ref = htmlurl + "?" + para;

        for(int i=0;i<7;i++){
            sumbs+=bs_buffer[i];
        }
        avgbs=sumbs/7;
        tvavgbs.setText("本周血糖(飯後兩小時)平均值 為："+avgbs+"  mg/dl");
        webbs.loadUrl(url_ref);
        webbs.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webbs.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
    }
}

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
    TextView tvtime,tvavgsys,tvavgdia;
    double avgsys,avgdia,sumsys,sumdia;
    double[] bp_sys_buffer;
    double[] bp_dia_buffer;

    private static final String htmlurl = "http://www.hth96.me/nabu_connect/mmhg.html";
    private static final String TAG = BPActivity.class.getSimpleName();
    String para = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hg);
        webhg= (WebView) findViewById(R.id.webhg);
        tvtime= (TextView) findViewById(R.id.tvtime);
        tvavgsys= (TextView) findViewById(R.id.tvavgbs);
        tvavgdia= (TextView) findViewById(R.id.tvavgdia);

        Intent in = getIntent();
        bp_sys_buffer = in.getDoubleArrayExtra("bp_sys_buffer");
        bp_dia_buffer = in.getDoubleArrayExtra("bp_dia_buffer");

        para = "hmon=" + bp_sys_buffer[0] + "&&" +
                "htue=" + bp_sys_buffer[1] + "&&" +
                "hwed=" + bp_sys_buffer[2] + "&&" +
                "hthr=" + bp_sys_buffer[3] + "&&" +
                "hfri=" + bp_sys_buffer[4] + "&&" +
                "hsat=" + bp_sys_buffer[5] + "&&" +
                "hsun=" + bp_sys_buffer[6] + "&&";

        para += "lmon=" + bp_dia_buffer[0] + "&&" +
                "ltue=" + bp_dia_buffer[1] + "&&" +
                "lwed=" + bp_dia_buffer[2] + "&&" +
                "lthr=" + bp_dia_buffer[3] + "&&" +
                "lfri=" + bp_dia_buffer[4] + "&&" +
                "lsat=" + bp_dia_buffer[5] + "&&" +
                "lsun=" + bp_dia_buffer[6] ;


        String url_ref = htmlurl + "?" + para;
        Log.d(TAG, "URL REF : " + url_ref);

        for(int i=0;i<7;i++){
            sumdia+=bp_dia_buffer[i];
            sumsys+=bp_sys_buffer[i];
        }
        avgdia=sumdia/7;
        avgsys=sumsys/7;
        avgdia=((Math.round(avgdia*100.0))/100.0);
        avgsys=((Math.round(avgsys*100.0))/100.0);
        tvavgsys.setText("本週平均心肌收縮壓為： "+avgsys);
        tvavgdia.setText("本週平均心肌舒張壓為： "+avgdia);
        //webhg.loadUrl("http://45.55.213.89/nabu_connect/mmhg.html?hmon=200&htue=225&hwed=240&hthr=260&hfri=215&hsat=270&hsun=234&lmon=100&ltue=125&lwed=140&lthr=160&lfri=115&lsat=170&lsun=134");
        webhg.loadUrl(url_ref);
        webhg.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webhg.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
    }
}

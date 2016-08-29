package com.example.nabux.projectgogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {
    WebView webbmi;
    TextView tvtime,tvhigh,tvavg;

    double[] bmi_buffer;
    double max_month_bmi=0.0,avgbmi=0.0,sumbmi=0.0;
    String max_month;
    String[] month=new String[]{"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/BMIActivity.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        webbmi= (WebView) findViewById(R.id.webbmi);
        tvtime= (TextView) findViewById(R.id.tvtime);
        tvhigh= (TextView) findViewById(R.id.tvavgsys);
        tvavg= (TextView) findViewById(R.id.tvavgdia);

        Intent in = getIntent();
        bmi_buffer = in.getDoubleArrayExtra("bmi_buffer");
        String para = "jan=" + bmi_buffer[0] + "&&" +
                      "feb=" + bmi_buffer[1] + "&&" +
                      "mar=" + bmi_buffer[2] + "&&" +
                      "apr=" + bmi_buffer[3] + "&&" +
                      "may=" + bmi_buffer[4] + "&&" +
                      "jun=" + bmi_buffer[5] + "&&" +
                      "jul=" + bmi_buffer[6] + "&&" +
                      "aug=" + bmi_buffer[7] + "&&" +
                      "sep=" + bmi_buffer[8] + "&&" +
                      "oct=" + bmi_buffer[9] + "&&" +
                      "nov=" + bmi_buffer[10] + "&&" +
                      "dec=" + bmi_buffer[11] ;

        String url_ref = htmlurl + "?" + para;

        for(int i=0;i<12;i++){
        if(bmi_buffer[i]>max_month_bmi){
            max_month_bmi=bmi_buffer[i];
            max_month=month[i];
        }

        }

        for(int i=0;i<12;i++){
            sumbmi+=bmi_buffer[i];
        }
        avgbmi=sumbmi/12.0;
        avgbmi=((Math.round(avgbmi*100.0))/100.0);
        tvhigh.setText("最高BMI出現在 "+max_month+" !!");
        tvavg.setText("2016年 1到12月 BMI平均值 為："+avgbmi);
        //webbmi.loadUrl("http://45.55.213.89/nabu_connect/BMIActivity.html?jan=22&feb=23&mar=24&apr=21&may=25&jun=20&jul=23&aug=21&sep=22&oct=24&nov=23&dec=25");
        webbmi.loadUrl(url_ref);
        webbmi.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webbmi.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}

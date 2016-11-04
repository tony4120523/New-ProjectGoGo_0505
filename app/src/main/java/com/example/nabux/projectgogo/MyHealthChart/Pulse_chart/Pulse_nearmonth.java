package com.example.nabux.projectgogo.MyHealthChart.Pulse_chart;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.nabux.projectgogo.R;

public class Pulse_nearmonth extends Fragment {
    WebView webpulse;
    TextView tvtime,tvavgpulse;
    int pulse_buffer[];

    private static final String htmlurl = "http://www.hth96.me/nabu_connect/pulse/pulse_new.html";
    public static com.example.nabux.projectgogo.MyHealthChart.Pulse_chart.Pulse_nearmonth newInstance() {

        com.example.nabux.projectgogo.MyHealthChart.Pulse_chart.Pulse_nearmonth fragment = new com.example.nabux.projectgogo.MyHealthChart.Pulse_chart.Pulse_nearmonth();


        return fragment;
    }
    public Pulse_nearmonth() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_pulse_nearmonth, container, false);
        webpulse= (WebView) rootView.findViewById(R.id.webpulse);
        tvavgpulse=(TextView) rootView.findViewById(R.id.tvavgpulse);
        tvtime= (TextView) rootView.findViewById(R.id.tvtime);
        Intent in = getActivity().getIntent();
        pulse_buffer = in.getIntArrayExtra("month_pulse_buffer");
        /*String para = "mon="+pulse_buffer[0]+"&&"+
                "tue="+pulse_buffer[1]+"&&"+
                "wed="+pulse_buffer[2]+"&&"+
                "thr="+pulse_buffer[3]+"&&"+
                "fri="+pulse_buffer[4]+"&&"+
                "sat="+pulse_buffer[5]+"&&"+
                "sun="+pulse_buffer[6];*/

        String para="";

        para += pulse_buffer[0];
        for(int i=1;i<pulse_buffer.length;i++){
            if(pulse_buffer[i]!=0) {
                para += "+"+pulse_buffer[i];
            }
        }
        String url_ref = htmlurl + "?something=" + para;
        int avgpulse,sumpulse=0,avgby=0;
        for(int i=0;i<pulse_buffer.length;i++){
            if(pulse_buffer[i]>0) {
                sumpulse += pulse_buffer[i];
                avgby++;
            }
        }
        avgpulse=sumpulse/avgby;
        tvavgpulse.setText("最近三個月脈搏平均值 為："+avgpulse+"  下/每分");
        webpulse.loadUrl(url_ref);
        webpulse.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webpulse.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);


        return rootView;
    }
}
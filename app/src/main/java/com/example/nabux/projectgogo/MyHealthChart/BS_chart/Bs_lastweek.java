package com.example.nabux.projectgogo.MyHealthChart.BS_chart;

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

public class Bs_lastweek extends Fragment {
    WebView webbs;
    TextView tvtime,tvavgbs;
    int[] bs_buffer;
    int sumbs,avgbs;
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/bs.html";
    public static com.example.nabux.projectgogo.MyHealthChart.BS_chart.Bs_lastweek newInstance() {

        com.example.nabux.projectgogo.MyHealthChart.BS_chart.Bs_lastweek fragment = new com.example.nabux.projectgogo.MyHealthChart.BS_chart.Bs_lastweek();


        return fragment;
    }
    public Bs_lastweek() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_bs_lastweek, container, false);
        webbs= (WebView) rootView.findViewById(R.id.webbs);
        tvavgbs= (TextView) rootView.findViewById(R.id.tvavgsys);
        tvtime= (TextView) rootView.findViewById(R.id.tvtime);
        Intent in = getActivity().getIntent();
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
        tvavgbs.setText("上周血糖(飯後兩小時)平均值 為："+avgbs+"  mg/dl");
        webbs.loadUrl(url_ref);
        webbs.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webbs.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);

        return rootView;
    }
}
package com.example.nabux.projectgogo.MyHealthChart.BP_chart;

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

public class BP_nearmonth extends Fragment {
    WebView webhg;
    TextView tvtime,tvavgsys,tvavgdia;
    double avgsys,avgdia,sumsys,sumdia;
    double[] bp_sys_buffer;
    double[] bp_dia_buffer;
    String para = "";
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/mmhg/mmhg_new.html";
    public static BP_nearmonth newInstance() {

        BP_nearmonth fragment = new BP_nearmonth();


        return fragment;
    }
    public BP_nearmonth() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_bp_nearmonth, container, false);
        webhg= (WebView) rootView.findViewById(R.id.webhg);
        tvtime= (TextView) rootView.findViewById(R.id.tvtime);
        tvavgsys= (TextView) rootView.findViewById(R.id.tvavgsys);
        tvavgdia= (TextView) rootView.findViewById(R.id.tvavgdia);
        Intent in = getActivity().getIntent();
        bp_sys_buffer = in.getDoubleArrayExtra("month_bp_sys_buffer");
        bp_dia_buffer = in.getDoubleArrayExtra("month_bp_dia_buffer");
        /*para = "hmon=" + bp_sys_buffer[0] + "&&" +
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
*/

        String para="";
        for(int i=0;i<bp_sys_buffer.length;i++){
            para+=bp_sys_buffer[i]+"+"+bp_dia_buffer[i]+"+";
        }
        String url_ref = htmlurl + "?something=" + para;
        double avgsys,avgdia,sumsys =0.0;
        double sumdia=0.0;
        int avgby1=0,avgby2=0;
        for(int i=0;i<bp_sys_buffer.length;i++){
            if(bp_dia_buffer[i]>0) {
                sumdia += bp_dia_buffer[i];
                avgby1++;
            }
            if(bp_sys_buffer[i]>0) {
                sumsys += bp_sys_buffer[i];
                avgby2++;
            }
        }
        avgdia=sumdia/avgby1;
        avgsys=sumsys/avgby2;
        avgdia=((Math.round(avgdia*100.0))/100.0);
        avgsys=((Math.round(avgsys*100.0))/100.0);
        tvavgsys.setText("最近三個月平均心肌收縮壓為： "+avgsys);
        tvavgdia.setText("最近三個月平均心肌舒張壓為： "+avgdia);
        webhg.loadUrl(url_ref);
        webhg.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webhg.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);


        return rootView;
    }
}
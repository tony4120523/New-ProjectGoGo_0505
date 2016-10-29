package com.example.nabux.projectgogo.MyHealthChart.Step_chart;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.nabux.projectgogo.R;

public class Step_nearmonth extends Fragment {
    WebView webstep;
    TextView tvtime,tvhigh,tvavg;
    private static final String TAG = Step_nearmonth.class.getSimpleName();
    int[] step_buffer;
    int max_week_step;
    String max_day;
    String[] weekday=new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/step/step_new.html";
    public static com.example.nabux.projectgogo.MyHealthChart.Step_chart.Step_nearmonth newInstance() {

        com.example.nabux.projectgogo.MyHealthChart.Step_chart.Step_nearmonth fragment = new com.example.nabux.projectgogo.MyHealthChart.Step_chart.Step_nearmonth();


        return fragment;
    }
    public Step_nearmonth() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_step_nearmonth, container, false);
        webstep= (WebView) rootView.findViewById(R.id.webstep);
        tvtime= (TextView) rootView.findViewById(R.id.tvtime);
        tvhigh= (TextView) rootView.findViewById(R.id.tvavgsys);
        tvavg= (TextView) rootView.findViewById(R.id.tvavgdia);
        Intent in = getActivity().getIntent();
        step_buffer = in.getIntArrayExtra("month_step_buffer");
        /*String para = "aa="+step_buffer[0]+"&&"+
                "bb="+step_buffer[1]+"&&"+
                "cc="+step_buffer[2]+"&&"+
                "dd="+step_buffer[3]+"&&"+
                "ee="+step_buffer[4]+"&&"+
                "ff="+step_buffer[5]+"&&"+
                "gg="+step_buffer[6];
*/
        String para="";
        for(int i=0;i<step_buffer.length;i++){
            para+=step_buffer[i]+"+";
        }
        String url_ref = htmlurl + "?something=" + para;
        for(int i=0;i<step_buffer.length;i++){
            if(step_buffer[i]>max_week_step){
                max_week_step=step_buffer[i];
                max_day="DAY"+(i+1);
            }

        }
        int avgstep,sumstep=0,avgby=0;
        for(int i=0;i<step_buffer.length;i++){
            if(step_buffer[i]>0) {
                sumstep += step_buffer[i];
                avgby++;
            }
        }
        Log.d(TAG, "★★★"+avgby+"★★★");
        avgstep=sumstep/avgby;

        tvhigh.setText("最近三個月最高步數出現在 "+max_day+" !!");
        //tvavg.setText("本周步數平均值 為："+avgstep+" 步");
        tvavg.setText("步數平均值 為："+avgstep+" 步");
        webstep.loadUrl(url_ref);
        webstep.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webstep.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);


        return rootView;
    }
}
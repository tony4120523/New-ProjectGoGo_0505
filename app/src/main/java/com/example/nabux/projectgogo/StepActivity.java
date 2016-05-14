package com.example.nabux.projectgogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class StepActivity extends AppCompatActivity {
    WebView webstep;
    TextView tvtime,tvhigh,tvavg;

    int[] step_buffer;
    private static final String htmlurl = "http://www.hth96.me/nabu_connect/steps.html";

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
        String para = "aa="+step_buffer[0]+"&&"+
                "bb="+step_buffer[1]+"&&"+
                "cc="+step_buffer[2]+"&&"+
                "dd="+step_buffer[3]+"&&"+
                "ee="+step_buffer[4]+"&&"+
                "ff="+step_buffer[5]+"&&"+
                "gg="+step_buffer[6];

        String url_ref = htmlurl + "?" + para;
        webstep.loadUrl(url_ref);
        webstep.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webstep.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
/*
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   //確定按下退出鍵

            Intent in = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(in);
            finish();

            return true;

        }

        return super.onKeyDown(keyCode, event);

    }*/
}

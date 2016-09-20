package com.example.nabux.projectgogo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class HBActivity extends AppCompatActivity {
    WebView webhb;
    TextView tvtime,tvavghb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hb);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("脈搏變化圖");
        webhb= (WebView) findViewById(R.id.webbs);
        tvavghb=(TextView) findViewById(R.id.tvavghb);
        tvtime= (TextView) findViewById(R.id.tvtime);
    }
}

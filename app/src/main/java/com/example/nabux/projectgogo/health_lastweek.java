package com.example.nabux.projectgogo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class health_lastweek extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_lastweek);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("上週健康報告 9/11 - 9/17");
    }
}

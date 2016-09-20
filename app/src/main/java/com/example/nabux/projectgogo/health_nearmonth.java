package com.example.nabux.projectgogo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class health_nearmonth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_nearmonth);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("最近三個月(90天)健康報告 6/19 - 9/19");
    }
}

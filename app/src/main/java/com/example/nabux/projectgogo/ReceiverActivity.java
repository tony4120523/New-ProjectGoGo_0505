package com.example.nabux.projectgogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        NetworkChangeReceiver networkchangereceiver = new NetworkChangeReceiver();        
    }
}

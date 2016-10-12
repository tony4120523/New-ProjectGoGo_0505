package com.example.nabux.projectgogo;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class know_pulse extends Fragment {

    public static know_pulse newInstance() {

        know_pulse fragment = new know_pulse();

        return fragment;
    }
    public know_pulse() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_know_pulse, container, false);



        return rootView;
    }
}

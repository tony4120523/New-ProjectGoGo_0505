package com.example.nabux.projectgogo;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class know_step extends Fragment {

    public static know_step newInstance() {

        know_step fragment = new know_step();

        return fragment;
    }
    public know_step() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_know_step, container, false);



        return rootView;
    }
}

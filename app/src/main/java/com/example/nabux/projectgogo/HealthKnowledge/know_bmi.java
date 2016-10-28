package com.example.nabux.projectgogo.HealthKnowledge;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nabux.projectgogo.R;

public class know_bmi extends Fragment {

    public static know_bmi newInstance() {

        know_bmi fragment = new know_bmi();

        return fragment;
    }
    public know_bmi() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_know_bmi, container, false);



        return rootView;
    }
}

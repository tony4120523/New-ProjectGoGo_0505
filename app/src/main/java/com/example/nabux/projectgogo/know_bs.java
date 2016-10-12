package com.example.nabux.projectgogo;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class know_bs extends Fragment {

    public static know_bs newInstance() {

        know_bs fragment = new know_bs();

        return fragment;
    }
    public know_bs() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_know_bs, container, false);



        return rootView;
    }
}

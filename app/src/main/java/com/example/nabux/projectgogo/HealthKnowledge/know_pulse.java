package com.example.nabux.projectgogo.HealthKnowledge;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nabux.projectgogo.R;

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
        ImageView img_pulse= (ImageView) rootView.findViewById(R.id.img_pulse);
        TextView tv_pulse= (TextView) rootView.findViewById(R.id.tv_pulse);
        tv_pulse.setText("銀髮族脈搏正常值為50-70下/分\n出現異常就有可能是\n▲中風\n▲心臟病\n▲高血壓\n▲其他心血管疾病\n必須特別注意！！");


        return rootView;
    }
}

package com.example.nabux.projectgogo.HealthKnowledge;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nabux.projectgogo.R;

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
        ImageView img_step= (ImageView) rootView.findViewById(R.id.img_step);
        TextView tv_step= (TextView) rootView.findViewById(R.id.tv_step);
        tv_step.setText("每日建議步數為6000步\n健走的好處：\n●加速代謝脂肪\n●強化肌肉組織及功能\n●維持健康體重\n●增加心肺功能\n●降低情緒壓力");


        return rootView;
    }
}

package com.example.nabux.projectgogo.HealthKnowledge;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nabux.projectgogo.R;

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
        ImageView img_step= (ImageView) rootView.findViewById(R.id.img_bs);
        TextView tv_bs= (TextView) rootView.findViewById(R.id.tv_bs);
        tv_bs.setText("正常血糖值(空腹)為:70-99mg/dl\n如何控制血糖：\n●保持適當體重\n●改吃複合式澱粉\n●水果、糖份要限量\n●適當補充礦物質\n●運動");


        return rootView;
    }
}

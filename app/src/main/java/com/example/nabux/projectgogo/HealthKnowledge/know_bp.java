package com.example.nabux.projectgogo.HealthKnowledge;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nabux.projectgogo.R;

public class know_bp extends Fragment {

    public static know_bp newInstance() {

        know_bp fragment = new know_bp();

        return fragment;
    }
    public know_bp() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_know_bp, container, false);
        ImageView img_bp= (ImageView) rootView.findViewById(R.id.img_bp);
        TextView tv_bp= (TextView) rootView.findViewById(R.id.tv_bp);
        tv_bp.setText("正常血壓範圍為\n收縮壓100–140mmHg(高壓)\n舒張壓   60–  90mmHg(低壓)\n血壓持續等於或高於140/90mmHg時則為高血壓。");


        return rootView;
    }
}

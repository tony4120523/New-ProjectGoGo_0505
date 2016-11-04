package com.example.nabux.projectgogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MemberActivity extends AppCompatActivity {

    private static final String TAG = MemberActivity.class.getSimpleName();
    ListView list_info;
    private SimpleAdapter adapter;
    ArrayList<HashMap<String,String>> arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        Session session = new Session(getApplicationContext());
        Log.d(TAG, session.getNickName());
        Log.d(TAG, session.getUserCPemail());
        Log.d(TAG, "H" + session.getHypertension());
        Log.d(TAG, "D1" + session.getDiabetes());
        Log.d(TAG, "D2" + session.getDisease());

        String tmp_name, tmp_email, tmp_hy, tmp_diabetes, tmp_disease;
        if(session.getNickName() != null && !session.getNickName().isEmpty()){
            tmp_name = session.getNickName();
        } else {
            tmp_name = "無";
        }
        if(session.getUserCPemail() != null && !session.getUserCPemail().isEmpty()){
            tmp_email = session.getUserCPemail();
        } else {
            tmp_email = "無";
        }
        if(session.getHypertension() != null && !session.getHypertension().isEmpty()){
            tmp_hy = "有";
        } else {
            tmp_hy = "無";
        }
        if(session.getDiabetes() != null && !session.getDiabetes().isEmpty()){
            tmp_diabetes = "有";
        } else {
            tmp_diabetes = "無";
        }
        if(session.getDisease() != null && !session.getDisease().isEmpty()){
            tmp_disease = "有";
        } else {
            tmp_disease = "無";
        }

        list_info = (ListView) findViewById(R.id.list_member_info);
        arraylist = new ArrayList<HashMap<String,String>>();

        String[] info_title = new String[]{"名字", "聯絡人的電子信箱", "高血壓", "糖尿病", "心臟疾病"};
        String[] info = new String[]{tmp_name, tmp_email, tmp_hy, tmp_diabetes, tmp_disease};

        for(int i=0; i<info_title.length; i++){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put( "info_title", info_title[i]);
            item.put( "info",info[i]);
            arraylist.add(item);
        }

        adapter = new SimpleAdapter(
                this,
                arraylist,
                android.R.layout.simple_list_item_2,
                new String[] { "info_title","info" },
                new int[] { android.R.id.text1, android.R.id.text2 }
        );

        list_info.setAdapter(adapter);


    }
}

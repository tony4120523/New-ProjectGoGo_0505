package com.example.nabux.projectgogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowHospitalActivity extends AppCompatActivity {

    String[]name;
    String[]address;
    String[]phone;
    ListAdapter adapter;
    /*private static final String name_qq = "ver";
    private static final String address_qq = "name";
    private static final String phone_qq = "api";*/
    //private List<HashMap<String, String>> hospitalMapList = new ArrayList<>();
    //List<Map<String, Object>> hospitalMapList = new ArrayList<Map<String,Object>>();
    private ListView list1;
    private static final String TAG = ShowHospitalActivity.class.getSimpleName();
    // List<Map<String, Object>> hospitalMapList = new ArrayList<Map<String,Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hospital);
        list1= (ListView) findViewById(R.id.list1);
        Intent in = getIntent();
        name = in.getStringArrayExtra("name");

        address = in.getStringArrayExtra("address");
        phone = in.getStringArrayExtra("phone");
        List<Map<String, Object>> hospitalMapList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<name.length;i++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("name_qq", name[i]);
            map.put("address_qq", address[i]);
            map.put("phone_qq", phone[i]);
            hospitalMapList.add(map);
            Log.d(TAG, "★★★"+map+"★★★");
        }
        adapter = new SimpleAdapter(this, hospitalMapList, R.layout.list_item,
                new String[] { "name_qq", "address_qq", "phone_qq" },
                new int[] { R.id.name,R.id.address, R.id.phone });

        list1.setAdapter(adapter);
        //loadListView();
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent aa=new Intent(getApplicationContext(), MapsActivity.class);
                aa.putExtra("name", name[position]);
                aa.putExtra("address", address[position]);
                startActivity(aa);

            }
        });
    }
}

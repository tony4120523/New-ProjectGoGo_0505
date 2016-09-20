package com.example.nabux.projectgogo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class health extends AppCompatActivity {
    ListView listrange;
    private SimpleAdapter adapter;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    String[]range=new String[]{"本週","上週","最近三個月(90天)"};
    String[]date=new String[]{"2016/9/18 - 2016/9/24","2016/9/11 - 2016/9/17","2016/6/19 - 2016/9/19"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("我的健康報告");
        listrange= (ListView) findViewById(R.id.listrange);
        for(int i=0; i<range.length; i++){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put( "ranged", range[i]);
            item.put( "dated",date[i] );
            list.add( item );
        }

        adapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2,
                new String[] { "ranged","dated" },
                new int[] { android.R.id.text1, android.R.id.text2 }
        );

        listrange.setAdapter(adapter);
    }
}

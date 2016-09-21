package com.example.nabux.projectgogo;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class health extends AppCompatActivity {
    ListView listrange;

    private SimpleAdapter adapter;

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    String[]range=new String[]{"上週","本週(建置中)","最近三個月(90天)(建置中)"};
    String[]date=new String[]{"2016/9/11 - 2016/9/17","2016/9/18 - 2016/9/24","2016/6/19 - 2016/9/19"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("我的健康報告");
        Intent in = getIntent();

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

        listrange.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                                    Intent in=new Intent(getApplicationContext(),health_lastweek.class);

                                        startActivity(in);
                                        break;
                                    default:
                                        break;

                    case 1:
                        Intent it=new Intent(getApplicationContext(),health_thisweek.class);
                        startActivity(it);
                        break;
                    case 2:
                        Intent ii=new Intent(getApplicationContext(),health_nearmonth.class);
                        startActivity(ii);
                        break;
                }
            }
        });
    }

}

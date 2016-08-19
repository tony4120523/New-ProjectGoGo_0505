package com.example.nabux.projectgogo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ITM Admin on 2016/7/28.
 */
public class lastweek_fragment extends Fragment{
    private SimpleAdapter adapter;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    double[] thisweek_ans = new double[]{26.17,12796.0,1559.71,1537.86};
    String[] thisweek_item=new String[]{"本週平均bmi為："+thisweek_ans[0],"本週平均步數為："+thisweek_ans[1],"本週平均心肌收縮壓為："+thisweek_ans[2],"本週平均平均心肌舒張為："+thisweek_ans[3],};

    double[] lastweek_ans = new double[]{28.17,22796.0,1759.71,1737.86};
    String[] compare=new String[]{"比上週平均bmi少了："+(lastweek_ans[0]-thisweek_ans[0]),"比上週平均步數少了："+(lastweek_ans[1]-thisweek_ans[1]),"比上週平均心肌收縮壓少了："+(lastweek_ans[2]-thisweek_ans[2]),"比上週平均平均心肌舒張少了："+(lastweek_ans[3]-thisweek_ans[3]),};
    ListView list1;

    public static lastweek_fragment newInstance() {

        lastweek_fragment fragment = new lastweek_fragment();

        return fragment;
    }
    public lastweek_fragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lastweek_show, container, false);
        list1= (ListView) rootView.findViewById(R.id.list1);

        for(int i=0; i<thisweek_item.length; i++){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put( "thisweek", thisweek_item[i]);
            item.put( "compared",compare[i] );
            list.add( item );
        }
        adapter = new SimpleAdapter(
                getActivity(),
                list,
                android.R.layout.simple_list_item_2,
                new String[] { "thisweek","compared" },
                new int[] { android.R.id.text1, android.R.id.text2 } );

                list1.setAdapter(adapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "顯示"+thisweek_item[position], Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }






}

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
    double[] lastweek_ans = new double[]{36.0,12796.0,143.0,88.0};
    String[] lastweek_item=new String[]{"本週平均bmi為："+lastweek_ans[0],"本週平均步數為："+lastweek_ans[1],"本週平均心肌收縮壓為："+lastweek_ans[2],"本週平均平均心肌舒張壓為："+lastweek_ans[3],};
    //double[] lastweek_ans = new double[]{28.17,22796.0,144.0,77.0};
    //String[] compare=new String[]{"比上週平均bmi少了："+(lastweek_ans[0]-thisweek_ans[0]),"比上週平均步數少了："+(lastweek_ans[1]-thisweek_ans[1]),"比上週平均心肌收縮壓少了："+(lastweek_ans[2]-thisweek_ans[2]),"比上週平均平均心肌舒張少了："+(lastweek_ans[3]-thisweek_ans[3]),};
    String[]compare=new String[]{"","","",""};
    String bmians,stepans,sysbpans,diabpans,bsans;
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
        count_bmi();
        count_step();
        count_sysbp();
        count_diabp();
        for(int i=0; i<lastweek_item.length; i++){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put( "thisweek", lastweek_item[i]);
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
                Toast.makeText(getActivity(), "顯示"+lastweek_item[position], Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

public String count_bmi(){
    if(lastweek_ans[0]<18.5){
        bmians="您的體位為 過輕";
        compare[0]=bmians;

    }
    else if(lastweek_ans[0]>=18.5 && lastweek_ans[0]<24.0){
        bmians="您的體位為 正常";
        compare[0]=bmians;

    }
    else if(lastweek_ans[0]>=24.0 && lastweek_ans[0]<27.0){
        bmians="您的體位為 過重";
        compare[0]=bmians;

    }
    else if(lastweek_ans[0]>=27.0 && lastweek_ans[0]<30.0){
        bmians="您的體位為 輕度肥胖";
        compare[0]=bmians;
    }
    else if(lastweek_ans[0]>=30.0 && lastweek_ans[0]<35.0){
        bmians="您的體位為 中度肥胖";
        compare[0]=bmians;
    }
    else if(lastweek_ans[0]>=35.0){
        bmians="您的體位為 重度肥胖";
        compare[0]=bmians;
    }

    return bmians;
}
    public String count_step(){
        if(lastweek_ans[1]<6000.0){
            stepans="距離建議步數還有 "+(6000-lastweek_ans[1])+" 步";
            compare[1]=stepans;
        } else if (lastweek_ans[1]>6000.0) {
            stepans="太棒了!! 比建議步數多了 "+(lastweek_ans[1]-6000)+" 步";
            compare[1]=stepans;
        }
        return stepans;
    }

    public String count_sysbp(){
        if(lastweek_ans[2]>=111.0 &&lastweek_ans[2]<=143.0){
            sysbpans="心肌收縮壓正常!! 正常值為 127 + - 16 (即111~143)";
            compare[2]=sysbpans;
        }
        else{
            sysbpans="心肌收縮壓異常!! 正常值為 127 + - 16 (即111~143)";
            compare[2]=sysbpans;
        }


        return sysbpans;
    }

    public String count_diabp(){
        if(lastweek_ans[3]>=67.0 &&lastweek_ans[2]<=87.0){
            sysbpans="心肌舒張壓正常!! 正常值為 77 + - 10 (即67~87)";
            compare[3]=sysbpans;
        }
        else{
            sysbpans="心肌舒張壓異常!! 正常值為 77 + - 10 (即67~87)";
            compare[3]=sysbpans;
        }


        return diabpans;
    }


}

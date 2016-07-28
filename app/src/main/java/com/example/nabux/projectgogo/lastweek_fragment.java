package com.example.nabux.projectgogo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by ITM Admin on 2016/7/28.
 */
public class lastweek_fragment extends Fragment{


    String[] item = new String[]{aa(),"2","3","4","5","6","7","8","9","10"};
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

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1,item);
        list1.setAdapter(adapter);
        return rootView;
    }
    public String aa(){
       String xx="hello"+" you";

        return xx;
    }




}

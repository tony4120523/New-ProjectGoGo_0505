package com.example.nabux.projectgogo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jack on 2016/8/27.
 */
public class thisweek_fragment extends Fragment {

    double[] thisweek_ans = new double[]{40.0,4956.0,125.0,95.0,195.0};
    String[] thisweek_item=new String[]{"本週平均bmi為："+thisweek_ans[0],"本週平均步數為："+thisweek_ans[1],"本週平均心肌收縮壓為："+thisweek_ans[2],"本週平均平均心肌舒張壓為："+thisweek_ans[3],"本週平均血糖為："+thisweek_ans[4]};
    String bmians,stepans,sysbpans,diabpans,bsans;
    TextView thisweekbmi_show,thisweekbmi_vs,thisweekstep_show,thisweekstep_vs,thisweeksysbp_show,thisweeksysbp_vs,thisweekdiabp_show,thisweekdiabp_vs,thisweekbs_show,thisweekbs_vs;
    LinearLayout ly1,ly2,ly3,ly4,ly5;
    public static thisweek_fragment newInstance() {
        thisweek_fragment fragment = new thisweek_fragment();
        return fragment;
    }

    public thisweek_fragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.thisweek_show, container, false);
        thisweekbmi_show= (TextView) rootView.findViewById(R.id.thisweekbmi_show);
        thisweekbmi_vs= (TextView) rootView.findViewById(R.id.thisweekbmi_vs);
        thisweekstep_show= (TextView) rootView.findViewById(R.id.thisweekstep_show);
        thisweekstep_vs= (TextView) rootView.findViewById(R.id.thisweekstep_vs);
        thisweeksysbp_show= (TextView) rootView.findViewById(R.id.thisweeksysbp_show);
        thisweeksysbp_vs= (TextView) rootView.findViewById(R.id.thisweeksysbp_vs);
        thisweekdiabp_show= (TextView) rootView.findViewById(R.id.thisweekdiabp_show);
        thisweekdiabp_vs= (TextView) rootView.findViewById(R.id.thisweekdiabp_vs);
        thisweekbs_show= (TextView) rootView.findViewById(R.id.thisweekbs_show);
        thisweekbs_vs= (TextView) rootView.findViewById(R.id.thisweekbs_vs);
        ly1= (LinearLayout) rootView.findViewById(R.id.ly1);
        ly2= (LinearLayout) rootView.findViewById(R.id.ly2);
        ly3= (LinearLayout) rootView.findViewById(R.id.ly3);
        ly4= (LinearLayout) rootView.findViewById(R.id.ly4);
        ly5= (LinearLayout) rootView.findViewById(R.id.ly5);
        thisweekbmi_show.setText(thisweek_item[0]);
        thisweekstep_show.setText(thisweek_item[1]);
        thisweeksysbp_show.setText(thisweek_item[2]);
        thisweekdiabp_show.setText(thisweek_item[3]);
        thisweekbs_show.setText(thisweek_item[4]);
        ly1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    ly1.setBackgroundColor(0x00000000);
                    Toast.makeText(getActivity(),bmians, Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    ly1.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    ly1.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                return false;
            }
        });

        ly2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    ly2.setBackgroundColor(0x00000000);
                    Toast.makeText(getActivity(),stepans, Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    ly2.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    ly2.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                return false;
            }
        });

        ly3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    ly3.setBackgroundColor(0x00000000);
                    Toast.makeText(getActivity(),sysbpans, Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    ly3.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    ly3.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                return false;
            }
        });

        ly4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    ly4.setBackgroundColor(0x00000000);
                    Toast.makeText(getActivity(),diabpans, Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    ly4.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    ly4.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                return false;
            }
        });

        ly5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    ly5.setBackgroundColor(0x00000000);
                    Toast.makeText(getActivity(),bsans, Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    ly5.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    ly5.setBackgroundResource(R.drawable.border1);
                    return true;
                }
                return false;
            }
        });
        count_bmi();
        count_step();
        count_sysbp();
        count_diabp();
        count_bs();
        thisweekbmi_vs.setText("    "+bmians);
        thisweekstep_vs.setText("    "+stepans);
        thisweeksysbp_vs.setText("    "+sysbpans);
        thisweekdiabp_vs.setText("    "+diabpans);
        thisweekbs_vs.setText("    "+bsans);
        return rootView;
    }

    public String count_bmi(){
        if(thisweek_ans[0]<18.5){
            bmians="您的體位為 過輕";
            thisweekbmi_vs.setTextColor(Color.BLUE);
        }
        else if(thisweek_ans[0]>=18.5 && thisweek_ans[0]<24.0){
            bmians="您的體位為 正常";
          //  thisweekbmi_vs.setTextColor(0xFF0FD00F);
        }
        else if(thisweek_ans[0]>=24.0 && thisweek_ans[0]<27.0){
            bmians="您的體位為 過重";
            thisweekbmi_vs.setTextColor(Color.RED);

        }
        else if(thisweek_ans[0]>=27.0 &&thisweek_ans[0]<30.0){

            bmians="您的體位為 輕度肥胖";
            thisweekbmi_vs.setTextColor(Color.RED);
        }
        else if(thisweek_ans[0]>=30.0 && thisweek_ans[0]<35.0){
            bmians="您的體位為 中度肥胖";
            thisweekbmi_vs.setTextColor(Color.RED);        }
        else if(thisweek_ans[0]>=35.0){
            bmians="您的體位為 重度肥胖";
            thisweekbmi_vs.setTextColor(Color.RED);
        }

        return bmians;
    }
    public String count_step(){
        if(thisweek_ans[1]<6000.0){
            stepans="距離建議步數還有 "+(6000-thisweek_ans[1])+" 步";
            thisweekstep_vs.setTextColor(Color.BLUE);
        } else if (thisweek_ans[1]>6000.0) {
            stepans="太棒了!! 比建議步數多了 "+(thisweek_ans[1]-6000)+" 步";
           // thisweekstep_vs.setTextColor(0xFF0FD00F);
        }
        return stepans;
    }

    public String count_sysbp(){
        if(thisweek_ans[2]>=111.0 &&thisweek_ans[2]<=143.0){
            sysbpans="心肌收縮壓正常!! 正常值為 127 + - 16 (即111~143)";
           // thisweeksysbp_vs.setTextColor(0xFF0FD00F);
        }
        else{
            sysbpans="心肌收縮壓異常!! 正常值為 127 + - 16 (即111~143)";
            thisweeksysbp_vs.setTextColor(Color.RED);
        }
        return sysbpans;
    }

    public String count_diabp(){
        if(thisweek_ans[3]>=67.0 &&thisweek_ans[2]<=87.0){
            diabpans="心肌舒張壓正常!! 正常值為 77 + - 10 (即67~87)";
           // thisweekdiabp_vs.setTextColor(0xFF0FD00F);
        }
        else{
            diabpans="心肌舒張壓異常!! 正常值為 77 + - 10 (即67~87)";
            thisweekdiabp_vs.setTextColor(Color.RED);
        }
        return diabpans;
    }

    public String count_bs(){
        if(thisweek_ans[4]>=200.0){
            bsans="血糖異常!! 正常值為200以下";
            thisweekbs_vs.setTextColor(Color.RED);
             }
        else {
            bsans="血糖正常!!";
            //thisweekbs_vs.setTextColor(0xFF0FD00F);
        }
        return bsans;
    }

}

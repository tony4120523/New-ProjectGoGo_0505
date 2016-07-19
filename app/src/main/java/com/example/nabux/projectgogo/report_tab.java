package com.example.nabux.projectgogo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class report_tab extends AppCompatActivity {
    private android.support.design.widget.TabLayout mTabs;
    double sumbmi,avgbmi,sumsysbp,sumdiabp,avgsysbp,avgdiabp = 0;
    int sumstep,avgstep = 0;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_tab);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("我的健康報告 page ver.");

        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tabs);
        mTabs.addTab(mTabs.newTab().setText("Tab 1"));
        mTabs.addTab(mTabs.newTab().setText("Tab 2"));
        mTabs.addTab(mTabs.newTab().setText("Tab 3"));






        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        mTabs.setupWithViewPager(mViewPager);
    }
    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title="";
            switch(position) {
                case 0:
                    title="本週";
                    break;

                case 1:
                    title="上周";
                    break;
                case 2:
                    title="最近三個月";
            }
            return title;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int woo=-1;
            int ss=-1;
            switch(position){
                case 0:
                    woo=R.layout.reportpage_thisweek;
                    ss=0;

                    break;

                case 1:
                    woo=R.layout.reportpage_lastweek;
                    ss=1;
                    break;
                case 2:
                    woo=R.layout.reportpage_theremonth;
                    ss=2;
                    break;


            }


            View view = getLayoutInflater().inflate(woo,
                    container, false);
            container.addView(view);
            TextView avgbmishow = (TextView) view.findViewById(R.id.avgbmishow);
            TextView avgstepshow = (TextView) view.findViewById(R.id.avgstepshow);
            TextView avgsysbpshow = (TextView) view.findViewById(R.id.avgsysbpshow);
            TextView avgdiabpshow = (TextView) view.findViewById(R.id.avgdiabpshow);
            if(ss==0) {
                avgbmi=22.5;
                avgstep=6596;
                avgsysbp=129.5;
                avgdiabp=164.5;
                avgbmishow.setText("平均bmi為："+avgbmi);
                //Log.d("step avg here",Integer.toString(avgstep));
                avgstepshow.setText("平均步數為："+avgstep);
                //Log.d("sysbp avg here",Double.toString(avgsysbp));
                avgsysbpshow.setText("平均心肌收縮壓為："+avgsysbp);
                //Log.d("diabp avg here",Double.toString(avgdiabp));
                avgdiabpshow.setText("平均心肌舒張壓為："+avgdiabp);
            }
            else if(ss==1){
                avgbmi=28.5;
                avgstep=7596;
                avgsysbp=145.5;
                avgdiabp=184.5;
                avgbmishow.setText("平均bmi為："+avgbmi);
                //Log.d("step avg here",Integer.toString(avgstep));
                avgstepshow.setText("平均步數為："+avgstep);
                //Log.d("sysbp avg here",Double.toString(avgsysbp));
                avgsysbpshow.setText("平均心肌收縮壓為："+avgsysbp);
                //Log.d("diabp avg here",Double.toString(avgdiabp));
                avgdiabpshow.setText("平均心肌舒張壓為："+avgdiabp);

            }
            else if(ss==2){
                avgbmi=21.2;
                avgstep=9563;
                avgsysbp=155.5;
                avgdiabp=164.5;
                avgbmishow.setText("平均bmi為："+avgbmi);
                //Log.d("step avg here",Integer.toString(avgstep));
                avgstepshow.setText("平均步數為："+avgstep);
                //Log.d("sysbp avg here",Double.toString(avgsysbp));
                avgsysbpshow.setText("平均心肌收縮壓為："+avgsysbp);
                //Log.d("diabp avg here",Double.toString(avgdiabp));
                avgdiabpshow.setText("平均心肌舒張壓為："+avgdiabp);

            }
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}

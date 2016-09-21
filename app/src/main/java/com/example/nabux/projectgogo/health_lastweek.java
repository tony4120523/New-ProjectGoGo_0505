package com.example.nabux.projectgogo;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class health_lastweek extends AppCompatActivity {
    Handler myHandler;
    int[] step_buffer;
    double[] bmi_buffer;
    double[] bp_sys_buffer;
    double[] bp_dia_buffer;
    String[]state;
    int[]bs_buffer;
    int[]pulse_buffer;
    private Session session;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    ListView list_health_lastweek;
    private SimpleAdapter adapter;
    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    private static final String url_bmi_detials = "http://www.hth96.me/nabu_connect/query_bmi.php";
    private static final String url_step_detials = "http://www.hth96.me/nabu_connect/query_steps_weekly.php";
    private static final String url_bp_detials = "http://www.hth96.me/nabu_connect/query_bp_weekly.php";
    private static final String url_bs_detials = "http://45.55.213.89/nabu_connect/query_bs_weekly.php";
    private static final String url_pulse_detials = "http://45.55.213.89/nabu_connect/query_pulse_weekly.php";
    private static final String TAG = health_lastweek.class.getSimpleName();
    String bmians,stepans,sysbpans,diabpans,bsans,pulseans;
    double sumbmi,avgbmi,sumsysbp,sumdiabp,avgsysbp,avgdiabp;
    int sumstep,avgstep,avgbs,sumbs,sumpulse,avgpulse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_lastweek);
        list_health_lastweek= (ListView) findViewById(R.id.list_health_lastweek);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("上週健康報告 9/11 - 9/17");
        session = new Session(getApplicationContext());


        step_buffer = new int[100];
        bmi_buffer = new double[100];
        bp_sys_buffer = new double[100];
        bp_dia_buffer = new double[100];
        bs_buffer=new int[100];
        pulse_buffer=new int[100];
        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        count_bmi();
                        count_step();
                        count_sysbp();
                        count_diabp();
                        count_bs();
                        count_pulse();
                        String[]state=new String[]{"平均bmi為："+avgbmi,"平均步數為："+avgstep+" 步","平均心肌收縮壓為："+avgsysbp+" mmhg","平均心肌舒張壓為："+avgdiabp+" mmhg","平均血糖為："+avgbs+" mg/dl","平均脈搏為："+avgpulse+" 下/每分"};
                        String[]compare=new String[]{bmians,stepans,sysbpans,diabpans,bsans,pulseans};
                        for(int i=0; i<state.length; i++){
                            HashMap<String,String> item = new HashMap<String,String>();
                            item.put( "stated", state[i]);
                            item.put( "compared",compare[i] );
                            list.add( item );
                        }

                        adapter = new SimpleAdapter(
                                health_lastweek.this,
                                list,
                                android.R.layout.simple_list_item_2,
                                new String[] { "stated","compared" },
                                new int[] { android.R.id.text1, android.R.id.text2 }
                        );
                        list_health_lastweek.setAdapter(adapter);
                        /*Log.d("bmi avg here",Double.toString(avgbmi));
                        Log.d("step avg here",Integer.toString(avgstep));
                        Log.d("sysbp avg here",Double.toString(avgsysbp));
                        Log.d("diabp avg here",Double.toString(avgdiabp));
                        Log.d("bs avg here",Double.toString(avgbs));
                        Log.d("pulse avg here",Double.toString(avgpulse));*/

                    default:
                        break;
                }
            }
        };

        new Getdata().execute();






    }
    class Getdata extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(health_lastweek.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

            String id = session.getUserID();

            try {

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));




                JSONObject json = jsonParser.makeHttpRequest(url_step_detials, "GET", params);
                // check your log for json response
                Log.d(TAG, json.toString());
                // json success tag
                int success;
                success = json.getInt("success");
                if (success == 1) {
                    // successfully received json
                    JSONArray userArr = json.getJSONArray("steps"); // JSON Array
                    //each step is here
                    for(int i=0; i<userArr.length(); i++) {
                        step_buffer[i] = Integer.parseInt(userArr.getString(i));
                        sumstep+=step_buffer[i];
                    }
                    avgstep=sumstep/userArr.length();
                }else{
                    // user with id not found

                }

                JSONObject json2 = jsonParser.makeHttpRequest(url_bmi_detials, "GET", params);
                // check your log for json response
                Log.d(TAG, json2.toString());
                // json success tag
                int success2;
                success2 = json2.getInt("success");
                if (success2 == 1) {
                    // successfully received json
                    JSONArray userArr = json2.getJSONArray("bmi"); // JSON Array


                    //each bmi is here
                    for(int i=0; i<userArr.length(); i++) {

                        bmi_buffer[i] = Double.parseDouble(userArr.getString(i));
                        sumbmi+=bmi_buffer[i];


                    }

                    avgbmi=sumbmi/userArr.length();
                    avgbmi=((Math.round(avgbmi*100.0))/100.0);

                }else{
                    // user with id not found
                }

                JSONObject json3 = jsonParser.makeHttpRequest(url_bp_detials, "GET", params);
                // check your log for json response
                Log.d(TAG, json3.toString());
                // json success tag
                int success3;
                success3 = json3.getInt("success");
                if (success3 == 1) {
                    // successfully received json
                    JSONArray bp_sys_Arr = json3.getJSONArray("bp_sys"); // JSON Array
                    JSONArray bp_dia_Arr = json3.getJSONArray("bp_dia");
                    for(int i=0; i<bp_sys_Arr.length(); i++) {

                        bp_sys_buffer[i] = Double.parseDouble(bp_sys_Arr.getString(i));
                        bp_dia_buffer[i] = Double.parseDouble(bp_dia_Arr.getString(i));
                        sumsysbp+=bp_sys_buffer[i];
                        sumdiabp+=bp_dia_buffer[i];


                    }

                    avgsysbp=sumsysbp/bp_sys_Arr.length();
                    avgsysbp=((Math.round(avgsysbp*100.0))/100.0);

                    avgdiabp=sumdiabp/bp_dia_Arr.length();
                    avgdiabp=((Math.round(avgdiabp*100.0))/100.0);

                }else{
                    // user with id not found

                }

                JSONObject json4 = jsonParser.makeHttpRequest(url_bs_detials, "GET", params);
                // check your log for json response
                Log.d(TAG, json4.toString());
                // json success tag
                int success4;
                success4 = json4.getInt("success");
                if (success4 == 1) {
                    // successfully received json
                    JSONArray bs_Arr = json4.getJSONArray("bloodsugar"); // JSON Array
                    for(int i=0; i<bs_Arr.length(); i++) {
                       bs_buffer[i] = Integer.parseInt(bs_Arr.getString(i));
                       sumbs+=bs_buffer[i];
                    }
                    avgbs=sumbs/bs_Arr.length();

                }else{
                    // user with id not found

                }

                JSONObject json5 = jsonParser.makeHttpRequest(url_pulse_detials, "GET", params);
                // check your log for json response
                Log.d(TAG, json5.toString());
                // json success tag
                int success5;
                success5 = json5.getInt("success");
                if (success5 == 1) {
                    // successfully received json
                    JSONArray pulse_Arr = json5.getJSONArray("pulse"); // JSON Array
                    for(int i=0; i<pulse_Arr.length(); i++) {
                        pulse_buffer[i] = Integer.parseInt(pulse_Arr.getString(i));
                        sumpulse+=pulse_buffer[i];
                    }
                    avgpulse=sumpulse/pulse_Arr.length();

                }else{
                    // user with id not found

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
            myHandler.sendEmptyMessage(0);

        }
    }
    public String count_bmi(){
        if(avgbmi<18.5){
            bmians="您的體位為 過輕";

        }
        else if(avgbmi>=18.5 && avgbmi<24.0){
            bmians="您的體位為 正常";

        }
        else if(avgbmi>=24.0 && avgbmi<27.0){
            bmians="您的體位為 過重";


        }
        else if(avgbmi>=27.0 &&avgbmi<30.0){

            bmians="您的體位為 輕度肥胖";

        }
        else if(avgbmi>=30.0 && avgbmi<35.0){
            bmians="您的體位為 中度肥胖";
               }
        else if(avgbmi>=35.0){
            bmians="您的體位為 重度肥胖";

        }

        return bmians;
    }
    public String count_step(){
        if(avgstep<6000.0){
            stepans="距離建議步數還有 "+(6000-avgstep)+" 步";

        } else if (avgstep>6000.0) {
            stepans="太棒了!! 比建議步數多了 "+(avgstep-6000)+" 步";
            // thisweekstep_vs.setTextColor(0xFF0FD00F);
        }
        return stepans;
    }

    public String count_sysbp(){
        if(avgsysbp>=111.0 &&avgsysbp<=143.0){
            sysbpans="心肌收縮壓正常!! 正常值為 127 + - 16 (即111~143)";
            // thisweeksysbp_vs.setTextColor(0xFF0FD00F);
        }
        else{
            sysbpans="心肌收縮壓異常!! 正常值為 127 + - 16 (即111~143)";

        }
        return sysbpans;
    }

    public String count_diabp(){
        if(avgdiabp>=67.0 &&avgdiabp<=87.0){
            diabpans="心肌舒張壓正常!! 正常值為 77 + - 10 (即67~87)";
            // thisweekdiabp_vs.setTextColor(0xFF0FD00F);
        }
        else{
            diabpans="心肌舒張壓異常!! 正常值為 77 + - 10 (即67~87)";

        }
        return diabpans;
    }

    public String count_bs(){
        if(avgbs>=200.0){
            bsans="血糖異常!! 正常值為200以下";

        }
        else {
            bsans="血糖正常!!";
            //thisweekbs_vs.setTextColor(0xFF0FD00F);
        }
        return bsans;
    }

    public String count_pulse(){
        if(avgpulse<64){
            pulseans="脈博偏慢";
        }
        else if(avgpulse>=65.0 &&avgpulse<=80.0){
            pulseans="脈博正常";
        }
        else if(avgpulse>=80.0 &&avgpulse<=95.0){
            pulseans="脈博偏快";
        }
        else if(avgpulse>=96.0){
            pulseans="脈博太快";
        }
        return pulseans;
    }
}

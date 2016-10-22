package com.example.nabux.projectgogo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nabux.projectgogo.MyHealthChart.BP_chart.BP_TABBED;
import com.example.nabux.projectgogo.MyHealthChart.BS_chart.BS_TEBBED;
import com.example.nabux.projectgogo.MyHealthChart.Pulse_chart.PULSE_TABBED;
import com.example.nabux.projectgogo.MyHealthChart.Step_chart.STEP_TEBBED;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectChartActivity extends AppCompatActivity {

    Button btnstep,btnbmi,btnhg,btnbs,btnpulse;
    Handler myHandler;
    private ProgressDialog pDialog;
    private Session session;
    private static final String TAG = SelectChartActivity.class.getSimpleName();
    JSONParser jsonParser = new JSONParser();
    //step
    private static final String url_this_step_detials = "http://www.hth96.me/nabu_connect/query_steps_weekly.php";
    private static final String url_last_step_detials = "http://45.55.213.89/nabu_connect/query_steps_lastweek.php";
    private static final String url_month_step_detials = "http://45.55.213.89/nabu_connect/query_steps_3month.php";
    //bp
    private static final String url_last_bp_detials = "http://45.55.213.89/nabu_connect/query_bp_lastweek.php";
    private static final String url_this_bp_detials = "http://www.hth96.me/nabu_connect/query_bp_weekly.php";
    private static final String url_month_bp_detials = "http://45.55.213.89/nabu_connect/query_bp_3month.php";
    //bs
    private static final String url_this_bs_detials = "http://45.55.213.89/nabu_connect/query_bs_weekly.php";
    private static final String url_last_bs_detials = "http://45.55.213.89/nabu_connect/query_bs_lastweek.php";
    private static final String url_month_bs_detials = "http://45.55.213.89/nabu_connect/query_bs_3month.php";
    //bmi
    private static final String url_bmi_detials = "http://www.hth96.me/nabu_connect/query_bmi.php";
    //pulse
    private static final String url_this_pulse_detials = "http://45.55.213.89/nabu_connect/query_pulse_weekly.php";
    private static final String url_last_pulse_detials = "http://45.55.213.89/nabu_connect/query_pulse_lastweek.php";
    private static final String url_month_pulse_detials = "http://45.55.213.89/nabu_connect/query_pulse_3month.php";
    int[]last_step_buffer;
    int[]this_step_buffer;
    int[]month_step_buffer;
    int[]this_bs_buffer;
    int[]last_bs_buffer;
    int[]month_bs_buffer;
    int[]this_pulse_buffer;
    int[]last_pulse_buffer;
    int[]month_pulse_buffer;
    double[] bmi_buffer;
    double[] this_bp_sys_buffer;
    double[] this_bp_dia_buffer;
    double[] last_bp_sys_buffer;
    double[] last_bp_dia_buffer;
    double[] month_bp_sys_buffer;
    double[] month_bp_dia_buffer;
    public NetworkStateReceiver networkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartselect);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("我的健康圖表");
        session = new Session(getApplicationContext());
        btnstep= (Button) findViewById(R.id.btnstep);
        //btnbmi= (Button) findViewById(R.id.btnbmi);
        btnhg= (Button) findViewById(R.id.btnhg);
        btnbs= (Button) findViewById(R.id.btnbs);
        btnpulse= (Button) findViewById(R.id.btnpulse);
        Intent in = getIntent();
        last_step_buffer = new int[100];
        this_step_buffer = new int[100];
        month_step_buffer = new int[100];
        this_bs_buffer=new int[100];
        last_bs_buffer=new int[100];
        month_bs_buffer=new int[100];
        this_pulse_buffer=new int[100];
        last_pulse_buffer=new int[100];
        month_pulse_buffer=new int[100];
        bmi_buffer = new double[100];
        this_bp_sys_buffer = new double[100];
        this_bp_dia_buffer = new double[100];
        last_bp_sys_buffer = new double[100];
        last_bp_dia_buffer = new double[100];
        month_bp_sys_buffer = new double[100];
        month_bp_dia_buffer = new double[100];
        networkStateReceiver = new NetworkStateReceiver();


        btnstep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:

                                Intent in = new Intent(getApplicationContext(), STEP_TEBBED.class);
                                in.putExtra("this_step_buffer", this_step_buffer);
                                in.putExtra("last_step_buffer", last_step_buffer);
                                in.putExtra("month_step_buffer", month_step_buffer);
                                startActivity(in);
                                break;
                            default:
                                break;
                        }
                    }
                };

                // Getting data in background thread
                new Getdata("step").execute();


            }
        });

        /*btnbmi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent in = new Intent(getApplicationContext(), BMIActivity.class);
                //startActivity(in);

                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Log.d(TAG, "NOW BMI_BUFFER[0] : " + bmi_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), BMIActivity.class);
                                in.putExtra("bmi_buffer", bmi_buffer);
                                startActivity(in);
                                break;
                            default:
                                break;
                        }
                    }
                };

                // Getting data in background thread
                new Getdata("bmi").execute();
            }
        });*/

        btnhg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent in = new Intent(getApplicationContext(), BPActivity.class);
                //startActivity(in);
                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Log.d(TAG, "THIS BP_SYS_BUFFER[0] : " + this_bp_sys_buffer[0]);
                                Log.d(TAG, "THIS BP_DIA_BUFFER[0] : " + this_bp_dia_buffer[0]);
                                Log.d(TAG, "LAST BP_SYS_BUFFER[0] : " + last_bp_sys_buffer[0]);
                                Log.d(TAG, "LAST BP_DIA_BUFFER[0] : " + last_bp_dia_buffer[0]);
                                Log.d(TAG, "MONTH BP_SYS_BUFFER[0] : " + month_bp_sys_buffer[0]);
                                Log.d(TAG, "MONTH BP_DIA_BUFFER[0] : " + month_bp_dia_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), BP_TABBED.class);
                                in.putExtra("this_bp_sys_buffer", this_bp_sys_buffer);
                                in.putExtra("this_bp_dia_buffer", this_bp_dia_buffer);
                                in.putExtra("last_bp_sys_buffer", last_bp_sys_buffer);
                                in.putExtra("last_bp_dia_buffer", last_bp_dia_buffer);
                                in.putExtra("month_bp_sys_buffer", month_bp_sys_buffer);
                                in.putExtra("month_bp_dia_buffer", month_bp_dia_buffer);
                                startActivity(in);
                                break;
                            default:
                                break;
                        }
                    }
                };

                // Getting data in background thread
                new Getdata("bp").execute();

            }
        });

        btnbs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent in = new Intent(getApplicationContext(), BMIActivity.class);
                //startActivity(in);

                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Log.d(TAG, "NOW THIS_BS_BUFFER[0] : " + this_bs_buffer[0]);
                                Log.d(TAG, "NOW LAST_BS_BUFFER[0] : " + last_bs_buffer[0]);
                                Log.d(TAG, "NOW MONTH_BS_BUFFER[0] : " + month_bs_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), BS_TEBBED.class);
                                in.putExtra("this_bs_buffer", this_bs_buffer);
                                in.putExtra("last_bs_buffer", last_bs_buffer);
                                in.putExtra("month_bs_buffer", month_bs_buffer);
                                startActivity(in);
                                break;
                            default:
                                break;
                        }
                    }
                };

                // Getting data in background thread
                new Getdata("bs").execute();
            }
        });

        btnpulse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Intent in = new Intent(getApplicationContext(), BMIActivity.class);
                //startActivity(in);

                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Log.d(TAG, "THIS PULSE_BUFFER[0] : " + this_pulse_buffer[0]);
                                Log.d(TAG, "LAST PULSE_BUFFER[0] : " + last_pulse_buffer[0]);
                                Log.d(TAG, "MONTH PULSE_BUFFER[0] : " + month_pulse_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), PULSE_TABBED.class);
                                in.putExtra("this_pulse_buffer", this_pulse_buffer);
                                in.putExtra("last_pulse_buffer", last_pulse_buffer);
                                in.putExtra("month_pulse_buffer", month_pulse_buffer);
                                startActivity(in);
                                break;
                            default:
                                break;
                        }
                    }
                };

                // Getting data in background thread
                new Getdata("pulse").execute();
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    public void onPause() {
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }


    class Getdata extends AsyncTask<String, String, String> {
        String describe;
        Getdata(String desc) {
            describe = desc;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SelectChartActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

            String id = session.getUserID();
            Log.d(TAG, "SESSION ID : " + id);


            try {

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));

                Log.d(TAG, "DESCRIBE : " + describe);

                if(describe.equals("step")) {
                    JSONObject thisjson = jsonParser.makeHttpRequest(url_this_step_detials, "GET", params);
                    JSONObject lastjson = jsonParser.makeHttpRequest(url_last_step_detials, "GET", params);
                    JSONObject monthjson = jsonParser.makeHttpRequest(url_month_step_detials, "GET", params);

                    // check your log for json response
                    Log.d(TAG, "RETURN THISJSON : " + thisjson.toString());
                    Log.d(TAG, "RETURN LASTJSON : " + lastjson.toString());
                    Log.d(TAG, "RETURN MONTHJSON : " + monthjson.toString());
                    // json success tag
                    int this_success,last_success,month_success;
                    this_success = thisjson.getInt("success");
                    last_success = lastjson.getInt("success");
                    month_success = monthjson.getInt("success");
                    if (this_success == 1 && last_success == 1 && month_success == 1) {
                        // successfully received json
                        JSONArray this_userArr = thisjson.getJSONArray("steps"); // JSON Array
                        JSONArray last_userArr = lastjson.getJSONArray("steps"); // JSON Array
                        JSONArray month_userArr = monthjson.getJSONArray("steps"); // JSON Array
                        Log.d(TAG, "The this_userArr is " + this_userArr.toString());
                        Log.d(TAG, "The last_ userArr is " + last_userArr.toString());
                        Log.d(TAG, "The month_ userArr is " + month_userArr.toString());

                        //each step is here
                        for(int i=0; i<this_userArr.length(); i++) {
                            this_step_buffer[i] = Integer.parseInt(this_userArr.getString(i));
                            Log.d(TAG, "THISWEEK STEP SHOW HERE : " + Integer.toString(this_step_buffer[i]));
                        }
                        for(int i=0; i<last_userArr.length(); i++) {
                            last_step_buffer[i] = Integer.parseInt(last_userArr.getString(i));
                            Log.d(TAG, "LASTWEEK STEP SHOW HERE : " + Integer.toString(last_step_buffer[i]));
                        }
                        for(int i=0; i<month_userArr.length(); i++) {
                            month_step_buffer[i] = Integer.parseInt(month_userArr.getString(i));
                            Log.d(TAG, "MONTH STEP SHOW HERE : " + Integer.toString(month_step_buffer[i]));
                        }

                    }else{
                        // user with id not found
                        Log.d(TAG, "QUERY STEP NOT FOUND");

                    }
                }else if(describe.equals("bmi")) {
                    JSONObject json = jsonParser.makeHttpRequest(url_bmi_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN JSON : " + json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray userArr = json.getJSONArray("bmi"); // JSON Array
                        Log.d(TAG, "The userArr is" + userArr.toString());

                        //each bmi is here
                        for(int i=0; i<userArr.length(); i++) {

                            bmi_buffer[i] = Double.parseDouble(userArr.getString(i));
                            Log.d(TAG, "EVERY BMI SHOW HERE : " + Double.toString(bmi_buffer[i]));
                        }

                    }else{
                        // user with id not found
                        Log.d(TAG, "QUERY BMI NOT FOUND");
                    }
                }else if(describe.equals("bp")) {
                    JSONObject thisjson = jsonParser.makeHttpRequest(url_this_bp_detials, "GET", params);
                    JSONObject lastjson = jsonParser.makeHttpRequest(url_last_bp_detials, "GET", params);
                    JSONObject monthjson = jsonParser.makeHttpRequest(url_month_bp_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN THISJSON : " + thisjson.toString());
                    Log.d(TAG, "RETURN LASTJSON : " + lastjson.toString());
                    Log.d(TAG, "RETURN MONTHJSON : " + monthjson.toString());
                    // json success tag
                    int thissuccess,lastsuccess,monthsuccess;
                    thissuccess = thisjson.getInt("success");
                    lastsuccess = lastjson.getInt("success");
                    monthsuccess = monthjson.getInt("success");

                    if (thissuccess == 1 && lastsuccess == 1 && monthsuccess == 1) {
                        // successfully received json
                        JSONArray this_bp_sys_Arr = thisjson.getJSONArray("bp_sys"); // JSON Array
                        JSONArray this_bp_dia_Arr = thisjson.getJSONArray("bp_dia");
                        JSONArray last_bp_sys_Arr = lastjson.getJSONArray("bp_sys"); // JSON Array
                        JSONArray last_bp_dia_Arr = lastjson.getJSONArray("bp_dia");
                        JSONArray month_bp_sys_Arr = monthjson.getJSONArray("bp_sys"); // JSON Array
                        JSONArray month_bp_dia_Arr = monthjson.getJSONArray("bp_dia");
                        Log.d(TAG, "THIS BP SYS ARR" + this_bp_sys_Arr.toString());
                        Log.d(TAG ,"THIS BP DIA ARR" + this_bp_dia_Arr.toString());
                        Log.d(TAG, "LAST BP SYS ARR" + last_bp_sys_Arr.toString());
                        Log.d(TAG ,"LAST BP DIA ARR" + last_bp_dia_Arr.toString());
                        Log.d(TAG, "MONTH BP SYS ARR" + month_bp_sys_Arr.toString());
                        Log.d(TAG ,"MONTH BP DIA ARR" + month_bp_dia_Arr.toString());


                        for(int i=0; i<this_bp_sys_Arr.length(); i++) {

                            this_bp_sys_buffer[i] = Double.parseDouble(this_bp_sys_Arr.getString(i));
                            this_bp_dia_buffer[i] = Double.parseDouble(this_bp_dia_Arr.getString(i));
                            Log.d(TAG, "THISWEEK SYS BP SHOW HERE : " + Double.toString(this_bp_sys_buffer[i]));
                            Log.d(TAG ,"THISWEEK DIA BP SHOW HERE : " + Double.toString(this_bp_dia_buffer[i]));
                        }
                        for(int i=0; i<last_bp_sys_Arr.length(); i++) {

                            last_bp_sys_buffer[i] = Double.parseDouble(last_bp_sys_Arr.getString(i));
                            last_bp_dia_buffer[i] = Double.parseDouble(last_bp_dia_Arr.getString(i));
                            Log.d(TAG, "LASTWEEK SYS BP SHOW HERE : " + Double.toString(last_bp_sys_buffer[i]));
                            Log.d(TAG ,"LASTWEEK DIA BP SHOW HERE : " + Double.toString(last_bp_dia_buffer[i]));
                        }
                        for(int i=0; i<month_bp_sys_Arr.length(); i++) {

                            month_bp_sys_buffer[i] = Double.parseDouble(month_bp_sys_Arr.getString(i));
                            month_bp_dia_buffer[i] = Double.parseDouble(month_bp_dia_Arr.getString(i));
                            Log.d(TAG, "MONTHWEEK SYS BP SHOW HERE : " + Double.toString(month_bp_sys_buffer[i]));
                            Log.d(TAG ,"MONTHWEEK DIA BP SHOW HERE : " + Double.toString(month_bp_dia_buffer[i]));
                        }

                    }else{
                        // user with id not found
                        Log.d(TAG, "QUERY BP NOT FOUND");
                    }
                }else if(describe.equals("bs")) {
                    JSONObject thisjson = jsonParser.makeHttpRequest(url_this_bs_detials, "GET", params);
                    JSONObject lastjson = jsonParser.makeHttpRequest(url_last_bs_detials, "GET", params);
                    JSONObject monthjson = jsonParser.makeHttpRequest(url_month_bs_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN THIS JSON : " + thisjson.toString());
                    Log.d(TAG, "RETURN LAST JSON : " + lastjson.toString());
                    Log.d(TAG, "RETURN MONTH JSON : " + monthjson.toString());
                    // json success tag
                    int thissuccess,lastsuccess,monthsuccess;
                    thissuccess = thisjson.getInt("success");
                    lastsuccess = lastjson.getInt("success");
                    monthsuccess = monthjson.getInt("success");
                    if (thissuccess == 1 && lastsuccess == 1 && monthsuccess == 1) {
                        // successfully received json
                        JSONArray thisuserArr = thisjson.getJSONArray("bloodsugar"); // JSON Array
                        JSONArray lastuserArr = lastjson.getJSONArray("bloodsugar"); // JSON Array
                        JSONArray monthuserArr = monthjson.getJSONArray("bloodsugar"); // JSON Array
                        Log.d(TAG, "The this userArr is" + thisuserArr.toString());
                        Log.d(TAG, "The last userArr is" + lastuserArr.toString());
                        Log.d(TAG, "The month userArr is" + monthuserArr.toString());

                        //each bmi is here
                        for(int i=0; i<thisuserArr.length(); i++) {

                            this_bs_buffer[i] = Integer.parseInt(thisuserArr.getString(i));
                            Log.d(TAG, "THIS BS SHOW HERE : " + Integer.toString(this_bs_buffer[i]));
                        }
                        for(int i=0; i<lastuserArr.length(); i++) {

                            last_bs_buffer[i] = Integer.parseInt(lastuserArr.getString(i));
                            Log.d(TAG, "LAST BS SHOW HERE : " + Integer.toString(last_bs_buffer[i]));
                        }
                        for(int i=0; i<monthuserArr.length(); i++) {

                            month_bs_buffer[i] = Integer.parseInt(monthuserArr.getString(i));
                            Log.d(TAG, "MONTH BS SHOW HERE : " + Integer.toString(month_bs_buffer[i]));
                        }

                    }else{
                        // user with id not found
                        Log.d(TAG, "QUERY BS NOT FOUND");
                    }
                }else if(describe.equals("pulse")) {
                    JSONObject thisjson = jsonParser.makeHttpRequest(url_this_pulse_detials, "GET", params);
                    JSONObject lastjson = jsonParser.makeHttpRequest(url_last_pulse_detials, "GET", params);
                    JSONObject monthjson = jsonParser.makeHttpRequest(url_month_pulse_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN THIS JSON : " + thisjson.toString());
                    Log.d(TAG, "RETURN LAST JSON : " + lastjson.toString());
                    Log.d(TAG, "RETURN MONTH JSON : " + monthjson.toString());
                    // json success tag
                    int thissuccess,lastsuccess,monthsuccess;
                    thissuccess = thisjson.getInt("success");
                    lastsuccess = lastjson.getInt("success");
                    monthsuccess = monthjson.getInt("success");
                    if (thissuccess == 1 && lastsuccess == 1 && monthsuccess == 1) {
                        // successfully received json
                        JSONArray thisuserArr = thisjson.getJSONArray("pulse"); // JSON Array
                        JSONArray lastuserArr = lastjson.getJSONArray("pulse"); // JSON Array
                        JSONArray monthuserArr = monthjson.getJSONArray("pulse"); // JSON Array
                        Log.d(TAG, "The this userArr is" + thisuserArr.toString());
                        Log.d(TAG, "The last userArr is" + lastuserArr.toString());
                        Log.d(TAG, "The month userArr is" + monthuserArr.toString());


                        //each bmi is here
                        for(int i=0; i<thisuserArr.length(); i++) {
                            this_pulse_buffer[i] = Integer.parseInt(thisuserArr.getString(i));
                            Log.d(TAG, "THIS PULSE SHOW HERE : " + Integer.toString(this_pulse_buffer[i]));
                        }
                        for(int i=0; i<lastuserArr.length(); i++) {
                            last_pulse_buffer[i] = Integer.parseInt(lastuserArr.getString(i));
                            Log.d(TAG, "LAST PULSE SHOW HERE : " + Integer.toString(last_pulse_buffer[i]));
                        }
                        for(int i=0; i<monthuserArr.length(); i++) {
                            month_pulse_buffer[i] = Integer.parseInt(monthuserArr.getString(i));
                            Log.d(TAG, "MONTH PULSE SHOW HERE : " + Integer.toString(month_pulse_buffer[i]));
                        }

                    }else{
                        // user with id not found
                        Log.d(TAG, "QUERY BS NOT FOUND");
                    }
                }

                else {
                    //wait to add...
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
    public void onBackPressed() {
        finish();
    }

}

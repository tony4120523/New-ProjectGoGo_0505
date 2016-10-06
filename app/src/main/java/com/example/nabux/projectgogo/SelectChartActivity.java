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
    private static final String url_step_detials = "http://www.hth96.me/nabu_connect/query_steps_weekly.php";
    private static final String url_bp_detials = "http://www.hth96.me/nabu_connect/query_bp_weekly.php";
    private static final String url_bs_detials = "http://45.55.213.89/nabu_connect/query_bs_weekly.php";
    private static final String url_bmi_detials = "http://www.hth96.me/nabu_connect/query_bmi.php";
    private static final String url_pulse_detials = "http://45.55.213.89/nabu_connect/query_pulse_weekly.php";
    int[] step_buffer;
    int[]bs_buffer;
    int[]pulse_buffer;
    double[] bmi_buffer;
    double[] bp_sys_buffer;
    double[] bp_dia_buffer;
    public NetworkStateReceiver networkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartselect);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("我的健康圖表");
        session = new Session(getApplicationContext());
        btnstep= (Button) findViewById(R.id.btnstep);
        btnbmi= (Button) findViewById(R.id.btnbmi);
        btnhg= (Button) findViewById(R.id.btnhg);
        btnbs= (Button) findViewById(R.id.btnbs);
        btnpulse= (Button) findViewById(R.id.btnpulse);
        Intent in = getIntent();
        step_buffer = new int[100];
        bs_buffer=new int[100];
        pulse_buffer=new int[100];
        bmi_buffer = new double[100];
        bp_sys_buffer = new double[100];
        bp_dia_buffer = new double[100];
        networkStateReceiver = new NetworkStateReceiver();


        btnstep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:

                                Intent in = new Intent(getApplicationContext(), StepActivity.class);
                                in.putExtra("step_buffer", step_buffer);
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

        btnbmi.setOnClickListener(new View.OnClickListener() {

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
        });

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
                                Log.d(TAG, "NOW BP_SYS_BUFFER[0] : " + bp_sys_buffer[0]);
                                Log.d(TAG, "NOW BP_DIA_BUFFER[0] : " + bp_dia_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), BPActivity.class);
                                in.putExtra("bp_sys_buffer", bp_sys_buffer);
                                in.putExtra("bp_dia_buffer", bp_dia_buffer);
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
                                Log.d(TAG, "NOW BS_BUFFER[0] : " + bs_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), BSActivity.class);
                                in.putExtra("bs_buffer", bs_buffer);
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
                                Log.d(TAG, "NOW PULSE_BUFFER[0] : " + pulse_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), PulseActivity.class);
                                in.putExtra("pulse_buffer", pulse_buffer);
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
                    JSONObject json = jsonParser.makeHttpRequest(url_step_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN JSON : " + json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray userArr = json.getJSONArray("steps"); // JSON Array
                        Log.d(TAG, "The userArr is " + userArr.toString());

                        //each step is here
                        for(int i=0; i<userArr.length(); i++) {

                            step_buffer[i] = Integer.parseInt(userArr.getString(i));
                            Log.d(TAG, "EVERY STEP SHOW HERE : " + Integer.toString(step_buffer[i]));
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
                    JSONObject json = jsonParser.makeHttpRequest(url_bp_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN JSON : " + json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray bp_sys_Arr = json.getJSONArray("bp_sys"); // JSON Array
                        JSONArray bp_dia_Arr = json.getJSONArray("bp_dia");
                        Log.d(TAG, "BP SYS ARR" + bp_sys_Arr.toString());
                        Log.d(TAG ,"BP DIA ARR" + bp_dia_Arr.toString());


                        for(int i=0; i<bp_sys_Arr.length(); i++) {

                            bp_sys_buffer[i] = Double.parseDouble(bp_sys_Arr.getString(i));
                            bp_dia_buffer[i] = Double.parseDouble(bp_dia_Arr.getString(i));
                            Log.d(TAG, "EVERY SYS BP SHOW HERE : " + Double.toString(bp_sys_buffer[i]));
                            Log.d(TAG ,"EVERY DIA BP SHOW HERE : " + Double.toString(bp_dia_buffer[i]));
                        }

                    }else{
                        // user with id not found
                        Log.d(TAG, "QUERY BP NOT FOUND");
                    }
                }else if(describe.equals("bs")) {
                    JSONObject json = jsonParser.makeHttpRequest(url_bs_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN JSON : " + json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray userArr = json.getJSONArray("bloodsugar"); // JSON Array
                        Log.d(TAG, "The userArr is" + userArr.toString());

                        //each bmi is here
                        for(int i=0; i<userArr.length(); i++) {

                            bs_buffer[i] = Integer.parseInt(userArr.getString(i));
                            Log.d(TAG, "EVERY BS SHOW HERE : " + Integer.toString(bs_buffer[i]));
                        }

                    }else{
                        // user with id not found
                        Log.d(TAG, "QUERY BS NOT FOUND");
                    }
                }else if(describe.equals("pulse")) {
                    JSONObject json = jsonParser.makeHttpRequest(url_pulse_detials, "GET", params);
                    // check your log for json response
                    Log.d(TAG, "RETURN JSON : " + json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray userArr = json.getJSONArray("pulse"); // JSON Array
                        Log.d(TAG, "The userArr is" + userArr.toString());

                        //each bmi is here
                        for(int i=0; i<userArr.length(); i++) {

                            pulse_buffer[i] = Integer.parseInt(userArr.getString(i));
                            Log.d(TAG, "EVERY PULSE SHOW HERE : " + Integer.toString(pulse_buffer[i]));
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

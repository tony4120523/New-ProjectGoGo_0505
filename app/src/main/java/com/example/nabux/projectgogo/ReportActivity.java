package com.example.nabux.projectgogo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class ReportActivity extends AppCompatActivity {
    //WebView webreport;
    TextView avgbmishow,avgstepshow,avgdiabpshow,avgsysbpshow;
    Handler myHandler;
    int[] step_buffer;
    double[] bmi_buffer;
    double[] bp_sys_buffer;
    double[] bp_dia_buffer;
    private Session session;
    double sumbmi,avgbmi,sumsysbp,sumdiabp,avgsysbp,avgdiabp=0.0;
    int sumstep,avgstep=0;

    JSONParser jsonParser = new JSONParser();
    private static final String url_step_detials = "http://www.hth96.me/nabu_connect/query_steps_weekly.php";
    private static final String url_bmi_detials = "http://www.hth96.me/nabu_connect/query_bmi.php";
    private static final String url_bp_detials = "http://www.hth96.me/nabu_connect/query_bp_weekly.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        avgbmishow= (TextView) findViewById(R.id.avgbmishow);
        avgstepshow= (TextView) findViewById(R.id.avgstepshow);
        avgdiabpshow= (TextView) findViewById(R.id.avgdiabpshow);
        avgsysbpshow= (TextView) findViewById(R.id.avgsysbpshow);
        session = new Session(getApplicationContext());

        /*webreport= (WebView) findViewById(R.id.webreport);
        webreport.loadUrl("http://45.55.213.89/nabu_connect/score.html?score=87");
        webreport.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webreport.getSettings();
        webSettings.setJavaScriptEnabled(true);*/


        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Log.d("0123165465", bmi_buffer.toString());
                        Log.d("bmi avg here",Double.toString(avgbmi));
                        avgbmishow.setText("平均bmi為："+avgbmi);
                        Log.d("step avg here",Integer.toString(avgstep));
                        avgstepshow.setText("平均步數為："+avgstep);
                        Log.d("diabp avg here",Double.toString(avgdiabp));
                        avgdiabpshow.setText("平均心肌舒張壓為："+avgdiabp);
                        Log.d("sysbp avg here",Double.toString(avgsysbp));
                        avgsysbpshow.setText("平均心肌收縮壓為："+avgdiabp);
                    default:
                        break;
                }
            }
        };
        Log.d("ggggggggggggggggggggggg","ggu");
        new Getdata("bmi").execute();
        new Getdata("step").execute();
        new Getdata("bp").execute();

    }
    class Getdata extends AsyncTask<String, String, String> {
        String describe;
        Getdata(String desc) {
            describe = desc;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog();//???!!!!!!!!!
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }


        protected String doInBackground(String... args) {

            String id = session.getUserID();
            Log.d("SESSION ID", id);


            try {

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));

                Log.d("DESCRIBE", describe);

                if(describe.equals("step")) {
                    JSONObject json = jsonParser.makeHttpRequest(url_step_detials, "GET", params);
                    // check your log for json response
                    Log.d("RETURN JSON", json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray userArr = json.getJSONArray("steps"); // JSON Array
                        Log.d("The userArr is", userArr.toString());

                        //each step is here
                        step_buffer = new int[userArr.length()];
                        for(int i=0; i<userArr.length(); i++) {
                            step_buffer[i] = Integer.parseInt(userArr.getString(i));
                            sumstep+=step_buffer[i];


                            Log.d("EVERY STEP SHOW HERE", Integer.toString(step_buffer[i]));


                        }
                        avgstep=sumstep/userArr.length();
                    }else{
                        // user with id not found
                        Log.d("QUERY STEP NOT FOUND", "user data not in database");
                    }
                }else if(describe.equals("bmi")) {
                    JSONObject json = jsonParser.makeHttpRequest(url_bmi_detials, "GET", params);
                    // check your log for json response
                    Log.d("RETURN JSON", json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray userArr = json.getJSONArray("bmi"); // JSON Array
                        Log.d("The userArr is", userArr.toString());

                        //each bmi is here
                        bmi_buffer = new double[userArr.length()];
                        for(int i=0; i<userArr.length(); i++) {

                            bmi_buffer[i] = Double.parseDouble(userArr.getString(i));
                            sumbmi+=bmi_buffer[i];
                            Log.d("EVERY BMI SHOW HERE", Double.toString(bmi_buffer[i]));

                        }

                        avgbmi=sumbmi/userArr.length();
                        avgbmi=((Math.round(avgbmi*100.0))/100.0);

                    }else{
                        // user with id not found
                        Log.d("QUERY BMI NOT FOUND", "user data not in database");
                    }
                }else if(describe.equals("bp")) {
                    JSONObject json = jsonParser.makeHttpRequest(url_bp_detials, "GET", params);
                    // check your log for json response
                    Log.d("RETURN JSON", json.toString());
                    // json success tag
                    int success;
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received json
                        JSONArray bp_sys_Arr = json.getJSONArray("bp_sys"); // JSON Array
                        JSONArray bp_dia_Arr = json.getJSONArray("bp_dia");
                        Log.d("BP SYS ARR", bp_sys_Arr.toString());
                        Log.d("BP DIA ARR", bp_dia_Arr.toString());

                        bp_sys_buffer = new double[bp_sys_Arr.length()];
                        bp_dia_buffer = new double[bp_dia_Arr.length()];
                        for(int i=0; i<bp_sys_Arr.length(); i++) {

                            bp_sys_buffer[i] = Double.parseDouble(bp_sys_Arr.getString(i));
                            bp_dia_buffer[i] = Double.parseDouble(bp_dia_Arr.getString(i));
                            sumsysbp+=bp_sys_buffer[i];
                            sumdiabp+=bp_dia_buffer[i];
                            Log.d("EVERY SYS BP SHOW HERE", Double.toString(bp_sys_buffer[i]));
                            Log.d("EVERY DIA BP SHOW HERE", Double.toString(bp_dia_buffer[i]));
                        }
                            avgdiabp=sumdiabp/bp_dia_Arr.length();
                            avgdiabp=((Math.round(avgdiabp*100.0))/100.0);
                            avgsysbp=sumsysbp/bp_sys_Arr.length();
                            avgsysbp=((Math.round(avgsysbp*100.0))/100.0);

                    }else{
                        // user with id not found
                        Log.d("QUERY BP NOT FOUND", "user data not in database");
                    }
                }else {
                    //wait to add...
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            //pDialog.dismiss();
            myHandler.sendEmptyMessage(0);

        }
    }
}

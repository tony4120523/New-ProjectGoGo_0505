package com.example.nabux.projectgogo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InputDataActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private Session session;
    JSONParser jsonParser;
    private static final String url_insertdata = "http://www.hth96.me/nabu_connect/insert_data.php";
    private static final String url_insertdata_first_day = "http://www.hth96.me/nabu_connect/insert_data_first_day.php";
    private static final String TAG_SUCCESS = "success";

    ImageView ima_step, ima_sys, ima_dia, ima_bs, ima_pulse;
    EditText edit_step, edit_bp_sys, edit_bp_dia, edit_pulse, edit_bs, edit_height, edit_weight;
    Button btn_save;
    Handler handler;
    String input_step, input_sys, input_dia, input_pulse, input_bs, input_height, input_weight;
    LinearLayout linear_exp, linear_height, linear_weight;
    int day;
    double bmi;
    private static final String TAG = InputDataActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        jsonParser = new JSONParser();
        session = new Session(getApplicationContext());
        ima_step = (ImageView) findViewById(R.id.ima_step);
        ima_sys = (ImageView) findViewById(R.id.ima_sys);
        ima_dia = (ImageView) findViewById(R.id.ima_dia);
        ima_pulse = (ImageView) findViewById(R.id.ima_pulse);
        ima_bs = (ImageView) findViewById(R.id.ima_bs);
        edit_step = (EditText) findViewById(R.id.edit_step);
        edit_bp_sys = (EditText) findViewById(R.id.edit_sys);
        edit_bp_dia = (EditText) findViewById(R.id.edit_dia);
        edit_pulse = (EditText) findViewById(R.id.edit_pulse);
        edit_bs = (EditText) findViewById(R.id.edit_bs);
        edit_height = (EditText) findViewById(R.id.edit_height);
        edit_weight = (EditText) findViewById(R.id.edit_weight);
        linear_exp = (LinearLayout) findViewById(R.id.linear_exp);
        linear_height = (LinearLayout) findViewById(R.id.linear_height);
        linear_weight = (LinearLayout) findViewById(R.id.linear_weight);
        btn_save = (Button) findViewById(R.id.btn_save);

        ima_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivityForResult(intent, 11);
            }
        });

        ima_sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivityForResult(intent, 22);
            }
        });

        ima_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivityForResult(intent, 33);
            }
        });

        ima_pulse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivityForResult(intent, 44);
            }
        });

        ima_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivityForResult(intent, 55);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_step = edit_step.getText().toString();
                input_sys = edit_bp_sys.getText().toString();
                input_dia = edit_bp_dia.getText().toString();
                input_pulse = edit_pulse.getText().toString();
                input_bs = edit_bs.getText().toString();
                input_height = edit_height.getText().toString();
                input_weight = edit_weight.getText().toString();

                handler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Toast.makeText(getApplication(), "Saved successfully.", Toast.LENGTH_SHORT).show();
                                finish();
                                break;
                            default:
                                break;
                        }
                    }
                };
                new InsertData().execute();
            }
        });

        Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        if(day != 1) {
            linear_exp.setVisibility(View.GONE);
            linear_height.setVisibility(View.GONE);
            linear_weight.setVisibility(View.GONE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == RESULT_OK) {
            String result = intent.getStringExtra("result");
            switch(requestCode) {
                case 11 :
                    edit_step.setText(result);
                    break;
                case 22 :
                    edit_bp_sys.setText(result);
                    break;
                case 33 :
                    edit_bp_dia.setText(result);
                    break;
                case 44 :
                    edit_pulse.setText(result);
                    break;
                case 55 :
                    edit_bs.setText(result);
                    break;
            }
        }
    }

    class InsertData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(InputDataActivity.this);
            pDialog.setMessage("Saving ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            JSONObject json;
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", session.getUserID()));
            params.add(new BasicNameValuePair("steps", input_step));
            params.add(new BasicNameValuePair("bp_sys", input_sys));
            params.add(new BasicNameValuePair("bp_dia", input_dia));
            params.add(new BasicNameValuePair("pulse", input_pulse));
            params.add(new BasicNameValuePair("bs", input_bs));
            if(day == 1) {
                params.add(new BasicNameValuePair("height", input_height));
                params.add(new BasicNameValuePair("weight", input_weight));
                String str_bmi = String.valueOf(Double.parseDouble(input_weight)/Double.parseDouble(input_height)/Double.parseDouble(input_height)*10000);
                params.add(new BasicNameValuePair("bmi", str_bmi));
                json = jsonParser.makeHttpRequest(url_insertdata_first_day, "POST", params);
            }
            else {
                json = jsonParser.makeHttpRequest(url_insertdata, "POST", params);
            }

            Log.d(TAG + " json", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    Log.d(TAG, "Absolutely success");

                } else {
                    // failed to create user
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            handler.sendEmptyMessage(0);
        }

    }
}

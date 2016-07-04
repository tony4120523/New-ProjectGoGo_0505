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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InputDataActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private Session session;
    JSONParser jsonParser;
    private static final String url_insertdata = "http://www.hth96.me/nabu_connect/insert_data.php";
    private static final String TAG_SUCCESS = "success";

    ImageView ima_step, ima_sys, ima_dia, ima_bs;
    EditText edit_step, edit_bp_sys, edit_bp_dia, edit_pulse, edit_bs;
    Button btn_save;
    Handler handler;
    String input_step, input_sys, input_dia, input_pulse, input_bs;
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
        ima_bs = (ImageView) findViewById(R.id.ima_bs);
        edit_step = (EditText) findViewById(R.id.edit_step);
        edit_bp_sys = (EditText) findViewById(R.id.edit_sys);
        edit_bp_dia = (EditText) findViewById(R.id.edit_dia);
        edit_pulse = (EditText) findViewById(R.id.edit_pulse);
        edit_bs = (EditText) findViewById(R.id.edit_bs);
        btn_save = (Button) findViewById(R.id.btn_save);

        ima_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivity(intent);
            }
        });

        ima_sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivity(intent);
            }
        });

        ima_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivity(intent);
            }
        });

        ima_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivity(intent);
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

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", session.getUserID()));
            params.add(new BasicNameValuePair("steps", input_step));
            params.add(new BasicNameValuePair("bp_sys", input_sys));
            params.add(new BasicNameValuePair("bp_dia", input_dia));
            params.add(new BasicNameValuePair("pulse", input_pulse));
            params.add(new BasicNameValuePair("bs", input_bs));

            JSONObject json = jsonParser.makeHttpRequest(url_insertdata, "POST", params);


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

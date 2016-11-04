package com.example.nabux.projectgogo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;


public class RegistActivity extends AppCompatActivity {


    Handler myHandler;
    Session session;

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    // url to create new product
    private static final String url_create_user = "http://45.55.213.89/nabu_connect/create_user_token.php";
    private static final String TAG = RegistActivity.class.getSimpleName();

    TextView tvrgid,tvrgpsd,tvrgpsd2,tvrgnickname;
    Button btnrgsubmit,btnrgreset;
    EditText edrgid,edrgpsd,edrgpsd2,edrgnickname, edit_cp_email;
    CheckBox chk_hyper, chk_diabetes, chk_heart;

    String id, psd, nickname, cp_email, hypertension, diabetes, heart_disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        session = new Session(getApplicationContext());

        tvrgid= (TextView) findViewById(R.id.tvrgid);
        tvrgpsd= (TextView) findViewById(R.id.tvrgpsd);
        tvrgpsd2= (TextView) findViewById(R.id.tvrgpsd2);
        tvrgnickname= (TextView) findViewById(R.id.tvrgnickname);
        btnrgsubmit=(Button)findViewById(R.id.btnrgsubmit);
        btnrgreset= (Button) findViewById(R.id.btnrgreset);
        edrgid= (EditText) findViewById(R.id.edrgid);
        edrgpsd= (EditText) findViewById(R.id.edrgpsd);
        edrgpsd2= (EditText) findViewById(R.id.edrgpsd2);
        edrgnickname= (EditText) findViewById(R.id.edrgnickname);
        edit_cp_email = (EditText) findViewById(R.id.edit_cp_email);
        chk_hyper = (CheckBox) findViewById(R.id.chk_hyper);
        chk_diabetes = (CheckBox) findViewById(R.id.chk_diabetes);
        chk_heart = (CheckBox) findViewById(R.id.chk_heart);

        btnrgreset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // reset editview
                edrgid.setText("");
                edrgpsd.setText("");
                edrgpsd2.setText("");
                edrgnickname.setText("");
                edit_cp_email.setText("");
            }
        });


        //submit button clicked event
        btnrgsubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                id = edrgid.getText().toString();
                psd = edrgpsd.getText().toString();
                nickname = edrgnickname.getText().toString();
                cp_email = edit_cp_email.getText().toString();
                hypertension = chk_hyper.isChecked() ? "1" : "0";
                diabetes = chk_diabetes.isChecked() ? "1" : "0";
                heart_disease = chk_heart.isChecked() ? "1" : "0";

                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                // calling to this function from other pleaces
                                Toast.makeText(getApplication(), "Regist Done!", Toast.LENGTH_SHORT).show();
                                finish();
                                break;
                            default:
                                break;
                        }
                    }
                };



                // create new data to database in background thread
                new CreateNewUser().execute();
            }
        });


    }



    /**
     * Background Async Task to Create new User
     * */
    class CreateNewUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegistActivity.this);
            pDialog.setMessage("Registing ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating User
         * */
        protected String doInBackground(String... args) {

            Log.d(TAG, "DoInBackground ID : " + id);
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("psd", psd));
            params.add(new BasicNameValuePair("nickname", nickname));
            params.add(new BasicNameValuePair("cp_email", cp_email));
            params.add(new BasicNameValuePair("isHyper", hypertension));
            params.add(new BasicNameValuePair("isDiabetes", diabetes));
            params.add(new BasicNameValuePair("isHeart", heart_disease));
            params.add(new BasicNameValuePair("token", FirebaseInstanceId.getInstance().getToken()));

            // getting JSON Object
            // Note that create_user url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_user,
                    "POST", params);

            // check log cat for response
            Log.d(TAG, "JSON : " + json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product

                    // closing this screen
                    finish();
                } else {
                    // failed to create user
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            myHandler.sendEmptyMessage(0);
        }

    }

}


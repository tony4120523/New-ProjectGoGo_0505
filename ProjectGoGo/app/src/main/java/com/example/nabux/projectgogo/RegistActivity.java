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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegistActivity extends AppCompatActivity {


    Handler myHandler;

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    // url to create new product
    private static String url_create_user = "http://45.55.213.89/nabu_connect/create_user.php";

    TextView tvrgid,tvrgpsd,tvrgpsd2,tvrgnickname;
    Button btnrgsubmit,btnrgreset;
    EditText edrgid,edrgpsd,edrgpsd2,edrgnickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

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

        btnrgreset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // reset editview
                edrgid.setText("");
                edrgpsd.setText("");
                edrgpsd2.setText("");
                edrgnickname.setText("");
            }
        });


        //submit button clicked event
        btnrgsubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


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
            String id = edrgid.getText().toString();
            String psd = edrgpsd.getText().toString();
            String nickname = edrgnickname.getText().toString();
            Log.d("Test Error", id);
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("psd", psd));
            params.add(new BasicNameValuePair("nickname", nickname));

            // getting JSON Object
            // Note that create_user url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_user,
                    "POST", params);

            // check log cat for response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product

                    /*******
                     * Note!!! Think about that after create user what is the next step.....
                     * Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                     * startActivity(i);
                     ********/

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


package com.example.nabux.projectgogo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lala on 2016/4/29.
 */
public class GetStepClass {
    JSONParser jsonParser;
    Handler myHandler;
    String url_step_detials = "http://www.hth96.me/nabu_connect/query_steps.php";

    public void getStep() {
        jsonParser = new JSONParser();
        myHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        // calling to this function from onPostExecute if it return 0



                        break;
                    default:
                        break;
                }
            }
        };

        // Getting user steps in background thread
        new GetUserSteps().execute();
    }



    /**
     * Background Async Task to Get User StepActivity details
     * */
    class GetUserSteps extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog();//???!!!!!!!!!
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        /**
         * Getting User StepActivity details in background thread
         * */
        protected String doInBackground(String... args) {


            String id = "1"; // Just a test

            Log.d("Test", "!!!!!!!!!!!!");
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));
                Log.d("Obov", "!!!!!!!!!!!!");
                // getting user StepActivity details by making HTTP request
                // Note that user details url will use GET request
                JSONObject json = jsonParser.makeHttpRequest(
                        url_step_detials, "GET", params);

                // check your log for json response
                Log.d("User Step Details", json.toString());

                // json success tag
                int success;
                success = json.getInt("success");
                if (success == 1) {
                    // successfully received user StepActivity details
                    JSONArray userObj = json.getJSONArray("steps"); // JSON Array

                    // get first user StepActivity object from JSON Array
                    JSONObject userstep = userObj.getJSONObject(0);


                    // user detail





                }else{
                    // user with id not found
                    Log.d("Account StepActivity Not found", "user StepActivity id not in database");
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
            // dismiss the dialog once got all details
            //pDialog.dismiss();
            myHandler.sendEmptyMessage(0);
        }
    }

}

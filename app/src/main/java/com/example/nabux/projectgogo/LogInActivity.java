package com.example.nabux.projectgogo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class LogInActivity extends AppCompatActivity {


    private Session session;
    Handler myHandler;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    // single user url
    private static final String url_user_detials = "http://45.55.213.89/nabu_connect/login_token.php";
    private static final String TAG = LogInActivity.class.getSimpleName();

    EditText edtAccount, edtpsd;
    Button btnLog, btnRes;
    String usernickname, userAccount, userpsd, userID, user_cp_email, user_hypertension, user_diabetes, user_disease;
    String global_Account;

    // JSON Node names(depend on php)
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USER = "user";
    private static final String TAG_ACCOUNT = "account";
    private static final String TAG_PSD = "psd";
    private static final String TAG_ID = "id";
    private static final String TAG_NICKNAME = "nickname";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        session = new Session(getApplicationContext());

        userAccount = null;
        userpsd = null;
        usernickname = null;
        btnLog = (Button) findViewById(R.id.btnlogin);
        btnRes = (Button) findViewById(R.id.btnregist);
        edtAccount = (EditText) findViewById(R.id.ed1);
        edtpsd = (EditText) findViewById(R.id.ed2);

        Intent intent = getIntent();
        if(intent.hasExtra("userAccount")) {
            final String uAccount = intent.getStringExtra("userAccount");
            final String uPSD = intent.getStringExtra("userPSD");
            global_Account = uAccount;
            Log.d(TAG, "AutoLogIn_uAccount : " + uAccount);
            myHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case 0:
                            // calling to this function from onPostExecute
                            if (uAccount.equals(userAccount) && uPSD.equals(userpsd)) {
                                Intent homein = new Intent(getApplicationContext(), HomeActivity.class);
                                homein.putExtra(TAG_NICKNAME, usernickname);
                                homein.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                homein.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                homein.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(homein);
                                finish();
                            }else{
                                Toast.makeText(getApplication(), "Log in failed.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        default:
                            break;
                    }
                }
            };
            // Getting user details in background thread
            new CheckUserDetails().execute();
        }


        // login button click event
        btnLog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final String Account = edtAccount.getText().toString();
                final String psd = edtpsd.getText().toString();
                global_Account = edtAccount.getText().toString();


                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                // calling to this function from onPostExecute
                                if (Account.equals(userAccount) && psd.equals(userpsd)) {

                                    session.setUserID(userID);
                                    session.setUserAccount(userAccount);
                                    session.setUserPSD(userpsd);
                                    session.setNickName(usernickname);
                                    session.setUserCPemail(user_cp_email);
                                    session.setHypertension(user_hypertension);
                                    session.setDiabetes(user_diabetes);
                                    session.setDisease(user_disease);

                                    // 設定定期通知通知
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.set(Calendar.HOUR_OF_DAY, 19);
                                    calendar.set(Calendar.MINUTE, 0);
                                    calendar.set(Calendar.SECOND, 0);

                                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                                    intent.putExtra("purpose", "sendNotification");
                                    PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                                    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pi);


                                    // 判斷異常 上週
                                    Calendar calendar2 = Calendar.getInstance();
                                    calendar2.set(Calendar.HOUR_OF_DAY, 19);
                                    calendar2.set(Calendar.MINUTE, 0);
                                    calendar2.set(Calendar.SECOND, 0);
                                    Intent intent2 = new Intent(getApplicationContext(), AlarmReceiver.class);
                                    intent2.putExtra("purpose", "judge7");
                                    PendingIntent pi2 = PendingIntent.getBroadcast(getApplicationContext(), 101, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
                                    AlarmManager am2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    am2.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24*7 , pi2);

                                    // 判斷異常 三個月
                                    Calendar calendar3 = Calendar.getInstance();
                                    calendar3.set(Calendar.HOUR_OF_DAY, 19);
                                    calendar3.set(Calendar.MINUTE, 0);
                                    calendar3.set(Calendar.SECOND, 0);
                                    Intent intent3 = new Intent(getApplicationContext(), AlarmReceiver.class);
                                    intent3.putExtra("purpose", "judge3m");
                                    PendingIntent pi3 = PendingIntent.getBroadcast(getApplicationContext(), 102, intent3, PendingIntent.FLAG_CANCEL_CURRENT);
                                    AlarmManager am3 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    am3.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 *24*90 , pi3);

                                    // 開啟主畫面活動
                                    Intent homein = new Intent(getApplicationContext(), HomeActivity.class);
                                    homein.putExtra(TAG_NICKNAME, usernickname);
                                    homein.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    homein.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    homein.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(homein);
                                    finish();
                                }else{
                                    Toast.makeText(getApplication(), " 登入失敗 ", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                };


                // Getting user details in background thread
                new CheckUserDetails().execute();

            }
        });



        // resgist button click event
        btnRes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(in);
                finish();
            }
        });



    }


    /**
     * Background Async Task to Get User details
     * */
    class CheckUserDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LogInActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting User details in background thread
         * */
        protected String doInBackground(String... args) {

            // updating UI from Background Thread
            //runOnUiThread(new Runnable() {
                //public void run() {
                    String Account = global_Account;

                    Log.d(TAG, "In doInBackground "+Account);
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("account", Account));
                        params.add(new BasicNameValuePair("token", FirebaseInstanceId.getInstance().getToken()));

                        JSONObject json = jsonParser.makeHttpRequest(
                                url_user_detials, "POST", params);

                        // check your log for json response
                        Log.d(TAG, "JSON : " + json.toString());

                        // json success tag
                        int success;
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received user details
                            JSONArray userObj = json
                                    .getJSONArray(TAG_USER); // JSON Array

                            // get first user object from JSON Array
                            JSONObject user = userObj.getJSONObject(0);


                            // user detail
                            userAccount = user.getString(TAG_ACCOUNT);
                            userpsd = user.getString(TAG_PSD);
                            usernickname = user.getString(TAG_NICKNAME);
                            userID = user.getString(TAG_ID);
                            user_cp_email = user.getString("cp_email");
                            user_hypertension = user.getString("hypertension");
                            user_diabetes = user.getString("diabetes");
                            user_disease = user.getString("disease");
                        }else{
                            // user with Account not found
                            Log.d(TAG, "Success is 0");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                //}
            //});

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
            myHandler.sendEmptyMessage(0);
        }
    }



}

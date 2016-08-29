package com.example.nabux.projectgogo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lala on 2016/7/25.
 */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String url_check = "http://www.hth96.me/nabu_connect/isDataInserted.php";
    private static final String TAG = AlarmReceiver.class.getSimpleName();
    private Session session;
    private boolean isDataInserted;
    Handler myHandler;
    JSONParser jsonParser;
    Notification notification;


    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show();

        final int notifyID = 100;
        session = new Session(context);

        //get 系統通知服務
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            Intent resultIntent = new Intent(context, InputDataActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            // Adds the back stack
            stackBuilder.addParentStack(InputDataActivity.class);
            // Adds the Intent to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            notification = new NotificationCompat.Builder(context)
                    .setColor(Color.rgb(0, 204, 102))
                    //.setVibrate(new long[]{0, 300, 200, 100, 100, 100, 100, 100})
                    .setSmallIcon(R.drawable.house)
                    .setContentTitle("Notification")
                    .setContentText("~ Haven't Input Data ~")
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .build();

            IsNetworkConnected isnetworkconnected = new IsNetworkConnected(context);

            jsonParser = new JSONParser();
            isDataInserted = false;

            if(isnetworkconnected.isOnline()) {

                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                if(!isDataInserted){
                                    notificationManager.notify(notifyID, notification);
                                    Log.d(TAG, "Send Notification");
                                }
                        }
                    }
                };
                new Getdata().execute();
            }
        }

        //Log.d(TAG, "Notification");
    }

    class Getdata extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(ReportActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }


        protected String doInBackground(String... args) {

            try {

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", session.getUserID()));




                JSONObject json = jsonParser.makeHttpRequest(url_check, "GET", params);
                // check your log for json response
                Log.d(TAG, json.toString());

                // json success tag
                int success;
                success = json.getInt("success");
                if (success == 1) {
                    // successfully received json
                    isDataInserted = true;
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
package com.example.nabux.projectgogo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ReceiveFCMService extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        //Log.d(TAG, "From: " + remoteMessage.getFrom());
        //Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        //Log.d(TAG, FirebaseInstanceId.getInstance().getToken());
        /**if i only use getData(), the app can in background or closed will call this method
         * in foreground , 3 case are all worked.
         * if i use getNotification(), only foreground will call this method
         * background and closed will use notification center to display notification
          */
        Log.d(TAG, remoteMessage.getData().get("message"));
        if(remoteMessage.getData().containsKey("link")){
            Log.d(TAG, remoteMessage.getData().get("link"));
        }
        Intent i = new Intent();
        i.setClass(this, ReminderActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("message", remoteMessage.getData().get("message"));
        if(remoteMessage.getData().containsKey("link")){
            i.putExtra("link", remoteMessage.getData().get("link"));
        }

        startActivity(i);
    }

}

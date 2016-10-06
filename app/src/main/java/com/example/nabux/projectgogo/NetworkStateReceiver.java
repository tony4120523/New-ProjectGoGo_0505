package com.example.nabux.projectgogo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by lala on 2016/10/6.
 */

public class NetworkStateReceiver extends BroadcastReceiver {

    public boolean isNetworkOK;
    //private boolean firstDisConnect = true;

    @Override
    public void onReceive(final Context context, Intent intent) {

        IsNetworkConnected inc = new IsNetworkConnected(context);
        if(!inc.isOnline()) {
            isNetworkOK = false;

            //create a new intent to the MainScreenActivity
            Intent i = new Intent();
            i.setClassName("com.example.nabux.projectgogo", "com.example.nabux.projectgogo.MainScreenActivity");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            context.startActivity(i);


            //close current Activity, go to MainScreenActivity
            //finish();
            //Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            //startActivity(intent);

        }

        //if (!inc.isOnline() && firstDisConnect) {
        //    firstDisConnect = false;
        //}


    }
}

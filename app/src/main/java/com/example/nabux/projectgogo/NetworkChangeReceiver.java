package com.example.nabux.projectgogo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lala on 2016/6/29.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private static final String TAG = NetworkChangeReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        IsNetworkConnected inc = new IsNetworkConnected(context);
        if (inc.isOnline()) {
            // Do something
            /*context.startActivity(new Intent(, MainScreenActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));*/
            Log.d(TAG, "Network Available");
        }
        else {
            //context.startActivity(new Intent(context, MainScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            Log.d(TAG, "Network Unavailable");
        }
    }

}

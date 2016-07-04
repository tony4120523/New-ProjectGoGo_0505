package com.example.nabux.projectgogo;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainScreenActivity extends AppCompatActivity {

    private Session session;
    private static final String TAG = MainScreenActivity.class.getSimpleName();

    Button btnLogin;
    Button btnRegist;
    ImageView network_image;
    private MyReceiver receiver;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        receiver = new MyReceiver(new Handler());
        registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        network_image = (ImageView) findViewById(R.id.network);
        status = "";

        try {
            IsNetworkConnected inc = new IsNetworkConnected(getApplicationContext());
            if(!inc.isOnline()) {
                network_image.setVisibility(View.VISIBLE);
                throw new Exception();

            } else {
                session = new Session(getApplicationContext());
                if( !(session.getUserAccount().equals("") && session.getUserPSD().equals("")) ) {
                    Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                    intent.putExtra("userAccount", session.getUserAccount());
                    intent.putExtra("userPSD", session.getUserPSD());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }



                // Buttons
                btnLogin = (Button) findViewById(R.id.btn_login);
                btnRegist = (Button) findViewById(R.id.btn_regist);

                // Login
                btnLogin.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // Launching Login Activity
                        Intent i = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(i);

                    }
                });

                // Regist
                btnRegist.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // Launching Regist Activity
                        Intent i = new Intent(getApplicationContext(), RegistActivity.class);
                        startActivity(i);

                    }
                });

            }
        } catch(Exception e){
            Log.d(TAG, "Not Connected");
            status = "E";
            //Log.d(TAG, status);
        }
    }

    public class MyReceiver extends BroadcastReceiver {

        private final Handler handler; // Handler used to execute code on the UI thread
        private boolean firstDisConnect = true;

        public MyReceiver(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void onReceive(final Context context, Intent intent) {
            // Post the UI updating code to our Handler
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(context, "Toast from broadcast receiver", Toast.LENGTH_SHORT).show();
                    IsNetworkConnected inc = new IsNetworkConnected(getApplicationContext());
                    if(inc.isOnline()) {
                        network_image.setVisibility(View.GONE);
                        if(status.equals("E"))  {
                            finish();
                            startActivity(getIntent());
                        }
                    }
                    else {
                        if(firstDisConnect) {
                            network_image.setVisibility(View.VISIBLE);
                            new AlertDialog.Builder(MainScreenActivity.this).setMessage("沒有網路連接").setPositiveButton("好", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                            firstDisConnect = false;
                        }else{
                            firstDisConnect = true;
                        }
                    }
                }
            });
        }
    }
}

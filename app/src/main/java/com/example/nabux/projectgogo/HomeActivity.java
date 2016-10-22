package com.example.nabux.projectgogo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {



    public NetworkStateReceiver networkStateReceiver;
    private Session session;

    private static final int requestCode = 100;
    private static final String TAG = HomeActivity.class.getSimpleName();


    TextView txvhi;
    Button btnreport,btnhelp, btn_ocr, btn_input,btnreportpage,btnchartselect,btnhealth,btnmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        networkStateReceiver = new NetworkStateReceiver();

        session = new Session(getApplicationContext());

        txvhi = (TextView) findViewById(R.id.txv_hi);
        btnchartselect= (Button) findViewById(R.id.btnchartselect);
        btnreport= (Button) findViewById(R.id.btnreport);
        btnhelp= (Button) findViewById(R.id.btnhelp);
        btn_input = (Button) findViewById(R.id.btn_input);
        //btnreportpage= (Button) findViewById(R.id.btnreportpage);
        btnhealth= (Button) findViewById(R.id.btnhealth);
        //btnmail= (Button) findViewById(R.id.btnmail);
        Intent in = getIntent();
        String nickname = session.getNickName();
        Log.d(TAG, "Nickname : " + nickname);


        txvhi.setText(nickname + " 您好!");

        btnhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),health.class);
                startActivity(in);
            }
        });
        btnchartselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent in =new Intent(getApplicationContext(),SelectChartActivity.class);
                startActivity(in);

            }
        });





        /*btnreport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), xxxxxxxxxReportActivity.class);
                startActivity(in);

            }
        });*/

        btnhelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), KnowledgeActivity.class);
                startActivity(in);

            }
        });


        btn_input.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), InputDataActivity.class);
                startActivity(in);
            }
        });

       /*btnreportpage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), xxxxxtabbed.class);
                startActivity(in);
            }
        });*/

       /* btnmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),TestMailActivity.class);
                startActivity(in);
            }
        });*/


        /*
        //通知
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, requistCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 , pi);
        */

    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    public void onPause() {
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement, Log out...
        if (id == R.id.action_logout) {

            session.setUserAccount("");
            session.setUserPSD("");
            session.setUserID("");

            //Reset AlarmManager
            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.cancel(pi);

            // Launching MainScreen Activity
            Intent in = new Intent(getApplicationContext(), MainScreenActivity.class);
            startActivity(in);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }



    /*
    public class CheckNetworkReceiver extends BroadcastReceiver {

        private final Handler handler; // Handler used to execute code on the UI thread
        private boolean firstDisConnect = true;

        public CheckNetworkReceiver(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void onReceive(final Context context, Intent intent) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    IsNetworkConnected inc = new IsNetworkConnected(getApplicationContext());
                    if (!inc.isOnline() && firstDisConnect) {
                        firstDisConnect = false;
                        //close current Activity, go to MainScreenActivity
                        finish();
                        Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                }
            });
        }
    }
    */

}

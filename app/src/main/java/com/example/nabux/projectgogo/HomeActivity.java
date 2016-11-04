package com.example.nabux.projectgogo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nabux.projectgogo.HealthKnowledge.KnowledgeActivity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static final String url_test_detials = "http://data.taipei/opendata/datalist/apiAccess";
    String[]test_name;
    String[]test_address;
    String[]test_phone;
    Handler myHandler;
    private ProgressDialog pDialog;
    GSONParser gsonParser = new GSONParser();

    public NetworkStateReceiver networkStateReceiver;
    private Session session;
    private String tmp_account_for_thread;

    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final String url_delete_token = "http://45.55.213.89/nabu_connect/delete_token.php";

    TextView txvhi;
    Button btnreport,btnhelp, btn_ocr, btn_input,btnreportpage,btnchartselect,btnhealth,btnmail;
    ImageButton imgbtn_input, imgbtn_chart, imgbtn_report, imgbtn_knowledge, imgbtn_hospital, imgbtn_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        test_name = new String[30];
        test_address = new String[30];
        test_phone = new String[30];

        networkStateReceiver = new NetworkStateReceiver();
        session = new Session(getApplicationContext());

        /**
        *txvhi = (TextView) findViewById(R.id.txv_hi);
        *btnchartselect= (Button) findViewById(R.id.btnchartselect);
        *btnreport= (Button) findViewById(R.id.btnreport);
        *btnhelp= (Button) findViewById(R.id.btnhelp);
        *btn_input = (Button) findViewById(R.id.btn_input);
        *btnreportpage= (Button) findViewById(R.id.btnreportpage);
        *btnhealth= (Button) findViewById(R.id.btnhealth);
        *btnmail= (Button) findViewById(R.id.btnmail);
        *Intent in = getIntent();
        *String nickname = session.getNickName();
        *Log.d(TAG, "Nickname : " + nickname);

        *txvhi.setText(nickname + " 您好!");
         **/

        imgbtn_input = (ImageButton) findViewById(R.id.imageButtoninput);
        imgbtn_chart = (ImageButton) findViewById(R.id.imageButtonchart);
        imgbtn_report = (ImageButton) findViewById(R.id.imageButtonreport);
        imgbtn_knowledge = (ImageButton) findViewById(R.id.imageButtonknowledge);
        imgbtn_hospital = (ImageButton) findViewById(R.id.imageButtonhospital);
        imgbtn_member = (ImageButton) findViewById(R.id.imageButtonmember);

        imgbtn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),health.class);
                startActivity(in);
            }
        });
        imgbtn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent in =new Intent(getApplicationContext(),SelectChartActivity.class);
                startActivity(in);

            }
        });


        imgbtn_knowledge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), KnowledgeActivity.class);
                startActivity(in);

            }
        });


        imgbtn_input.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), InputDataActivity.class);
                startActivity(in);
            }
        });

        imgbtn_hospital.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Intent in = new Intent(getApplicationContext(), ShowHospitalActivity.class);
                                in.putExtra("name", test_name);
                                in.putExtra("address", test_address);
                                in.putExtra("phone",test_phone);
                                startActivity(in);
                                break;
                            default:
                                break;
                        }
                    }
                };
                new Getdata("test").execute();
            }
        });

        imgbtn_member.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), MemberActivity.class);
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

            tmp_account_for_thread = session.getUserAccount();
            new Thread(networkTask).start();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();

            //Reset AlarmManager
            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            Intent intent2 = new Intent(getApplicationContext(), AlarmReceiver.class);
            PendingIntent pi2 = PendingIntent.getBroadcast(getApplicationContext(), 101, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

            Intent intent3 = new Intent(getApplicationContext(), AlarmReceiver.class);
            PendingIntent pi3 = PendingIntent.getBroadcast(getApplicationContext(), 102, intent3, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.cancel(pi);
            am.cancel(pi2);
            am.cancel(pi3);

            // Launching MainScreen Activity
            Intent in = new Intent(getApplicationContext(), MainScreenActivity.class);

            finish();
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "in networkTask");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("account", tmp_account_for_thread));
            JSONParser jsonparser = new JSONParser();
            jsonparser.makeHttpRequest(url_delete_token, "POST", params);
        }
    };

    class Getdata extends AsyncTask<String, String, String> {
        String describe;
        Getdata(String desc) {
            describe = desc;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        protected String doInBackground(String... args) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                if(describe.equals("test")) {

                    String ppap="scope=resourceAquire&rid=ca9b88ff-a881-4ca3-a3a4-b26747f3e3e7";
                    Log.d(TAG, "★★★★★★★★★★ : " + ppap);
                    JSONObject json = gsonParser.makeHttpRequest(url_test_detials, "GET", ppap);
                    Log.d(TAG, "RETURN JSON : " + json.toString());
                    JSONObject cc = json.getJSONObject("result");
                    JSONArray aa = cc.getJSONArray("results");
                    String str="";
                    int kk=cc.getJSONArray("results").length();
                    Log.d("★☆★☆★☆★☆★☆",""+kk);
                    str += "\n--------\n";

                    for(int i=0;i<30;i++){

                        test_name[i]=aa.getJSONObject(i).getString("name");
                        test_address[i]=aa.getJSONObject(i).getString("address_for_display");
                        test_phone[i]=aa.getJSONObject(i).getString("telephone");
                        str += "name: "+aa.getJSONObject(i).getString("name")+"\n";
                        str += "address: "+aa.getJSONObject(i).getString("address_for_display")+"\n";
                        str += "phone: "+aa.getJSONObject(i).getString("telephone")+"\n";
                        str += "\n--------\n";
                    }

                    Log.d(TAG, str);
                }

                else {
                    //wait to add...
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
            myHandler.sendEmptyMessage(0);

        }
    }

}

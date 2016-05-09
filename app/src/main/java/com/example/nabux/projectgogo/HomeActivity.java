package com.example.nabux.projectgogo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //GetStepClass getstepclass = new GetStepClass();
    private Session session;
    Handler myHandler;
    JSONParser jsonParser = new JSONParser();
    private static final String url_step_detials = "http://www.hth96.me/nabu_connect/query_steps.php";
    private static final String url_bmi_detials = "";
    private static final String url_bp_detials = "";

    TextView txvhi;
    Button btnstep,btnbmi,btnhg,btnreport,btnhelp, btn_ocr;
    int[] step_buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session = new Session(getApplicationContext());

        txvhi = (TextView) findViewById(R.id.txv_hi);
        btnstep= (Button) findViewById(R.id.btnstep);
        btnbmi= (Button) findViewById(R.id.btnbmi);
        btnhg= (Button) findViewById(R.id.btnhg);
        btnreport= (Button) findViewById(R.id.btnreport);
        btnhelp= (Button) findViewById(R.id.btnhelp);
        btn_ocr = (Button) findViewById(R.id.btn_ocr);
        Intent in = getIntent();
        String nickname = in.getStringExtra("nickname");
        Log.d("....", nickname);


        txvhi.setText("Hola~" + nickname);



        btnstep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Log.d("Now step_buffer", "" + step_buffer[0]);
                                Intent in = new Intent(getApplicationContext(), StepActivity.class);
                                in.putExtra("step_buffer", step_buffer);
                                startActivity(in);
                                break;
                            default:
                                break;
                        }
                    }
                };

                // Getting data in background thread
                new Getdata().execute();


            }
        });

        btnbmi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), BMIActivity.class);
                startActivity(in);

            }
        });

        btnhg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), BPActivity.class);
                startActivity(in);

            }
        });

        btnreport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(in);

            }
        });

         btnhelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(in);

            }
        });

        btn_ocr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), com.example.nabux.projectgogo.ocr.CaptureActivity.class);
                startActivity(in);

            }
        });

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



    class Getdata extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog();//???!!!!!!!!!
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }


        protected String doInBackground(String... args) {

            String id = session.getUserID();
            Log.d("SESSION ID", id);


            try {

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));
                Log.d("ob'_'ov", "!!!!!!!!!!!!");


                JSONObject json = jsonParser.makeHttpRequest(
                        url_step_detials, "GET", params);

                // check your log for json response
                Log.d("User Step Details", json.toString());

                // json success tag
                int success;
                success = json.getInt("success");
                if (success == 1) {
                    // successfully received user step details
                    JSONArray userArr = json.getJSONArray("steps"); // JSON Array
                    Log.d("The userArr is", userArr.toString());

                    //each step is here
                    step_buffer = new int[userArr.length()];
                    for(int i=0; i<userArr.length(); i++) {
                        //Log.d("helloworld",userArr.getString(i));
                        step_buffer[i] = Integer.parseInt(userArr.getString(i));
                        Log.d("asdf", Integer.toString(step_buffer[i]));
                    }

                }else{
                    // user with id not found
                    Log.d("Account steps Not found", "user step id not in database");
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

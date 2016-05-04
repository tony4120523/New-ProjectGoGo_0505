package com.example.nabux.projectgogo;

import android.content.Intent;
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

    GetStepClass getstepclass = new GetStepClass();
    private Session session;
    TextView txvhi;
    Button btnstep,btnbmi,btnhg,btnreport,btnhelp;


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
        Intent in = getIntent();
        String nickname = in.getStringExtra("nickname");
        Log.d("....", nickname);


        txvhi.setText("Hola~" + nickname);



        btnstep.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent in = new Intent(getApplicationContext(), StepActivity.class);
                startActivity(in);

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

            session.setUserID("");
            session.setUserPSD("");


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



}

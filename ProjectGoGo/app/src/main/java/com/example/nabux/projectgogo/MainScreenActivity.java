package com.example.nabux.projectgogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainScreenActivity extends AppCompatActivity {

    private Session session;

    Button btnLogin;
    Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        session = new Session(getApplicationContext());
        Log.d("Message", session.getUserID()+"GGGG");
        if( !(session.getUserID().equals("") && session.getUserPSD().equals("")) ) {
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            intent.putExtra("userID", session.getUserID());
            intent.putExtra("userPSD", session.getUserPSD());
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
}

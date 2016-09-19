package com.example.nabux.projectgogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class mailtest extends AppCompatActivity {
    Button btnsend;
    WebView web1;

    private String url_ref="http://45.55.213.89/test/gg.php";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailtest);
        btnsend= (Button) findViewById(R.id.btnsend);
        web1= (WebView) findViewById(R.id.web1);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web1.loadUrl(url_ref);
                web1.setWebViewClient(new WebViewClient());
                WebSettings webSettings = web1.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setBuiltInZoomControls(true);
            }
        });
    }
}

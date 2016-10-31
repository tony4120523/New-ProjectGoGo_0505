package com.example.nabux.projectgogo;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long pattern[] = new long[]{1000,500,1000,500,1000,500,1000,500,1000,500,1000,
                500};
        v.vibrate(pattern, -1);

        final MediaPlayer mMediaPlayer;
        mMediaPlayer = MediaPlayer.create(this, R.raw.cuckooclocksound);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        TextView txv = (TextView) findViewById(R.id.remindertextview);
        Intent i = getIntent();
        txv.setText(i.getStringExtra("message"));

        Button ok = (Button) findViewById(R.id.reminder_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.cancel();
                mMediaPlayer.stop();
                finish();
            }
        });
    }
}

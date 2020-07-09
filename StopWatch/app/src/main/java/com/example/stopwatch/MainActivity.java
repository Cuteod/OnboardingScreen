package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private Button buttonStart;
    private Button buttonReset;
    private boolean running;
    private long pauseOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.TxtCountdown);
        buttonStart = findViewById(R.id.BtnStart);
        buttonReset = findViewById(R.id.BtnReset);

    }

    public void onReset(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        buttonReset.setVisibility(View.INVISIBLE);
        buttonStart.setVisibility(View.VISIBLE);

    }

    public void onStart(View v) {
        if (!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;

            buttonStart.setText(R.string.pause);
            buttonReset.setVisibility(View.INVISIBLE);
        }else {
            onPause(chronometer);
            buttonStart.setText(R.string.start);
            buttonStart.setVisibility(View.INVISIBLE);
            buttonReset.setVisibility(View.VISIBLE);
        }

    }

    public void onPause(View v){
        if (running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;

            buttonStart.setText(R.string.start);
            buttonReset.setVisibility(View.VISIBLE);
        }

    }
}

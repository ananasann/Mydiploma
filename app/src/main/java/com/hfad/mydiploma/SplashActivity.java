package com.hfad.mydiploma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    Intent intent;
    private final int SPLASH_DISPLAY_LENGTH = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        /*intent = new Intent(this, AuthActivity.class);
        startActivity(intent);*/
        new Handler(/*Looper.getMainLooper()*/).postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
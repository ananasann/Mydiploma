package com.hfad.mydiploma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SplashActivity extends AppCompatActivity {

    Intent intent;
    private final int SPLASH_DISPLAY_LENGTH = 300;
    GoogleSignInAccount gsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        /*intent = new Intent(this, AuthActivity.class);
        startActivity(intent);*/
        gsa = GoogleSignIn.getLastSignedInAccount(this);

        if(gsa != null){
            new Handler(/*Looper.getMainLooper()*/).postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
        }else{
            new Handler(/*Looper.getMainLooper()*/).postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
        }


    }
}
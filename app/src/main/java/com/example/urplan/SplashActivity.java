package com.example.urplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {

                    Intent i = new Intent(SplashActivity.this,TutorialActivity.class);
                    startActivity(i);

                    finish();
                }

//                else {
//                    isLogin();
//                }

        }, SPLASH_DISPLAY_TIME);
    }

    private void onFinishIntroPages(){
        SharedPreferences.Editor sharedPreferencesEditor =
                PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferencesEditor.putBoolean(
                "IntroPages", true);
        sharedPreferencesEditor.apply();
    }


    }
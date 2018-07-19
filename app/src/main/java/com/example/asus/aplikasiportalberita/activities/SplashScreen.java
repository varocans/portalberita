package com.example.asus.aplikasiportalberita.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.aplikasiportalberita.MainActivity;
import com.example.asus.aplikasiportalberita.R;

public class SplashScreen extends AppCompatActivity {

    private long ms = 0;
    private long splashtime = 3000;
    private boolean splashActive = true;
    private boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread mythread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashtime) {
                        if (!paused) {
                            ms = ms + 100;
                            sleep(100);
                        }
                    }
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        mythread.start();
    }
}
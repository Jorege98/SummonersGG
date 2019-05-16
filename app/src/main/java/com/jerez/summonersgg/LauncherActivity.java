package com.jerez.summonersgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Thread(new Runnable() {
            public void run() {
                LoadProgresBar();
                if (!isConected()){
                    Intent intent = new Intent(LauncherActivity.this, NoInternet.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        }).start();
    }

    public Boolean isConected(){
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null) && activeNetwork.isConnected();
    }

    private void LoadProgresBar(){
        ProgressBar progressBar = findViewById(R.id.progressBar);
        for (int i = 0; i <= 100; i++) {
            progressBar.setProgress(i);
            synchronized (this) {
                try {
                    wait(50);
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
        }
    }

}

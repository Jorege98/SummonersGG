package com.jerez.summonersgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Orianna.setRiotAPIKey("RGAPI-074774e5-41da-4823-9559-5c9f6c5de07e");
        Orianna.setDefaultRegion(Region.EUROPE_WEST);

        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();

        System.out.println(isConnected);
    }
}

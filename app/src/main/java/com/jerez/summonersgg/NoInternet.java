package com.jerez.summonersgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class NoInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_internet);
    }

    public void OnClick(View view){

        if (isConected()) {
            Intent intent = new Intent(NoInternet.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toasty.Config.getInstance().allowQueue(false).apply();
            Toasty.error(this, R.string.no_internet_alert, Toast.LENGTH_LONG, true).show();
        }
    }

    public Boolean isConected(){
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null) && activeNetwork.isConnected();
    }
}

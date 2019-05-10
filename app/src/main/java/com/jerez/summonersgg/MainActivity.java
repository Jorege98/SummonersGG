package com.jerez.summonersgg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jerez.summonersgg.ui.fragmentbusqueda.Fragment_busqueda;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.StatusBarHome);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Fragment_busqueda.newInstance())
                    .commitNow();
        }
    }
}

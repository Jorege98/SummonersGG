package com.jerez.summonersgg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.StatusBarHome);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment_busqueda.newInstance())
                        .commitNow();

                return true;
            case R.id.settings:
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.settings_tittle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new SettingsFragment())
                        .commit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

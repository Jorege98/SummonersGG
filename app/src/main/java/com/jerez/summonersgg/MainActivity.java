package com.jerez.summonersgg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jerez.summonersgg.ui.fragments.Fragment_busqueda;
import com.jerez.summonersgg.ui.fragments.Fragment_summoner;
import com.jerez.summonersgg.ui.fragments.MainActivityViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Menu menu;

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
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.StatusBarHome);
                showMenu();
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
            case R.id.info:
                Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.infoSupportBar);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, info.newInstance())
                        .commit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        try {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
            Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.StatusBarHome);
            showMenu();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Fragment_busqueda.newInstance())
                    .commitNow();
        }catch (Exception e){

        }
    }

    public void onSummonerFind(MainActivityViewModel viewModel) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle(viewModel.getSummoner().getName());
        hideMenu();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, Fragment_summoner.newInstance(viewModel))
                .commitNow();
    }

    void hideMenu(){
        boolean next = true;
        int cont = 0;
        do {
            try {
                menu.getItem(cont).setVisible(false);
            }catch (IndexOutOfBoundsException e){
                next=false;
            }
            cont++;
        }while (next);
    }

    void showMenu(){
        boolean next = true;
        int cont = 0;
        do {
            try {
                menu.getItem(cont).setVisible(true);
            }catch (IndexOutOfBoundsException e){
                next=false;
            }            cont++;
        }while (next);
    }
}

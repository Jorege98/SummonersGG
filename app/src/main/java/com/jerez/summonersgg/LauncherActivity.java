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

//        new Thread(new Runnable() {
//            public void run() {
//
//                try(InputStream stream = getAssets().open("config.json")) {
//                    Orianna.loadConfiguration(ByteSource.wrap(ByteStreams.toByteArray(stream)).asCharSource(Charset.forName("UTF-8")));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Orianna.setDefaultRegion(Region.EUROPE_WEST);
//                Orianna.setRiotAPIKey("RGAPI-418cec1b-07dd-447e-a2bf-6a93f4eececc");
//
//                Summoner summoner = Summoner.named("OG xPako").withRegion(Region.EUROPE_WEST).get();
//                System.out.println("Level: " + summoner.getLevel());
//            }
//        }).start();

        new Thread(new Runnable() {
            public void run() {
                LoadProgresBar();
                if (!isConected()){
                    Intent intent = new Intent(LauncherActivity.this, NoInternet.class);
                    startActivity(intent);

                    finish();
                }
            }

        }).start();
    }

    private Boolean isConected(){
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


//    public File cargar(){
//
//        File f = new File(getCacheDir()+"/config.json");
//        if (!f.exists()) try {
//
//            InputStream is = getAssets().open("config.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//
//
//            FileOutputStream fos = new FileOutputStream(f);
//            fos.write(buffer);
//            fos.close();
//        } catch (Exception e) { throw new RuntimeException(e); }
//
//        return f;
//    }
}

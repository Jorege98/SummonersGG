package com.jerez.summonersgg.ui.fragments;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.jerez.summonersgg.LauncherActivity;
import com.jerez.summonersgg.R;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MainActivityViewModel extends AndroidViewModel {

    private Resources resources;
    private Summoner summoner;
    private LeaguePosition tt;
    private LeaguePosition flex;
    private LeaguePosition soloq;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        resources = application.getResources();
    }

    Summoner loadSummoner(String region, String name) throws RiotApiException {

        ApiConfig config = new ApiConfig().setKey("RGAPI-418cec1b-07dd-447e-a2bf-6a93f4eececc");
        RiotApi api = new RiotApi(config);


        Summoner summoner = api.getSummonerByName(Platform.getPlatformByName(region), name);

        Collection<LeaguePosition> leaguePositionsBySummonerId = api.getLeaguePositionsBySummonerId(Platform.getPlatformByName(region), summoner.getId());

        for (LeaguePosition league :leaguePositionsBySummonerId) {
            switch (league.getQueueType()){
                case "RANKED_SOLO_5x5":
                    soloq = league;
                    break;
                case "RANKED_FLEX_TT":
                    tt = league;
                    break;
                case "RANKED_FLEX_SR":
                    flex = league;
                    break;
            }

        }

        return summoner;
    }


    Drawable getRegionImage(String region) {
        Drawable image = null;

        switch (region){
            case "EUW":
                image = resources.getDrawable(R.drawable.lec_eu, null);
                break;
            case "NA":
                image = resources.getDrawable(R.drawable.lcs_na, null);
                break;
            case "KR":
                image = resources.getDrawable(R.drawable.lck_korea, null);
                break;
            case "JP":
                image = resources.getDrawable(R.drawable.ljl_japan, null);
                break;
            case "BR":
                image = resources.getDrawable(R.drawable.cblol_brazil, null);
                break;
            case "OCE":
                image = resources.getDrawable(R.drawable.opl_oceania, null);
                break;
            case "RU":
                image = resources.getDrawable(R.drawable.lcl_russia, null);
                break;
            case "TR":
                image = resources.getDrawable(R.drawable.tcl_turkey, null);
                break;
            case "LAN":
                image = resources.getDrawable(R.drawable.lla_lans, null);
                break;
            case "LAS":
                image = resources.getDrawable(R.drawable.lla_lans, null);
                break;
            default:
                image = resources.getDrawable(R.drawable.generic, null);
                break;
        }

        return image;
    }

    public Summoner getSummoner() {
        return summoner;
    }

    void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    LeaguePosition getTt() {
        return tt;
    }

    LeaguePosition getFlex() {
        return flex;
    }

    LeaguePosition getSoloq() {
        return soloq;
    }

    Bitmap getSummonerIcon(int id) throws IOException {
        String preURL = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/"+id+".png";
        URL url = null;
        Bitmap image = null;

        url =  new URL(preURL);
        image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        image = Bitmap.createScaledBitmap(image, 500, 500, false);
        image = getCroppedBitmap(image);

        return  image;
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

}

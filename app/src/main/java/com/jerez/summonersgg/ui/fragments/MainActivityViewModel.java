package com.jerez.summonersgg.ui.fragments;

import android.app.Application;
import android.content.res.Resources;
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
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MainActivityViewModel extends AndroidViewModel {

    private Resources resources;
    private Summoner summoner;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        resources = application.getResources();
    }

    Summoner loadSummoner(String region, String name) throws RiotApiException {

        ApiConfig config = new ApiConfig().setKey("RGAPI-418cec1b-07dd-447e-a2bf-6a93f4eececc");
        RiotApi api = new RiotApi(config);


        Summoner summoner = api.getSummonerByName(Platform.getPlatformByName(region), name);

//        Collection<LeaguePosition> leaguePositionsBySummonerId = api.getLeaguePositionsBySummonerId(Platform.getPlatformByName(region), summoner.getId());
//
//        for (LeaguePosition leage :leaguePositionsBySummonerId) {
//            leage.getTier();
//        }

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

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }
}

package com.jerez.summonersgg.ui.fragmentbusqueda;

import androidx.lifecycle.ViewModel;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

public class MainActivityViewModel extends ViewModel {



    public Summoner loadSummoner(String region, String name) throws RiotApiException {

        ApiConfig config = new ApiConfig().setKey("RGAPI-418cec1b-07dd-447e-a2bf-6a93f4eececc");
        RiotApi api = new RiotApi(config);

        Summoner summoner = api.getSummonerByName(Platform.EUW, "OG qwerxPako");

        return summoner;
    }
}

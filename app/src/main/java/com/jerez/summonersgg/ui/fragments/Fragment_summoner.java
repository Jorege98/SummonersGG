package com.jerez.summonersgg.ui.fragments;

import android.annotation.SuppressLint;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jerez.summonersgg.R;

import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Fragment_summoner extends Fragment {

    private static MainActivityViewModel mViewModel;

    public static Fragment_summoner newInstance(MainActivityViewModel ViewModel) {
        mViewModel = ViewModel;
        return new Fragment_summoner();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView summonerIcon = getView().findViewById(R.id.icon);
        TextView level = getView().findViewById(R.id.level);
        try {
            summonerIcon.setImageBitmap(mViewModel.getSummonerIcon(mViewModel.getSummoner().getProfileIconId()));
            level.setText(getResources().getString(R.string.level)+mViewModel.getSummoner().getSummonerLevel());
        } catch (IOException e) {
            summonerIcon.setImageDrawable(getResources().getDrawable(R.drawable.notfound, null));
        }

        TextView solosub = getView().findViewById(R.id.soloqsub);
        TextView flexsub = getView().findViewById(R.id.flexsub);
        TextView ttsub = getView().findViewById(R.id.ttsub);


        if (mViewModel.getSoloq()!=null){
            ImageView soloqIcon =  getView().findViewById(R.id.soloqIcon);
            setLeagueIcon(soloqIcon, mViewModel.getSoloq().getTier());
            setLeagueText(solosub, mViewModel.getSoloq());
            TextView solowins = getView().findViewById(R.id.solowins);
            solowins.setText("W "+mViewModel.getSoloq().getWins());
            TextView sololose = getView().findViewById(R.id.sololoss);
            sololose.setText("L "+mViewModel.getSoloq().getLosses());
        }
        if (mViewModel.getFlex()!=null){
            ImageView flexIcon = getView().findViewById(R.id.flexIcon);
            setLeagueIcon(flexIcon, mViewModel.getFlex().getTier());
            setLeagueText(flexsub, mViewModel.getFlex());
            TextView flexwins = getView().findViewById(R.id.flexwins);
            flexwins.setText("W "+mViewModel.getFlex().getWins());
            TextView flexloss = getView().findViewById(R.id.flexloss);
            flexloss.setText("L "+mViewModel.getFlex().getLosses());
        }
        if (mViewModel.getTt()!=null){
            ImageView ttIcon = getView().findViewById(R.id.ttIcon);
            setLeagueIcon(ttIcon, mViewModel.getTt().getTier());
            setLeagueText(ttsub, mViewModel.getTt());
            TextView ttwins = getView().findViewById(R.id.ttwins);
            ttwins.setText("W "+mViewModel.getTt().getWins());
            TextView ttloss = getView().findViewById(R.id.ttloss);
            ttloss.setText("L "+mViewModel.getTt().getLosses());
        }




    }

    @SuppressLint("SetTextI18n")
    private void setLeagueText(TextView sub, LeaguePosition league) {
        switch (league.getTier().toUpperCase()){
            case "IRON":
                sub.setText(getResources().getString(R.string.iron)+" "+league.getRank());
                break;
            case "BRONZE":
                sub.setText(getResources().getString(R.string.bronze)+" "+league.getRank());
                break;
            case "SILVER":
                sub.setText(getResources().getString(R.string.silver)+" "+league.getRank());
                break;
            case "GOLD":
                sub.setText(getResources().getString(R.string.gold)+" "+league.getRank());
                break;
            case "PLATINUM":
                sub.setText(getResources().getString(R.string.plat)+" "+league.getRank());
                break;
            case "DIAMOND":
                sub.setText(getResources().getString(R.string.dia)+" "+league.getRank());
                break;
            case "MASTER":
                sub.setText(getResources().getString(R.string.master));
                break;
            case "GRANDMASTER":
                sub.setText(getResources().getString(R.string.grandma));
                break;
            case "CHALLENGER":
                sub.setText(getResources().getString(R.string.challenger));
                break;
        }
    }

    private void setLeagueIcon(@NotNull ImageView icon, @NotNull String tier){
        switch (tier.toUpperCase()){
            case "IRON":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.iron, null));
                break;
            case "BRONZE":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.bronze, null));
                break;
            case "SILVER":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.silver, null));
                break;
            case "GOLD":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.gold, null));
                break;
            case "PLATINU":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.platinium, null));
                break;
            case "DIAMOND":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.diamond, null));
                break;
            case "MASTER":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.master, null));
                break;
            case "GRANDMASTER":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.grandmaster, null));
                break;
            case "CHALLENGER":
                icon.setImageDrawable(getResources().getDrawable(R.drawable.challenger, null));
                break;
        }
    }

}

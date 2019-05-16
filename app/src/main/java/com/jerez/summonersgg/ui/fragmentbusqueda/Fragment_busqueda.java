package com.jerez.summonersgg.ui.fragmentbusqueda;

import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jerez.summonersgg.R;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;



public class Fragment_busqueda extends Fragment {

    private MainActivityViewModel mViewModel;

    public static Fragment_busqueda newInstance() {
        return new Fragment_busqueda();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return inflater.inflate(R.layout.fragment_busqueda, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String regionPeference = preferences.getString("region", null);

        String[] regions = getResources().getStringArray(R.array.regions);

        for (String region:regions) {

        }

        try {
            Summoner summoner = mViewModel.loadSummoner("","");
            setText(summoner.getName());
        } catch (RiotApiException e) {
            e.printStackTrace();
        }

    }

    //metodo de pruebas para set text
    private void setText(String text){

        TextView textView = getView().findViewById(R.id.message);
        textView.setText(text);
    }
}

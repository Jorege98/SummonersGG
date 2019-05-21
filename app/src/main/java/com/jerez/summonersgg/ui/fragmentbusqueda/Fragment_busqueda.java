package com.jerez.summonersgg.ui.fragmentbusqueda;

import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jerez.summonersgg.R;

import es.dmoral.toasty.Toasty;

public class Fragment_busqueda extends Fragment implements View.OnClickListener {

    private MainActivityViewModel mViewModel;
    private TextView regionTitle;
    private TextView regionSub;
    private ImageView regionImage;
    private String regionPeference;
    private Button find;

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
        regionTitle = getView().findViewById(R.id.infoTitle);
        regionSub = getView().findViewById(R.id.infoSub);
        regionImage = getView().findViewById(R.id.infoImagen);
        find = getView().findViewById(R.id.find);

        find.setOnClickListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        regionPeference = preferences.getString("region", null);

        if (regionPeference==null){
            regionTitle.setText(getResources().getString(R.string.noregion));
        }else{
            regionImage.setImageDrawable(mViewModel.getRegionImage(regionPeference.split(", ")[0]));
            regionTitle.setText(regionPeference.split(", ")[1]);
            regionSub.setText(regionPeference.split(", ")[0]);
        }


    }

    //metodo de pruebas para set text
    private void setText(String text){
//        try {
//            Summoner summoner = mViewModel.loadSummoner(regionPeference.split(", ")[0], "OG xPako");
//            setText(summoner.getName());
//        } catch (RiotApiException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onClick(View v) {
        if (regionPeference==null){
            Toasty.Config.getInstance().allowQueue(false).apply();
            Toast toast = Toasty.error(getActivity(), R.string.noregion, Toast.LENGTH_LONG, true);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{

        }
    }
}

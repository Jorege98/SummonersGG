package com.jerez.summonersgg.ui.fragmentbusqueda;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jerez.summonersgg.R;

import java.util.Map;

public class Fragment_busqueda extends Fragment {

    private MainActivityViewModel mViewModel;

    public static Fragment_busqueda newInstance() {
        return new Fragment_busqueda();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_busqueda, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Map<String, ?> all = preferences.getAll();

        setText(all.toString());
        // TODO: Use the ViewModel
    }

    public void setText(String text){

        TextView textView = getView().findViewById(R.id.message);
        textView.setText(text);
    }
}

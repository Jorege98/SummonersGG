package com.jerez.summonersgg;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class info extends Fragment {


    public static info newInstance() {
        return new info();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        TextView t2 = getView().findViewById(R.id.infoSub);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
        super.onActivityCreated(savedInstanceState);
    }

}

package com.example.sleephelper.meizi.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sleephelper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment {


    public MeiziFragment() {
        // Required empty public constructor
    }

    public static MeiziFragment newInstance() {

        Bundle args = new Bundle();

        MeiziFragment fragment = new MeiziFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meizi, container, false);
    }

}

package com.example.sleephelper.duanzi.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sleephelper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuanziFragment extends Fragment {


    public DuanziFragment() {
        // Required empty public constructor
    }

    public static DuanziFragment newInstance() {

        Bundle args = new Bundle();

        DuanziFragment fragment = new DuanziFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_duanzi, container, false);
    }

}

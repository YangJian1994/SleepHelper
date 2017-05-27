package com.example.sleephelper.meizi.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.sleephelper.R;
import com.example.sleephelper.common.net.VolleyHelper;
import com.example.sleephelper.common.net.VolleyResponseCallback;
import com.example.sleephelper.meizi.api.MeiziApi;
import com.example.sleephelper.meizi.bean.MeiziBean;
import com.example.sleephelper.meizi.utils.GsonHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends Fragment {


    @Bind(R.id.meizi_recycler)
    RecyclerView meiziRecycler;
    @Bind(R.id.meizi_refresh)
    SwipeRefreshLayout meiziRefresh;

    private List<MeiziBean> meiziBeanList = new ArrayList<>();
    private static final String TAG = MeiziFragment.class.getSimpleName();

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
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        ButterKnife.bind(this, view);
        initView();
        refreshMeizi();
        return view;
    }

    private void refreshMeizi() {
        meiziRefresh.setColorSchemeResources(R.color.colorPrimary);
        meiziRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
                meiziRefresh.setRefreshing(false);
            }
        });
    }

    private void initView() {
        VolleyHelper.sendHttpGet(getActivity(), MeiziApi.getMeiziApi(), new VolleyResponseCallback() {
            @Override
            public void onSuccess(String response) {
                meiziBeanList = GsonHelper.getMeiziBean(response);
                meiziRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                Collections.shuffle(meiziBeanList);
                meiziRecycler.setAdapter(new MeiziAdapter(MeiziFragment.this, meiziBeanList));
            }

            @Override
            public void onError(VolleyError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

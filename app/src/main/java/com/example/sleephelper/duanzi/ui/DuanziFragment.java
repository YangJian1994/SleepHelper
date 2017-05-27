package com.example.sleephelper.duanzi.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.sleephelper.R;
import com.example.sleephelper.common.net.VolleyHelper;
import com.example.sleephelper.common.net.VolleyResponseCallback;
import com.example.sleephelper.duanzi.api.DuanziApi;
import com.example.sleephelper.duanzi.bean.DuanziBean;
import com.example.sleephelper.duanzi.utils.GsonHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DuanziFragment extends Fragment {

    private static final String TAG = DuanziFragment.class.getSimpleName();
    @Bind(R.id.duanzi_recyclerView)
    RecyclerView duanziRecycler;
    @Bind(R.id.duanzi_refresh)
    SwipeRefreshLayout duanziRefresh;

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
        View view = inflater.inflate(R.layout.fragment_duanzi, container, false);
        ButterKnife.bind(this, view);
        initView();
        initRefresh();
        return view;
    }

    private void initRefresh() {
        duanziRefresh.setColorSchemeResources(R.color.colorPrimary);
        duanziRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
                duanziRefresh.setRefreshing(false);
            }
        });
    }

    private void initView() {
        VolleyHelper.sendHttpGet(getActivity(), DuanziApi.GET_DUANZI, new VolleyResponseCallback() {
            @Override
            public void onSuccess(String response) {
                List<DuanziBean> duanziBeanList = GsonHelper.getDuanziBeanList(response);
                duanziRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                duanziRecycler.setAdapter(new DuanziAdapter(DuanziFragment.this, duanziBeanList));;
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

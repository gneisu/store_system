package com.poya.pengfusheng.repositorysys.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poya.pengfusheng.repositorysys.R;

/**
 * Created by pengfusheng on 2016/2/26.
 */
public class MainFragmentTable extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_table, container, false);
        return view;
    }
}

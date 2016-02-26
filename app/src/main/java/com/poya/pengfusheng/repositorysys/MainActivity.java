package com.poya.pengfusheng.repositorysys;

import android.support.v4.app.Fragment;

import com.poya.pengfusheng.repositorysys.fragment.MainFragment;
import com.poya.pengfusheng.repositorysys.fragment.MainFragmentTable;

/**
 * Created by pengfusheng on 2016/1/27.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
//        return new MainFragmentTable();
    }
}

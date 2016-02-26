package com.poya.pengfusheng.repositorysys;

import android.support.v4.app.Fragment;

import com.poya.pengfusheng.repositorysys.fragment.DomainFragment;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class DomainActivity extends BaseActivity {
    @Override
    protected Fragment createFragment() {
        return new DomainFragment();
    }
}

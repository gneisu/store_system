package com.poya.pengfusheng.repositorysys;

import android.support.v4.app.Fragment;

import com.poya.pengfusheng.repositorysys.fragment.UserFragment;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class UserActivity extends BaseActivity {
    @Override
    protected Fragment createFragment() {
        return new UserFragment();
    }
}

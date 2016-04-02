package com.poya.pengfusheng.repositorysys;

import android.support.v4.app.Fragment;

import com.poya.pengfusheng.repositorysys.fragment.PackageGetFragment;
import com.poya.pengfusheng.repositorysys.fragment.PackageInputFragment;

/**
 * Created by pengfusheng on 2016/1/27.
 */
public class PackageInputActivity extends BaseActivity {
    @Override
    protected Fragment createFragment() {
        return new PackageInputFragment();
    }
}

package com.poya.pengfusheng.repositorysys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.poya.pengfusheng.repositorysys.base.ActivityPool;

/**
 * Created by pengfusheng on 2016/1/27.
 */
public abstract class BaseActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = this.createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
        ActivityPool.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityPool.removeActivity(this);
    }
}

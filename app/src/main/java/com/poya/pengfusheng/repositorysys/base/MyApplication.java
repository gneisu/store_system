package com.poya.pengfusheng.repositorysys.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by pengfusheng on 2016/2/23.
 */
public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }


}

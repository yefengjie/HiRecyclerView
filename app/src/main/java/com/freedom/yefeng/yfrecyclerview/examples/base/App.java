package com.freedom.yefeng.yfrecyclerview.examples.base;

import android.app.Application;


/**
 * Created by yee on 11/1/13.
 * <p/>
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppInfo.init(getApplicationContext());
    }
}

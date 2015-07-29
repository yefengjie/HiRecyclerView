package com.freedom.yefeng.yfrecyclerview.example.base;

import android.app.Application;

import com.freedom.yefeng.yfrecyclerview.libs.DisplayInfo;


/**
 * Created by yee on 11/1/13.
 * <p>
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayInfo.init(getApplicationContext());
    }
}

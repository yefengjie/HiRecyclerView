package com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.example.base;

import android.app.Application;

import com.freedom.yefeng.recycleviewwithloadingerroremptyheaderfooter.libs.DisplayInfo;


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

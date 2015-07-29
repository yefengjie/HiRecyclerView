package com.freedom.yefeng.yfrecyclerview.libs;

import android.content.Context;
import android.util.DisplayMetrics;


/**
 * Created by yee on 11/18/13.
 *
 * @author yefeng
 */
public class DisplayInfo {

    public static int width;
    public static int height;
    public static float density;
    public static int densityDpi;

    public static void init(Context mContext) {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        density = metrics.density;
        densityDpi = metrics.densityDpi;
    }
}

package com.freedom.yefeng.yfrecyclerview.util;

/**
 * Created by yefeng on 8/7/15.
 * github:yefengfreedom
 */
public class LogUtil {

    private static boolean sDebug = false;

    public static void i(String tag, String msg) {
        if (sDebug) android.util.Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (sDebug) android.util.Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (sDebug) android.util.Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (sDebug) android.util.Log.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (sDebug) android.util.Log.v(tag, msg);
    }
}

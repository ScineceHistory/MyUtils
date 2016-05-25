package com.zjut.myutils;

import android.util.Log;

/**
 * Created by ScienceHistory on 16/5/25.
 * 打印日志的工具类
 * 能够自由地控制日志的打印,当程序处于开发阶段就让日志打印出来,当程序上线了之后就把日志屏蔽掉
 * 开发阶段：LEVEL = VERBOSE
 * 上线阶段：LEVEL = NOTHING
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;

    // 当LEVEL值为NOTHING（6）时，下面日志方法均会失效，对应于上线阶段
    // public static final int LEVEL = NOTHING;

    // 当LEVEL值为VERBOSE（1）时，下面日志方法均会失效，对应于开发阶段
    public static int LEVEL = VERBOSE;

    public static void setLevel(int level) {
        LEVEL = level;
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }
}

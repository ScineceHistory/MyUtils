package com.zjut.myutils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by ScienceHistory on 16/5/17.
 * 获取有关屏幕的信息
 */
public class ScreenUtil {
    /**
     * 获取屏幕的显示数据（size, density, and font scaling）
     * @param context 上下文界面
     * @return 屏幕的宽高
     */
    public static DisplayMetrics getScreenDisplayMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics;
    }

    /**
     * 获取屏幕的density
     * @param context 上下文
     * @return 屏幕的密度density，表示px与dp的缩放比例1dp ＝ density ＊ 1px
     */
    public static float getDeviceDensity(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }
}


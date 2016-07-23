package com.zjut.myutils.utils;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public class AnimationUtil {
    private static AnimationUtil util;
    public static AnimationUtil getInstance() {
        if (util == null) {
            util = new AnimationUtil();

        }
        return util;
    }
    private AnimationUtil() {
        super();
    }

    /**
     * 给listView加一个刷新动画的EmptyView listview外层最好用FrameLayout单独包着
     */
    public void setAnimationEmptyView(Context context, AbsListView lv,
                                      ProgressBar bar, String str) {
        ViewGroup parentView = (ViewGroup) lv.getParent();
        removeItem(parentView, lv, -10321);
        removeItem(parentView, lv, -10322);
        LinearLayout wait = getProgressView(context, bar, str);
        parentView.addView(wait);
        lv.setTag(-10322, wait);
        lv.setEmptyView(wait);
    }


    /**
     * 给listView加一个数据为空时EmptyView listview外层最好用FrameLayout单独包着
     */
    public void setNoDataEmptyView(Context context, AbsListView lv,
                                   Integer rid, String message, OnClickListener listener) {
        ViewGroup parentView = (ViewGroup) lv.getParent();

        removeItem(parentView, lv, -10321);
        removeItem(parentView, lv, -10322);

        LinearLayout inflate = getEmptyView(context, rid, message, listener);
        parentView.addView(inflate);
        lv.setEmptyView(inflate);
        lv.setTag(-10321, inflate);
    }

    /**
     * 删除上一个EmptyView
     */
    private void removeItem(ViewGroup parentView, AbsListView lv, int index) {
        Object tag = lv.getTag(index);
        if (tag != null && tag instanceof View) {
            View s = (View) tag;
            parentView.removeView(s);
            lv.setTag(index, null);
        }
    }

    /**
     * 得到一个刷新动画的EmptyView
     */
    private LinearLayout getProgressView(Context context, ProgressBar bar,
                                         String str) {
        LinearLayout ll1 = new LinearLayout(context);
        ll1.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        ll1.setGravity(Gravity.CENTER);
        ll1.setLayoutParams(params);
        if (bar == null) {
            bar = new ProgressBar(context);
        }

        TextView textView = new TextView(context);

        textView.setText(str);
        textView.setGravity(Gravity.CENTER);

        ll1.addView(bar);
        ll1.addView(textView);
        return ll1;
    }

    /**
     * 得到一个数据为空时的EmptyView
     */
    private LinearLayout getEmptyView(Context context, Integer rid, String str,
                                      OnClickListener click) {
        LinearLayout ll2 = new LinearLayout(context);
        ll2.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        ll2.setGravity(Gravity.CENTER);
        ll2.setLayoutParams(params);
        if (click != null) {
            ll2.setOnClickListener(click);
        }

        if (rid != null) {

            ImageView imageView = new ImageView(context);
            imageView.setImageResource(rid);
            ll2.addView(imageView);
        }
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setGravity(Gravity.CENTER);

        ll2.addView(textView);

        return ll2;
    }

    /**
     * 给view设置动画效果，并设置动画结束后回调
     */
    public static void startAnimation(Context context, View v, int animationId,
                                      final OnAnimEnd ae) {
        startAnimation(context, v, animationId, null, ae);
    }

    /**
     * 给view设置动画效果，并设置动画结束后回调
     */
    public static void startAnimation(Context context, View v, Animation anim,
                                      final OnAnimEnd ae) {
        startAnimation(context, v, 0, anim, ae);
    }

    /**
     * 给view设置动画效果，并设置动画结束后回调
     */
    private static void startAnimation(Context context, View v,
                                       int animationId, Animation anim, final OnAnimEnd ae) {
        if (anim == null) {
            anim = AnimationUtils.loadAnimation(context, animationId);
        }
        v.startAnimation(anim);

        anim.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                if (ae != null) {
                    ae.end();
                }

            }
        });

    }

    public interface OnAnimEnd {
        void end();
    }

    /**
     * 将ImageView的图片变为灰色，0灰色，1彩色
     */
    public void setImageViewGray(ImageView iv, float f) {
        try {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(f);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            iv.setColorFilter(filter);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * ImageView开始播放动画
     */
    public void startImageAnimation(ImageView iv) {
        iv.setVisibility(View.VISIBLE);
        AnimationDrawable anim = (AnimationDrawable) iv.getDrawable();
        anim.start();
    }

    /**
     * 旋转动画，旋转后保留最后的状态
     */
    public android.view.animation.RotateAnimation getRotateAnimation(int rotate) {

        android.view.animation.RotateAnimation animation = new android.view.animation.RotateAnimation(
                0, rotate);
        animation.setFillAfter(true);
        animation.setInterpolator(new LinearInterpolator());
        return animation;
    }
}

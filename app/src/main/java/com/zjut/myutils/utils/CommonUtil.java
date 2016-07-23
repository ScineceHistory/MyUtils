package com.zjut.myutils.utils;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

public class CommonUtil {
    //拨打电话
    public static void call(Context context, String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(
                new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
    }

    //跳转至拨号界面
    public static void callDial(Context context, String phoneNumber) {
        context.startActivity(
                new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
    }

    //发送短信
    public static void sendSms(Context context, String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" +
                (TextUtils.isEmpty(phoneNumber) ? "" : phoneNumber));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", TextUtils.isEmpty(content) ? "" : content);
        context.startActivity(intent);
    }

    //唤醒屏幕并解锁
    //需要添加权限
    //<uses-permission android:name= "android.permission.WAKE_LOCK" />
    //<uses-permission android:name= "android.permission.DISABLE_KEYGUARD" />
    public static void wakeUpAndUnlock(Context context) {
        KeyguardManager km = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm = (PowerManager) context
                .getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(
                PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
        //点亮屏幕
        wl.acquire();
        //释放
        wl.release();
    }


}

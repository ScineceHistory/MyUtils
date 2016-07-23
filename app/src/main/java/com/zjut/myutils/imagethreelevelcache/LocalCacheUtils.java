package com.zjut.myutils.imagethreelevelcache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.zjut.myutils.utils.MD5Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 作者 @ScienceHistory
 * 时间 @2016年07月23日 15:48
 * 备注 @三级缓存之本地缓存
 */

public class LocalCacheUtils {

    private static final String CACHE_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/WebPics";

    /**
     * 从本地获取图片
     * @param url 图片地址
     * @return 图片bitmap
     */
    public Bitmap getBitmapFromLocal(String url) {

        String filename = null; // 把图片的url作为文件名，并进行MD5加密
        try {
            filename = MD5Encoder.encode(url);
            File file = new File(CACHE_PATH,filename);
            return BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 从网络获取图片后保存至本地进行缓存
     * @param url 图片标识
     * @param bitmap 缓存图片
     */
    public void setBitmapToLocal(String url, Bitmap bitmap) {
        String filename = MD5Encoder.encode(url);  // //把图片的url当做文件名,并进行MD5加密
        File file = new File(CACHE_PATH, filename);

        // 通过得到文件的父文件，判断父文件是否存在
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        // 把图片保存至本地
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG ,100, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

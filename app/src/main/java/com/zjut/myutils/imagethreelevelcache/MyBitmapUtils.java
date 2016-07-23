package com.zjut.myutils.imagethreelevelcache;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.zjut.myutils.R;

/**
 * 作者 @ScienceHistory
 * 时间 @2016年07月23日 15:39
 * 备注 @自定义图片缓存工具类，实现图片的三级缓存
 */
public class MyBitmapUtils {

    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitmapUtils() {
        mMemoryCacheUtils = new MemoryCacheUtils();
        mLocalCacheUtils = new LocalCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mMemoryCacheUtils, mLocalCacheUtils);
    }

    public void display(ImageView ivPic, String url) {

        ivPic.setImageResource(R.mipmap.ic_launcher);    // 加载默认图片
        Bitmap bitmap;

        // 内存缓存
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            Log.d("Science", "display: 从内存获取图片");
            return;
        }

        // 本地缓存
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            Log.d("Science", "display: 从本地获取图片");
            // 从本地获取图片后，保存到内存中进行缓存
            mMemoryCacheUtils.setBitmapToMemory(url,bitmap);
            return;
        }

        // 网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic,url);
    }

}

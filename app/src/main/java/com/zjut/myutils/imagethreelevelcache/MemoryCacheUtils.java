package com.zjut.myutils.imagethreelevelcache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 作者 @ScienceHistory
 * 时间 @2016年07月23日 15:48
 * 备注 @三级缓存之内存缓存
 */
public class MemoryCacheUtils {

    // 1.因为强引用,容易造成内存溢出，所以考虑使用下面弱引用的方法
    //private HashMap<String,Bitmap> mMemoryCache = new HashMap<>();
    // 2.因为在Android2.3+后,系统会优先考虑回收弱引用对象,官方提出使用LruCache
    //private HashMap<String,SoftReference<Bitmap>> mMemoryCache= new HashMap<>();
    // 3.LruCache
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCacheUtils() {

        long maxMemory = Runtime.getRuntime().maxMemory() / 8; // 得到手机最大允许内存的1/8,即超过指定内存,则开始回收
        //需要传入允许的内存最大值,虚拟机默认内存16M,真机不一定相同
        mMemoryCache = new LruCache<String, Bitmap>((int) maxMemory) {
            // 用于计算每个条目的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    /**
     * 从内存中获取图片
     * @param url 图片的标示
     * @return 图片bitmap
     */
    public Bitmap getBitmapFromMemory(String url) {

        //1.强引用方法
        //Bitmap bitmap = mMemoryCache.get(url);
        //2.弱引用方法
        //SoftReference<Bitmap> bitmapSoftReference = mMemoryCache.get(url);
//        if (bitmapSoftReference != null) {
//            return bitmapSoftReference.get();
//        }
        return mMemoryCache.get(url);
    }

    /**
     * 往内存里面写图片
     * @param url    图片bitmap的标示
     * @param bitmap 写入的图片
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {

        //1.强引用方法
        //mMemoryCache.put(url, bitmap);
        /*2.弱引用方法
        mMemoryCache.put(url, new SoftReference<>(bitmap));
        */
        mMemoryCache.put(url,bitmap);
    }

}

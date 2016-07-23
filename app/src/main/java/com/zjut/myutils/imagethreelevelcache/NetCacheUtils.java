package com.zjut.myutils.imagethreelevelcache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者 @ScienceHistory
 * 时间 @2016年07月23日 15:48
 * 备注 @三级缓存之网络缓存
 */

public class NetCacheUtils {

    private MemoryCacheUtils mMemoryCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;

    public NetCacheUtils(MemoryCacheUtils memoryCacheUtils, LocalCacheUtils localCacheUtils) {
        mMemoryCacheUtils = memoryCacheUtils;
        mLocalCacheUtils = localCacheUtils;
    }

    /**
     * 从网络下载图片，用到了AsyncTask来进行异步数据的加载
     * @param ivPic 显示图片的ImageView
     * @param url 下载图片的网络地址
     */
    public void getBitmapFromNet(ImageView ivPic, String url) {
        BitmapTask picTask = new BitmapTask();
        picTask.execute(ivPic,url); // 启动AsyncTask
    }

    /**
     * AsyncTask是对handler和线程池的封装
     * 第一个范型参数：输入参数类型
     * 第二个范型参数：更新进度的范型
     * 第三个范型参数：onPostExecute()执行后的返回结果范型
     */
    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView ivPic;
        private String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 后台耗时任务，在子线程中进行
         * @param params 显示图片的ImageView和图片的网络地址url，此处是一个对象数组
         * @return 下载的图片Bitmap
         */
        @Override
        protected Bitmap doInBackground(Object... params) {
            ivPic = (ImageView) params[0];  // 顺序与参数的传入顺序对应
            url = (String) params[1];
            return downloadBitmap(url);
        }

        /**
         * 更新进度在主线程中进行
         * @param values 需要更行的进度值
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 耗时任务执行完毕后执行该方法，在主线程中，进行一些扫尾工作
         * @param bitmap 耗时任务返回的执行结果
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                ivPic.setImageBitmap(bitmap);
                Log.d("Science", "onPostExecute: 已经从网络处下载到图片");

                // 从网络获取图片后，分别保存至本地和内存中进行缓存
                // 方便下载需要同样图片时直接在本地和内存中调用即可
                // 此处传入的url作为该图片的一个标示
                mLocalCacheUtils.setBitmapToLocal(url,bitmap);
                mMemoryCacheUtils.setBitmapToMemory(url,bitmap);
            }
        }
    }

    /**
     * 网络下载图片
     * @param url 图片地址
     * @return 下载到的图片
     */
    private Bitmap downloadBitmap(String url) {
        HttpURLConnection conn = null;
        try {
            URL picUrl = new URL(url);
            conn = (HttpURLConnection) picUrl.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                // 对下载的图片进行压缩
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                return BitmapFactory.decodeStream(conn.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
}

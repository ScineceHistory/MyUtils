package com.zjut.myutils.myself;

/**
 * Created by ScienceHistory on 16/4/9.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}

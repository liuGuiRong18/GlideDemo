package com.android.summersoft.glidedemo.glide.listener;

import android.graphics.Bitmap;


public interface IGetBitmapListener {

    /***
     * 成功回调
     * @param bitmap
     */
    void onBitmap(Bitmap bitmap);

    /***
     * 失败回调
     */
    void onFailed();
}

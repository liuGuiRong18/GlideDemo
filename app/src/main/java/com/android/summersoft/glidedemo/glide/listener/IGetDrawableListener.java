package com.android.summersoft.glidedemo.glide.listener;

import android.graphics.drawable.Drawable;



/**
 * @author SummerSoft.L
 * @description
 */
public interface IGetDrawableListener {

    /**
     * 成功回调
     *
     * @param drawable
     */
    void onDrawable(Drawable drawable);

    /***
     * 失败回调
     */
    void onFailed();
}

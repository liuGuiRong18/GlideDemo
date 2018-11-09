package com.android.summersoft.glidedemo.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.summersoft.glidedemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
/**
 * @author SummerSoft.L
 * @description glide 全局配置https://muyangmin.github.io/glide-docs-cn/doc/configuration.html
 *   硬盘缓存策略：
 *   1. DiskCacheStrategy.NONE： 表示不缓存任何内容。
 *   2.DiskCacheStrategy.DATA： 表示只缓存原始图片。
 *   3.DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
 *   4. DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
 *   5.DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
 */
@GlideModule
public class MyAppGlideModule extends com.bumptech.glide.module.AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.mipmap.ic_launcher)
                        .skipMemoryCache(false)); //Glide默认开启内存缓存。关掉内存缓存：true
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

}
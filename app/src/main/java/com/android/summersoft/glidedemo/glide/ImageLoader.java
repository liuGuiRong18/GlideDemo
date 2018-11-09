package com.android.summersoft.glidedemo.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.android.summersoft.glidedemo.glide.listener.BitmapTarget;
import com.android.summersoft.glidedemo.glide.listener.DrawableTarget;
import com.android.summersoft.glidedemo.glide.listener.IGetBitmapListener;
import com.android.summersoft.glidedemo.glide.listener.IGetDrawableListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.Transition;


/**
 * @author SummerSoft.L
 * @description glide图片工具：1.默认开启内存缓存 2.硬盘缓存选择缓存转换过后的图片
 */
public class ImageLoader {


    /***
     *  with(Context context). 使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制。
     *  with(Activity activity).使用Activity作为上下文，Glide的请求会受到Activity生命周期控制。
     *  with(FragmentActivity activity).Glide的请求会受到FragmentActivity生命周期控制。
     *  with(android.app.Fragment fragment).Glide的请求会受到Fragment 生命周期控制。
     *  with(android.support.v4.app.Fragment fragment).Glide的请求会受到Fragment生命周期控制。
     * @param context
     * @param resId
     * @param imageView
     */
    public static void displayImage(Context context, int resId, ImageView imageView) {
        GlideApp.with(context)
                .load(resId)
                .into(imageView);
    }

    /**
     * @param context
     * @param url       gif格式
     * @param imageView
     */
    public static void displayImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .into(imageView);
    }

    /***
     * 关闭硬盘和内存缓存
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayImageSkipCache(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(new RequestOptions()
                                .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                .load(url)
                .into(imageView);
    }

    /***
     *
     * @param context
     * @param url
     * @param imageView
     * @param placeholderId 占位图图片id
     */
    public static void displayImageWithPlaceholder(Context context, String url, ImageView imageView, int placeholderId) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(new RequestOptions()
                                .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .placeholder(placeholderId))
                .load(url)
                .into(imageView);
    }

    /***
     * @param context
     * @param url
     * @param imageView
     * @param errorId 错误符图片id 请求永久性失败时展示。error Drawable 同样也在请求的url/model为 null ，且并没有设置 fallback Drawable 时展示。
     */
    public static void displayImageWithError(Context context, String url, ImageView imageView, int errorId) {
        GlideApp.with(context)
                .load(url)
                .error(errorId)
                .into(imageView);
    }

    /***
     *  @param context
     * @param url
     * @param imageView
     * @param fallbackId 后备回调符图片id 请求的url/model为 null 时展示
     */
    public static void displayImageWithFallback(Context context, String url, ImageView imageView, int fallbackId) {
        GlideApp.with(context)
                .load(url)
                .fallback(fallbackId)
                .into(imageView);
    }

    public static void displayImageWithCircleCrop(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(new RequestOptions().circleCrop())
                .load(url)
                .into(imageView);
    }


    public static void displayImageWithThumbnail(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .thumbnail(0.01F)
                .into(imageView);
    }

    /***
     * 下载大图可以使用
     * @param context
     * @param url
     * @param imageView
     * @param thumbnailUrl
     */
    public static void displayImageWithThumbnailUrl(Context context, String url, ImageView imageView, String thumbnailUrl) {
        GlideApp.with(context)
                .load(url)
                .thumbnail(Glide
                        .with(context)
                        .load(thumbnailUrl).thumbnail(0.1F))
                .into(imageView);

    }

    /***
     * 预加载
     * @param context
     * @param url
     */
    public static void displayImageWithPreload(Context context, String url) {
        GlideApp.with(context)
                .load(url)
                .preload();
    }

    /**
     * 通过URL得到Bitmap对象
     *
     * @param context
     * @param url
     * @param listener
     */
    public static void getBitmap(Context context, String url, final IGetBitmapListener listener) {
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .into(new BitmapTarget() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (listener != null) {
                            listener.onBitmap(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        if (listener != null) {
                            listener.onFailed();
                        }
                    }

                });
    }

    /**
     * 通过URL得到Drawable对象
     *
     * @param context
     * @param url
     * @param listener
     */
    public static void getDrawable(Context context, String url, final IGetDrawableListener listener) {
        GlideApp.with(context)
                .asDrawable()
                .load(url)
                .into(new DrawableTarget() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (listener != null) {
                            listener.onDrawable(resource);
                        }
                    }


                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        if (listener != null) {
                            listener.onFailed();
                        }
                    }
                });
    }

    /***
     * 清理内存缓存 可以在UI主线程中进行
     * @param context
     */
    public static void clearMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                GlideApp.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 清理磁盘缓存 需要在子线程中执行
     * @param context
     */
    public static void clearDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GlideApp.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

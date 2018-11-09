# 前期调研
 [ImageLoader，Picasso，Glide，Fresco图片加载库对比](http://note.youdao.com/groupshare/?token=2740E9312CD7455CB9426DB3C55757E1&gid=12353342)
# 学习资料 
- [Glide教程](https://blog.csdn.net/column/details/15318.html)
- [Glide4.0官网文档](https://muyangmin.github.io/glide-docs-cn/)
- [Glide github](https://github.com/bumptech/glide)

---
# Glide v4的集成
### 1. Android SDK 要求：
```
    Min Sdk Version - 使用 Glide 需要 min SDK 版本 API 14 (Ice Cream Sandwich) 或更高。
    Compile Sdk Version - Glide 必须使用 API 27 (Oreo MR1) 或更高版本的 SDK 来编译。
    Support Library Version - Glide 使用的支持库版本为 27。
```

### 2. Gradle
```
    //Support Library 
    implementation 'com.android.support:appcompat-v7:27.1.1'
    //glide
    implementation 'com.github.bumptech.glide:glide:4.8.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
```
### 3. 权限
```
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
### 4.Proguard 
```
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public class * extends com.bumptech.glide.module.AppGlideModule
    -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }
    # for DexGuard only
    -keepresourcexmlelements manifest/application/meta-data@value=GlideModule
```


# 开始使用
## 1. 基本用法
#### （1）加载图片
```
    Glide.with(context) .load(url).into(imageView)
```
- ######   with()方法中传入的context会决定Glide加载图片的生命周期，如果传入的是Activity或者Fragment的实例，那么当这个Activity或Fragment被销毁的时候，Glide 会自动取消加载并回收资源。如果传入的是ApplicationContext，那么只有当应用程序被杀掉的时候，图片加载才会停止。当然也可以手动取消加载：Glide.with(context).clear(imageView);
- ###### load()用于指定待加载的图片资源。Glide支持加载各种各样的图片资源，包括网络图片（包括gif）、本地图片、应用资源、二进制流、Uri对象等等；
- ######  into()这个方法就很简单了，我们希望让图片显示在哪个ImageView上，把这个ImageView的实例传进去就可以了。当然，into()方法不仅仅是只能接收ImageView类型的参数，还支持很多更丰富的用法，不过那个属于高级技巧；

#### （2）占位符
-  ###### 占位符(Placeholder)：占位符是当请求正在执行时被展示的 Drawable 。当请求成功完成时，占位符会被请求到的资源替换。如果被请求的资源是从内存中加载出来的，那么占位符可能根本不会被显示。如果请求失败并且没有设置 error Drawable ，则占位符将被持续展示。类似地，如果请求的url/model为 null ，并且 error Drawable 和 fallback 都没有设置，那么占位符也会继续显示。

```
Glide.with(context).load(url).placeholder(R.drawable.placeholder).into(imageView）;
```
- ###### 错误符(Error)：1.在请求永久性失败时展示2.请求的url/model为 null ，且并没有设置后备回调符。
```
Glide.with(context).load(url).error(R.drawable.error).into(imageView）;
```
- ###### 后备回调符(Fallback)：在请求的url/model为 null 时展示.
```
Glide.with(context).load(url).fallback(R.drawable.fallback).into(imageView）;
```
#### （3）选项使用 


```
request options 可以实现（包括但不限于）：
占位符(Placeholders)
转换(Transformations)
缓存策略(Caching Strategies)
组件特有的设置项，例如编码质量，或Bitmap的解码配置等
```
#####   以下是常用的一些选项
 - ###### 圆形图片
```
    RequestOptions options = new RequestOptions().circleCrop();
```
 - ###### 指定图片大小
```
    RequestOptions options = new RequestOptions().override(100, 100);
```

 - ###### Glide自动就是开启内存缓存的（true为关闭）
```
   RequestOptions options = new RequestOptions().skipMemoryCache(true);
```
 - ###### 硬盘缓存
 
######    1.DiskCacheStrategy.NONE： 表示不缓存任何内容。
######    2.DiskCacheStrategy.DATA： 表示只缓存原始图片。
######    3.DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
######    4.DiskCacheStrategy.ALL： 表示既缓存原始图片，也缓存转换过后的图片。
######    5.DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
```
   RequestOptions options = new RequestOptions() .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
```
######    最后选项放在  apply中
```
    Glide.with(context).load(url).apply(options).into(imageView);
```

#### （4）缩略图 (Thumbnail) 
 - ###### 缩略图1% （这个值可以修改）
```
    Glide.with(context).load(url).thumbnail(0.01F).into(imageView);
```
 - ###### 缩略图（地址）

```
 Glide.with(context).load(url)
                .thumbnail(Glide
                        .with(context)
                        .load(thumbnailUrl)
                        .thumbnail(0.1F))
                .into(imageView);
```


#### （5）预加载 

```
    Glide.with(context).load(url).preload();
```

#### （6）submit()
 - ###### asFile()返回 File （知道图片的下载地址）
 - ###### asBitmap()返回Bitmap 
 - ###### asDrawable()返回Drawable
 - ###### 注意1.target.get()必须也在子线程2.硬盘缓存策略：必須指定否則下載失敗

```
  public void downloadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://imgsrc.baidu.com/forum/w=580/sign=5729249949540923aa696376a259d1dc/ca816763f6246b60d6ab68ceebf81a4c500fa268.jpg";
                    final Context context = getApplicationContext();
                    final FutureTarget<File> target = Glide
                            .with(context)
                            .applyDefaultRequestOptions(new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))//硬盘缓存策略：必須指定否則下載失敗
                            .asFile()
                            .load(url)
                            .submit(100, 100);
                    final File imageFile = target.get();//耗时操作必须在子线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                            ImageLoader.displayImage(context,imageFile.getPath(),mImg);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

```
#### （7）非 View 目标下载的Bitmap、Drawable(比如微信分享用到)

```
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
```

#### （8）清理缓存

```
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
```

#### （9）自定义模块

 - ###### 自定义模块功能可以将更改Glide配置，替换Glide组件等操作独立出来，使得我们能轻松地对Glide的各种配置进行自定义，并且又和Glide的图片加载逻辑没有任何交集，这也是一种低耦合编程方式的体现。
 - ###### 4.0以上版本 加入了一个@GlideModule的注解，有了@GlideModule注解Glide才能够识别这个自定义模块。

```
/**
 * @author SummerSoft.L
 * @description glide 全局配置https://muyangmin.github.io/glide-docs-cn/doc/configuration.html
 * 
 */
@GlideModule
public class MyAppGlideModule extends com.bumptech.glide.module.AppGlideModule {
    /***
     *更改Glide配置
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.mipmap.ic_launcher)
                        .skipMemoryCache(false)); //Glide默认开启内存缓存。关掉内存缓存：true
    }

    /**
     * //替换Glide组件
     * @param context
     * @param glide
     * @param registry
     */
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

}
```



 

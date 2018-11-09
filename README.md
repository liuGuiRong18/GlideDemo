# GlideDemo
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

#### （1）占位符
###### 占位符(Placeholder)：占位符是当请求正在执行时被展示的 Drawable 。当请求成功完成时，占位符会被请求到的资源替换。如果被请求的资源是从内存中加载出来的，那么占位符可能根本不会被显示。如果请求失败并且没有设置 error Drawable ，则占位符将被持续展示。类似地，如果请求的url/model为 null ，并且 error Drawable 和 fallback 都没有设置，那么占位符也会继续显示。

```
Glide.with(context).load(url).placeholder(R.drawable.placeholder).into(view）;
```
###### 错误符(Error)：1.在请求永久性失败时展示2.请求的url/model为 null ，且并没有设置后备回调符。
```
Glide.with(context).load(url).error(R.drawable.error).into(view）;
```
###### 后备回调符(Fallback)：在请求的url/model为 null 时展示.
```
Glide.with(context).load(url).fallback(R.drawable.fallback).into(view）;
```













 

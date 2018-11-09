package com.android.summersoft.glidedemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.summersoft.glidedemo.glide.GlideApp;
import com.android.summersoft.glidedemo.glide.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final int IMAGE_TYPE_ZERO = 0;
    public static final int IMAGE_TYPE_ONE = 1;
    public static final int IMAGE_TYPE_TWO = 2;
    public static final int IMAGE_TYPE_THREE = 3;
    public static final int IMAGE_TYPE_FOUR = 4;
    public static final int IMAGE_TYPE_FIVE = 5;
    public static final int IMAGE_TYPE_SIX = 6;
    public static final int IMAGE_TYPE_SEVEN = 7;
    public static final int IMAGE_TYPE_EIGHT = 8;
    public static final int IMAGE_TYPE_NINE = 9;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImg = findViewById(R.id.img_load);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_ZERO));
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_ONE));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_TWO));
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_THREE));
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_FOUR));
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_FIVE));
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_SIX));
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_SEVEN));
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_EIGHT));
            }
        });
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class).putExtra("type", IMAGE_TYPE_NINE));
            }
        });
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PreLoadImageActivity.class));
            }
        });
        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.clearMemoryCache(MainActivity.this);
            }
        });
        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.clearDiskCache(MainActivity.this);
            }
        });
//        downloadImage2();
    }

    /**
     * submit 下载图片
     */
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
                    final File imageFile = target.get();
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

    public void downloadImage2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://imgsrc.baidu.com/forum/w=580/sign=5729249949540923aa696376a259d1dc/ca816763f6246b60d6ab68ceebf81a4c500fa268.jpg";
                    final Context context = getApplicationContext();
                    final FutureTarget<Bitmap> target = Glide
                            .with(context)
                            .applyDefaultRequestOptions(new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                            .asBitmap()
                            .load(url)
                            .submit();//耗时操作必须在子线程
                    final Bitmap imageFile = target.get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mImg.setImageBitmap(imageFile);
                            //不使用时清除target
                            Glide.with(context).clear(target);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}

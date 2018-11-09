package com.android.summersoft.glidedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.android.summersoft.glidedemo.glide.ImageLoader;

public class PreLoadImageActivity extends AppCompatActivity {

    private ImageView mPreLoadImg;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPreLoadImg = findViewById(R.id.preload_img_one);
        mImg = findViewById(R.id.preload_img_two);
        //先清除内存的图片才看的出效果
        ImageLoader.clearMemoryCache(PreLoadImageActivity.this);
        ImageLoader.clearDiskCache(PreLoadImageActivity.this);
        //预加载
        ImageLoader.displayImageWithPreload(PreLoadImageActivity.this, "http://imgsrc.baidu.com/forum/w=580/sign=5729249949540923aa696376a259d1dc/ca816763f6246b60d6ab68ceebf81a4c500fa268.jpg");
        findViewById(R.id.preload_btn_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.displayImage(PreLoadImageActivity.this, "http://imgsrc.baidu.com/forum/w=580/sign=5729249949540923aa696376a259d1dc/ca816763f6246b60d6ab68ceebf81a4c500fa268.jpg", mPreLoadImg);
            }
        });
        findViewById(R.id.preload_btn_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.displayImage(PreLoadImageActivity.this, "http://www.285868.com/uploadfile/2016/1027/20161027102235543.jpg", mImg);
            }
        });
    }
}

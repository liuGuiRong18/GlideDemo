package com.android.summersoft.glidedemo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.summersoft.glidedemo.glide.ImageLoader;
import com.android.summersoft.glidedemo.glide.listener.IGetBitmapListener;
import com.android.summersoft.glidedemo.glide.listener.IGetDrawableListener;

public class LoadImageActivity extends AppCompatActivity {

    private String imgUrl = "http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg";
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image_skip_cache);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mImg = findViewById(R.id.imageView3);
        int type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case MainActivity.IMAGE_TYPE_ZERO:
                ImageLoader.displayImage(LoadImageActivity.this,
                        "http://guolin.tech/test.gif", mImg);
                break;
            case MainActivity.IMAGE_TYPE_ONE:
                ImageLoader.displayImageSkipCache(LoadImageActivity.this,
                        imgUrl, mImg);
                break;
            case MainActivity.IMAGE_TYPE_TWO:
                ImageLoader.displayImageWithPlaceholder(LoadImageActivity.this,
                        imgUrl, mImg, R.mipmap.ic_launcher_round);
                break;
            case MainActivity.IMAGE_TYPE_THREE:
                ImageLoader.displayImageWithError(LoadImageActivity.this,
                        "", mImg, R.drawable.eror);
                break;
            case MainActivity.IMAGE_TYPE_FOUR:
                ImageLoader.displayImageWithFallback(LoadImageActivity.this,
                        null, mImg, R.drawable.fallback);
                break;
            case MainActivity.IMAGE_TYPE_FIVE:
                ImageLoader.displayImageWithCircleCrop(LoadImageActivity.this,
                        imgUrl, mImg);
                break;
            case MainActivity.IMAGE_TYPE_SIX:
                //下载成功
                ImageLoader.getBitmap(LoadImageActivity.this, imgUrl, new IGetBitmapListener() {
                    @Override
                    public void onBitmap(Bitmap bitmap) {
                        mImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailed() {
                        Toast.makeText(LoadImageActivity.this, "下载失败", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case MainActivity.IMAGE_TYPE_SEVEN:
                ImageLoader.getDrawable(LoadImageActivity.this,
                        imgUrl, new IGetDrawableListener() {
                            @Override
                            public void onDrawable(Drawable drawable) {
                                mImg.setImageDrawable(drawable);
                            }

                            @Override
                            public void onFailed() {
                                Toast.makeText(LoadImageActivity.this, "下载失败", Toast.LENGTH_LONG).show();
                            }
                        });
                break;
            case MainActivity.IMAGE_TYPE_EIGHT:
                ImageLoader.displayImageWithThumbnail(LoadImageActivity.this, imgUrl, mImg);
                break;
            case MainActivity.IMAGE_TYPE_NINE:
                ImageLoader.displayImageWithThumbnailUrl(LoadImageActivity.this, "http://www.285868.com/uploadfile/2016/1027/20161027102235543.jpg", mImg, "http://imgsrc.baidu.com/forum/w=580/sign=5729249949540923aa696376a259d1dc/ca816763f6246b60d6ab68ceebf81a4c500fa268.jpg");
                break;
        }


    }

}

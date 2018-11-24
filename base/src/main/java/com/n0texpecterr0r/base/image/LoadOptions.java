package com.n0texpecterr0r.base.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import java.io.File;

/**
 * ImageEngine加载的配置类
 *
 * @author N0tExpectErr0r
 * @time 2018/11/2
 */
public class LoadOptions {
    public Context context;         // 上下文
    public int placeholderResId;    // 占位图资源id
    public int errorResId;          // 加载出错图资源id
    public Bitmap.Config config = Config.RGB_565;   // Bitmap::Config
    public int targetWidth;         // 目标宽度
    public int targetHeight;        // 目标尺寸
    public Drawable placeholder;    // 占位图
    public Drawable error;          // 加载出错图
    public ImageView targetView;     // 在targetView上展示图片
    public int drawableResId;       // 要展示的Drawable的id
    public String url;              // 要展示的url
    public File file;               // 要展示的File
    public Uri uri;                 // 要展示的Uri
    public boolean isMemoryCache = true;    // 是否内存缓存
    public boolean isDiskCache = true;      // 是否本地缓存

    public LoadOptions(Context context){
        this.context = context;
    }

    public LoadOptions load(String url){
        this.url = url;
        return this;
    }

    public LoadOptions load(File file){
        this.file = file;
        return this;
    }

    public LoadOptions load(int drawableResId){
        this.drawableResId = drawableResId;
        return this;
    }

    public LoadOptions load(Uri uri) {
        this.uri = uri;
        return this;
    }

    public void into(ImageView targetView){
        this.targetView = targetView;
        ImageLoader.getInstance().loadWithOptions(this);
    }

    public LoadOptions placeholder(Drawable placeholder){
        this.placeholder = placeholder;
        return this;
    }

    public LoadOptions placeholder(@DrawableRes int placeholderResId){
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoadOptions error(@DrawableRes int errorResId){
        this.errorResId = errorResId;
        return this;
    }

    public LoadOptions error(Drawable error){
        this.error = error;
        return this;
    }

    public LoadOptions config(Bitmap.Config config){
        this.config = config;
        return this;
    }

    public LoadOptions resize(int targetWidth,int targetHeight){
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    public LoadOptions useMemoryCache(boolean isMemoryCache){
        this.isMemoryCache = isMemoryCache;
        return this;
    }

    public LoadOptions useDiskCache(boolean isDiskCache){
        this.isDiskCache = isDiskCache;
        return this;
    }
}

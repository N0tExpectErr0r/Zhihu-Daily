package com.n0texpecterr0r.base.image.engines;

import android.annotation.SuppressLint;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.n0texpecterr0r.base.image.ImageEngine;
import com.n0texpecterr0r.base.image.LoadOptions;

/**
 * 使用Glide实现的图片加载引擎
 * 继承自{@link ImageEngine}
 *
 * @author N0tExpectErr0r
 * @time 2018/11/2
 */
public class GlideEngine implements ImageEngine {

    @SuppressLint("CheckResult")
    @Override
    public void loadImage(LoadOptions options) {
        RequestOptions requestOptions = new RequestOptions();
        // resize图片
        if (options.targetWidth > 0 && options.targetHeight > 0)
            requestOptions.override(options.targetWidth, options.targetHeight);
        // 设置占位图
        if (options.placeholderResId != 0)
            requestOptions.placeholder(options.placeholderResId);
        else if (options.placeholder != null)
            requestOptions.placeholder(options.placeholder);
        // 设置错误图
        if (options.errorResId != 0)
            requestOptions.error(options.errorResId);
        else if (options.error != null)
            requestOptions.error(options.error);
        // 设置是否内存缓存
        requestOptions.skipMemoryCache(!options.isMemoryCache);
        // 设置是否本地缓存
        if (options.isDiskCache)
            requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        else
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        // 加载图片
        RequestBuilder builder = null;
        if (options.url != null)
            builder = Glide.with(options.context).load(options.url);
        if (options.file != null)
            builder = Glide.with(options.context).load(options.file);
        else if (options.drawableResId != 0)
            builder = Glide.with(options.context).load(options.drawableResId);
        else if (options.uri != null)
            builder = Glide.with(options.context).load(options.uri);
        // 判空
        if(builder != null)
            builder.apply(requestOptions).into(options.targetView);
    }
}

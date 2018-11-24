package com.n0texpecterr0r.base.image;

import android.content.Context;

/**
 * 单例的图片加载类，可以设置其加载引擎
 * 图片加载引擎需实现{@link ImageEngine}接口
 *
 * @author N0tExpectErr0r
 * @time 2018/11/2
 */
public class ImageLoader {

    private static ImageEngine sEngine;                 // 图片加载引擎
    private static volatile ImageLoader sInstance;      // 单例

    private ImageLoader() {
    }

    /**
     * 获取单例
     */
    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }

    /**
     * 设置图片加载引擎
     * @param engine 图片加载引擎
     */
    public void setEngine(ImageEngine engine) {
        if (engine != null) {
            sEngine = engine;
        }
    }

    public static LoadOptions with(Context context){
        return new LoadOptions(context);
    }


    public void loadWithOptions(LoadOptions options){
        sEngine.loadImage(options);
    }
}

package com.n0texpecterr0r.base.image;

/**
 * 图片加载引擎的接口
 *
 * @author N0tExpectErr0r
 * @time 2018/11/2
 */
public interface ImageEngine {

    /**
     * 加载图片
     * @param options 加载图片的配置
     */
    void loadImage(LoadOptions options);
}

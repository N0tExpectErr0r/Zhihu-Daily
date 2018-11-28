package com.n0texpecterr0r.base.app;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.n0texpecterr0r.base.image.ImageLoader;
import com.n0texpecterr0r.base.image.engines.GlideEngine;

/**
 * 知乎日报全局的Application
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public class DailyApplication extends Application {
    private static Context sContext;
    private boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if(sContext == null)
            sContext = getApplicationContext();
        initARouter();
        initImageLoader();
    }

    private void initImageLoader() {
        // 使用Glide引擎
        ImageLoader.getInstance().setEngine(new GlideEngine());
    }

    private void initARouter() {
        if (isDebug){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    public static Context getContext() {
        return sContext;
    }
}

package com.n0texpecterr0r.base.router;

import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 用于管理route到其他Activity的方式
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public class RouteActivity {

    private static void routeTo(String path){
        ARouter.getInstance().build(path).navigation();
    }
}

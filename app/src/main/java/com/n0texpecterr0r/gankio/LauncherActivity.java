package com.n0texpecterr0r.gankio;

import android.os.Bundle;

import com.n0texpecterr0r.base.component.activity.GankBaseActivity;

/**
 * 空白Activity，控制启动时候的跳转
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public class LauncherActivity extends GankBaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.app_activity_launcher;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}

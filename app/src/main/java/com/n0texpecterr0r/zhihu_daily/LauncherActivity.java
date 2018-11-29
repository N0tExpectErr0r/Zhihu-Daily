package com.n0texpecterr0r.zhihu_daily;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.n0texpecterr0r.base.component.activity.DailyBaseActivity;
import com.n0texpecterr0r.base.router.RouterConstant;

/**
 * 空白Activity，控制启动时候的跳转
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public class LauncherActivity extends DailyBaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.app_activity_launcher;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ARouter.getInstance().build(RouterConstant.ACTIVITY_HOME_MAIN).navigation();
        finish();
    }
}

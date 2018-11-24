package com.n0texpecterr0r.base.component.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * 简单的生命周期接口
 * Created by Albert on 18/5/19.
 */

public interface LifecycleSimpleObserver extends LifecycleObserver, LifecycleAttachObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy();

}

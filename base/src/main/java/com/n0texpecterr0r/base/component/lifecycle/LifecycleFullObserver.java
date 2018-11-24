package com.n0texpecterr0r.base.component.lifecycle;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import static android.arch.lifecycle.Lifecycle.Event.ON_ANY;
import static android.arch.lifecycle.Lifecycle.Event.ON_CREATE;
import static android.arch.lifecycle.Lifecycle.Event.ON_DESTROY;
import static android.arch.lifecycle.Lifecycle.Event.ON_PAUSE;
import static android.arch.lifecycle.Lifecycle.Event.ON_RESUME;
import static android.arch.lifecycle.Lifecycle.Event.ON_START;
import static android.arch.lifecycle.Lifecycle.Event.ON_STOP;

/**
 * 完整的生命周期接口
 *
 * Created by Albert on 18/5/19.
 */

public interface LifecycleFullObserver extends LifecycleObserver ,LifecycleAttachObserver {

    @OnLifecycleEvent(ON_CREATE)
    void onCreate();

    @OnLifecycleEvent(ON_START)
    void onStart();

    @OnLifecycleEvent(ON_RESUME)
    void onResume();

    @OnLifecycleEvent(ON_PAUSE)
    void onPause();

    @OnLifecycleEvent(ON_STOP)
    void onStop();

    @OnLifecycleEvent(ON_DESTROY)
    void onDestroy();

    @OnLifecycleEvent(ON_ANY)
    void onAny();
}


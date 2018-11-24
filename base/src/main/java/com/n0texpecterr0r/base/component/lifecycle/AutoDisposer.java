package com.n0texpecterr0r.base.component.lifecycle;

import android.arch.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * 简化 autoDispose 调用逻辑
 * Created by Albert on 18/7/6.
 */
public class AutoDisposer {

    public static <T> AutoDisposeConverter<T> bind(LifecycleOwner owner) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner));
    }
}

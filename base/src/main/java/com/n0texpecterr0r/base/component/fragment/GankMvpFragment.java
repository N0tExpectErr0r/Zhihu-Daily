package com.n0texpecterr0r.base.component.fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;

import com.n0texpecterr0r.base.component.mvp.BaseContract;

/**
 * GankIO的MvpFragment
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class GankMvpFragment<P extends BaseContract.Presenter> extends GankBaseFragment implements BaseContract.View{
    protected P mPresenter;
    private Lifecycle mLifecycle;

    @Override
    final protected void init(Bundle savedInstanceState) {
        mPresenter = onCreatePresenter();
        mLifecycle = new LifecycleRegistry(this);
        mLifecycle.addObserver(mPresenter);
        init(mPresenter, savedInstanceState);
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }

    abstract protected void init(P presenter, Bundle saveInstanceState);

    protected abstract P onCreatePresenter();

    public P getPresenter() {
        return mPresenter;
    }

}
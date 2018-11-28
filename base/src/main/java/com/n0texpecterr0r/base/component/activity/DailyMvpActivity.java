package com.n0texpecterr0r.base.component.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;

import com.n0texpecterr0r.base.component.mvp.BaseContract;

/**
 * 知乎日报的MvpActivity
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class DailyMvpActivity<P extends BaseContract.Presenter> extends DailyBaseActivity implements BaseContract.View{
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

    abstract protected void init(P presenter, Bundle savedInstanceState);

    protected abstract P onCreatePresenter();

    final public P getPresenter() {
        return mPresenter;
    }
}

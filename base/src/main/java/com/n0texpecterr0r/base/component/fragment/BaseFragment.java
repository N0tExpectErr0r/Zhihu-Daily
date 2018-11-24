package com.n0texpecterr0r.base.component.fragment;

import android.os.Bundle;

import com.n0texpecterr0r.base.util.HandlerUtil;
import com.n0texpecterr0r.base.util.ToastUtil;

/**
 * Fragment基类，所有Fragment均继承自它
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class BaseFragment extends LazyFragment{
    protected ToastUtil mToastUtil;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(getContentViewId());
        if(mToastUtil == null && getContext()!=null) mToastUtil = new ToastUtil(getContext());
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getContentViewId();

    protected void showToast(String msg){
        HandlerUtil.runOnUiThread(()->{
            if(mToastUtil == null && getContext()!=null)
                mToastUtil = new ToastUtil(getContext());
            mToastUtil.showToast(msg);
        });
    }
}

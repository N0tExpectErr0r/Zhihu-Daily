package com.n0texpecterr0r.base.component.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.n0texpecterr0r.base.component.activity.BaseActivity;

/**
 * GankIO中所有Fragment的基类
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
public abstract class GankBaseFragment extends BaseFragment{
    private AlertDialog mLoadingDialog;

    protected void showLoading(String msg){
        showLoading(msg, false);
    }

    protected void showLoading(String msg, boolean cancelable) {
        boolean isDestroy = false;
        if(getActivity()!=null)
            isDestroy = getActivity().isFinishing();
        if (mLoadingDialog == null && !isDestroy)
            mLoadingDialog = new ProgressDialog.Builder(getContext())
                    .setMessage(msg)
                    .setCancelable(cancelable)
                    .create();
            mLoadingDialog.show();
    }

    protected void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}

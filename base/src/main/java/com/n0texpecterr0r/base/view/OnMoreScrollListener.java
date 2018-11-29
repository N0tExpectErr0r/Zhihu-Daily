package com.n0texpecterr0r.base.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.n0texpecterr0r.base.adapter.BaseAdapter;

/**
 * 下拉加载更多OnScrollListener
 *
 * @author N0tExpectErr0r
 * @time 2018/11/23
 */
public abstract class OnMoreScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView mRecyclerView;

    protected OnMoreScrollListener(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
        BaseAdapter adapter = (BaseAdapter) mRecyclerView.getAdapter();

        if (null == manager) {
            throw new RuntimeException("you should call setLayoutManager() first!!");
        }
        if (manager instanceof LinearLayoutManager) {
            int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) manager)
                    .findLastCompletelyVisibleItemPosition();

            if (lastCompletelyVisibleItemPosition == adapter.getItemCount() - 1) {
                onLoadMore();
            }
        }
    }


    protected abstract void onLoadMore();
}

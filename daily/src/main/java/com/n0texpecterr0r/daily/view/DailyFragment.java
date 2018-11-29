package com.n0texpecterr0r.daily.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.n0texpecterr0r.base.component.fragment.DailyMvpFragment;
import com.n0texpecterr0r.base.router.RouterConstant;
import com.n0texpecterr0r.base.view.BannerView;
import com.n0texpecterr0r.base.view.OnMoreScrollListener;
import com.n0texpecterr0r.daily.adapter.DailyAdapter;
import com.n0texpecterr0r.daily.DailyContract;
import com.n0texpecterr0r.daily.bean.StoryBean;
import com.n0texpecterr0r.daily.bean.TopStoryBean;
import com.n0texpecterr0r.daily.presenter.DailyPresenter;
import com.n0texpecterr0r.daily.repository.DailyRepository;
import com.n0texpecterr0r.zhihu_daily.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 日报信息列表Fragment
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
@Route(path = RouterConstant.FRAGMENT_DAILY_LIST)
public class DailyFragment extends DailyMvpFragment<DailyPresenter> implements DailyContract.View{
    private RecyclerView mRvList;
    private SwipeRefreshLayout mSrlRefresh;
    private LinearLayout mSearchView;
    private BannerView mBannerView;
    private DailyAdapter mAdapter;

    @Override
    protected void init(DailyPresenter presenter, Bundle saveInstanceState) {
        mRvList = (RecyclerView) findViewById(R.id.daily_rv_list);
        mSrlRefresh = (SwipeRefreshLayout) findViewById(R.id.daily_srl_refresh);
        mSearchView = (LinearLayout) findViewById(R.id.daily_ll_search);
        presenter.refreshList();

        mSrlRefresh.setOnRefreshListener(presenter::refreshList);

        mAdapter = new DailyAdapter(new ArrayList<>(),R.layout.daily_item_daily,true);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mAdapter);
        mRvList.addOnScrollListener(new OnMoreScrollListener(mRvList) {
            @Override
            protected void onLoadMore() {
                presenter.loadMoreList();
            }
        });
    }

    @Override
    protected DailyPresenter onCreatePresenter() {
        return new DailyPresenter(getContext(),this,new DailyRepository());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.daily_fragment_daily;
    }

    @Override
    public void onLoadBannerFinish(List<TopStoryBean> topStories) {
        List<String> imgUrls = new ArrayList<>();
        for (TopStoryBean topStory : topStories) {
            imgUrls.add(topStory.getImage());
        }
        if (mBannerView == null){
            mBannerView = mAdapter.getBannerView();
        }
        mBannerView.setImageUrlList(imgUrls);
    }

    @Override
    public void onRefreshListFinish(List<StoryBean> stories) {
        mAdapter.setDatas(stories);
        mSrlRefresh.setRefreshing(false);
    }

    @Override
    public void onLoadMoreListFinish(List<StoryBean> stories) {
        mAdapter.addDatas(stories);
    }

    @Override
    public void onLoadError() {
        showToast("网络错误，请检查网络设置 ");
    }
}

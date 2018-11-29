package com.n0texpecterr0r.daily;


import android.content.Context;

import com.n0texpecterr0r.base.component.mvp.BaseContract;
import com.n0texpecterr0r.daily.bean.StoryBean;
import com.n0texpecterr0r.daily.bean.TopStoryBean;
import com.n0texpecterr0r.daily.repository.DailyRepository;

import java.util.List;

/**
 * 日报列表的Mvp契约类
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
public interface DailyContract {
    interface View extends BaseContract.View{
        void onLoadBannerFinish(List<TopStoryBean> topStories);

        void onRefreshListFinish(List<StoryBean> stories);

        void onLoadMoreListFinish(List<StoryBean> stories);

        void onLoadError();
    }

    abstract class Presenter extends BaseContract.RepoPresenter<View, DailyRepository>{

        public Presenter(Context context, View view, DailyRepository repository) {
            super(context, view, repository);
        }

        public abstract void refreshList();

        public abstract void loadMoreList();
    }
}

package com.n0texpecterr0r.daily.presenter;

import android.content.Context;

import com.n0texpecterr0r.daily.DailyContract;
import com.n0texpecterr0r.daily.repository.DailyRepository;
import com.n0texpecterr0r.daily.view.DailyFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 日报列表的Presenter层
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
public class DailyPresenter extends DailyContract.Presenter {
    private String date;

    public DailyPresenter(Context context, DailyContract.View view, DailyRepository repository) {
        super(context, view, repository);
    }

    @Override
    public void refreshList() {
        getRepository().refreshStory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(storyData -> {
                    date = storyData.getDate();
                    getView().onLoadBannerFinish(storyData.getTopList());
                    getView().onRefreshListFinish(storyData.getStoryList());
                }, throwable ->  {
                    getView().onLoadError();
                    throwable.printStackTrace();
                });
    }

    @Override
    public void loadMoreList() {
        if(date == null){
            Date tmp = new Date();
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            date = format.format(tmp);
        }
        getRepository().loadMoreStory(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(storyData -> {
                    date = storyData.getDate();
                    getView().onLoadMoreListFinish(storyData.getStoryList());
                }, throwable ->  {
                    getView().onLoadError();
                    throwable.printStackTrace();
                });
    }
}

package com.n0texpecterr0r.daily.repository;

import android.annotation.SuppressLint;

import com.n0texpecterr0r.base.component.mvp.BaseContract;
import com.n0texpecterr0r.base.util.Gsoner;
import com.n0texpecterr0r.daily.bean.StoryData;

import java.util.List;

import javax.security.auth.Destroyable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 具体描述
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
public class DailyRepository implements BaseContract.Repository {
    public static final String LATEST_STORIES_URL = "https://news-at.zhihu.com/api/4/news/latest";

    public static final String DATE_STORIES_URL = "https://news-at.zhihu.com/api/4/news/before/";

    @SuppressLint("CheckResult")
    public Observable<StoryData> refreshStory(){
        String url = LATEST_STORIES_URL;
        return Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .get()
                        .url(url)
                        .build();
                Call call = client.newCall(request);
                emitter.onNext(call.execute());
            }
        }).map(new Function<Response, StoryData>() {
            @Override
            public StoryData apply(Response response) throws Exception {
                String json = response.body().string();
                StoryData data = Gsoner.fromJson(json, StoryData.class);
                return data;
            }
        });
    }

    public Observable<StoryData> loadMoreStory(String date){
        String url = DATE_STORIES_URL+date;
        return Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .get()
                        .url(url)
                        .build();
                Call call = client.newCall(request);
                emitter.onNext(call.execute());
            }
        }).map(new Function<Response, StoryData>() {
            @Override
            public StoryData apply(Response response) throws Exception {
                String json = response.body().string();
                StoryData data = Gsoner.fromJson(json, StoryData.class);
                return data;
            }
        });
    }

}

package com.n0texpecterr0r.base.util;

import com.n0texpecterr0r.base.rx.RxSchedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * 用RxJava2实现的定时器功能
 *
 * @author N0tExpectErr0r
 * @time 2018/11/3
 */
public class Timer {

    private List<Disposable> mDisposables;

    public Timer() {
        mDisposables = new ArrayList<>();
    }

    /**
     * 启动定时任务
     *
     * @param timerTime 定时时间
     * @param task 定时任务
     */
    public void startTimer(int timerTime, TimerTask task) {
        startTimer(timerTime, TimeUnit.MILLISECONDS, RxSchedulers.ui(), task);
    }

    /**
     * 启动定时任务
     *
     * @param timerTime 定时时间
     * @param scheduler 执行任务的线程
     * @param task 定时任务
     */
    public void startTimer(int timerTime, Scheduler scheduler, TimerTask task) {
        startTimer(timerTime, TimeUnit.MILLISECONDS, scheduler, task);
    }

    /**
     * 启动定时任务
     *
     * @param timerTime 定时时间
     * @param timeUnit 定时时间单元
     * @param task 定时任务
     */
    public void startTimer(int timerTime, TimeUnit timeUnit, TimerTask task) {
        startTimer(timerTime, timeUnit, RxSchedulers.ui(), task);
    }

    /**
     * 启动定时任务
     *
     * @param timerTime 定时时间
     * @param timeUnit 定时时间单元
     * @param scheduler 执行任务的线程
     * @param task 定时任务
     */
    public void startTimer(int timerTime, TimeUnit timeUnit, Scheduler scheduler, TimerTask task) {
        mDisposables.add(Observable.timer(timerTime, timeUnit)
                .observeOn(scheduler)
                .subscribe(aLong -> task.doAction()));
    }

    /**
     * 启动轮询任务
     *
     * @param intervalTime 轮询间隔时间
     * @param task 定时任务
     */
    public void startInterval(int intervalTime, TimerTask task) {
        startInterval(intervalTime, TimeUnit.MILLISECONDS, RxSchedulers.ui(), task);
    }

    /**
     * 启动轮询任务
     *
     * @param intervalTime 轮询间隔时间
     * @param scheduler 执行任务的线程
     * @param task 定时任务
     */
    public void startInterval(int intervalTime, Scheduler scheduler, TimerTask task) {
        startInterval(intervalTime, TimeUnit.MILLISECONDS, scheduler, task);
    }

    /**
     * 启动轮询任务
     *
     * @param intervalTime 轮询间隔时间
     * @param timeUnit 轮询间隔时间单元
     * @param task 定时任务
     */
    public void startInterval(int intervalTime, TimeUnit timeUnit, TimerTask task) {
        startInterval(intervalTime, timeUnit, RxSchedulers.ui(), task);
    }

    /**
     * 启动轮询任务
     *
     * @param intervalTime 轮询间隔时间
     * @param timeUnit 轮询间隔时间单元
     * @param scheduler 执行任务的线程
     * @param task 定时任务
     */
    public void startInterval(int intervalTime, TimeUnit timeUnit, Scheduler scheduler, TimerTask task) {
        mDisposables.add(Observable.interval(intervalTime, timeUnit)
                .observeOn(scheduler)
                .subscribe(aLong -> task.doAction()));
    }

    /**
     * 定时任务
     */
    public interface TimerTask {

        void doAction();
    }

    /**
     * 取消全部任务
     */
    public void cancelAll(){
        for (Disposable disposable : mDisposables) {
            disposable.dispose();
            mDisposables.remove(disposable);
            disposable = null;
        }
    }
}

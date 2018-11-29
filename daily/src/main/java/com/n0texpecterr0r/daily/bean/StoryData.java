package com.n0texpecterr0r.daily.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 日报列表的bean列表
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
public class StoryData {

    private String date;
    @SerializedName("stories")
    private List<StoryBean> storyList;
    @SerializedName("top_stories")
    private List<TopStoryBean> topList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoryBean> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<StoryBean> storyList) {
        this.storyList = storyList;
    }

    public List<TopStoryBean> getTopList() {
        return topList;
    }

    public void setTopList(List<TopStoryBean> topList) {
        this.topList = topList;
    }



}

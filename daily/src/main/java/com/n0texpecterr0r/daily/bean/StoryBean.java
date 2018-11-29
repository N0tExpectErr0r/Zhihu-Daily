package com.n0texpecterr0r.daily.bean;

import java.util.List;

/**
 * 知乎日报单个bean
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
public class StoryBean {
    private int id;
    private String title;
    private List<String> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
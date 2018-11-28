package com.n0texpecterr0r.base.bean;


import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by Hans on 16/7/16.
 * 通用的TabBean类 提高复用性{
 */
public class CommonTabBean implements CustomTabEntity {
    private int selectedIcon;
    private int unselectedIcon;
    private String title;

    public CommonTabBean(String title) {
        this.title = title;
    }

    public CommonTabBean(String title, int selectedIcon, int unselectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unselectedIcon = unselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectedIcon;
    }
}

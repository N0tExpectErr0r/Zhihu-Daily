package com.n0texpecterr0r.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.n0texpecterr0r.base.adapter.CommonPagerAdapter;
import com.n0texpecterr0r.base.bean.CommonTabBean;
import com.n0texpecterr0r.base.component.activity.DailyBaseActivity;
import com.n0texpecterr0r.base.router.RouterConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * HomeActivity
 *
 * @author N0tExpectErr0r
 * @time 2018/11/24
 */
@Route(path = RouterConstant.ACTIVITY_HOME_MAIN)
public class HomeMainActivity extends DailyBaseActivity {
    private ViewPager mVpContainer;
    private CommonTabLayout mTlTab;
    private Toolbar mTbTitle;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList;

    @Override
    protected int getContentViewId() {
        return R.layout.home_activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mVpContainer = findViewById(R.id.home_vp_container);
        mTlTab = findViewById(R.id.home_tl_bottom);
        mTbTitle = findViewById(R.id.home_tb_toolbar);
        mFragmentManager = getSupportFragmentManager();
        mFragmentList = getFragmentList();
        setSupportActionBar(mTbTitle);
        initViewPager();
        initTabItems();
    }

    private void initViewPager() {
        CommonPagerAdapter adapter = new CommonPagerAdapter(mFragmentManager, mFragmentList);
        mVpContainer.setAdapter(adapter);
        mVpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTlTab.setCurrentTab(position);
                this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mVpContainer.setCurrentItem(0);
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        return fragmentList;
    }

    private List<CommonTabBean> getTabBeanList() {
        List<CommonTabBean> beans = new ArrayList<>();
        beans.add(new CommonTabBean("首页", R.drawable.home_ic_home_select,R.drawable.home_ic_home_unselect));
        beans.add(new CommonTabBean("栏目", R.drawable.home_ic_category_select,R.drawable.home_ic_category_unselect));
        beans.add(new CommonTabBean("收藏", R.drawable.home_ic_star_select,R.drawable.home_ic_star_unselect));
        return beans;
    }

    private void initTabItems() {
        List<CommonTabBean> tabBeans = getTabBeanList();
        ArrayList<CustomTabEntity> tabEntityList = new ArrayList<>(tabBeans);
        mTlTab.setTabData(tabEntityList);
        mTlTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVpContainer.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}

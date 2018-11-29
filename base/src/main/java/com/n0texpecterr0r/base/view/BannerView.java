package com.n0texpecterr0r.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.n0texpecterr0r.base.R;
import com.n0texpecterr0r.base.image.ImageLoader;
import com.n0texpecterr0r.base.util.Timer;

import org.w3c.dom.Text;

import java.util.List;

import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;

/**
 * 轮播图控件
 *
 * @author N0tExpectErr0r
 * @time 2018/11/03
 */
public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    // 网络图片集合
    private List<String> mImageUrlList;
    // 指示器Layout
    private LinearLayout indicatorLayout;
    // 当前位置
    private int currentPosition;
    // 轮播时间
    private int autoPlayTime = 2000;
    // 是否自动播放
    private boolean isAutoPlay;
    // 是否是单图片
    private boolean isOneImage;
    //监听事件
    private BannerItemClickListener mBannerItemClickListener;
    // 定时轮询任务的Timer
    private Timer mPlayTimer;

    public BannerView(@NonNull Context context) {
        this(context, null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 默认自动播放
        isAutoPlay = true;
        autoPlayTime = 2000;

        viewPager = new ViewPager(getContext());
        indicatorLayout = new LinearLayout(getContext());
        mPlayTimer = new Timer();
        // 添加监听事件
        viewPager.addOnPageChangeListener(this);
        // 将指示器放置底部并居中
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.bottomMargin = 10;

        addView(viewPager);
        addView(indicatorLayout, params);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 判断是否自动播放和是否是一张图片的情况
        if (isAutoPlay && !isOneImage) {
            mPlayTimer.startInterval(autoPlayTime, () -> {
                if(mImageUrlList!=null) {
                    currentPosition++;
                    currentPosition = currentPosition % (mImageUrlList.size() + 2);
                    viewPager.setCurrentItem(currentPosition);
                }
            });
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPlayTimer.cancelAll();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 按下Banner时Banner不滚动
        switch (ev.getAction()){
            case ACTION_DOWN:
                mPlayTimer.cancelAll();
                break;
            case ACTION_CANCEL:
            case ACTION_UP:
                mPlayTimer.startInterval(autoPlayTime, () -> {
                    currentPosition++;
                    currentPosition = currentPosition % (mImageUrlList.size() + 2);
                    viewPager.setCurrentItem(currentPosition);
                });
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        if (!isOneImage) {
            switchIndicatorTo(caculatePosition(position));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 根据滑动松开后的状态，去判断当前的current 并跳转到指定current
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            int currentPosition = viewPager.getCurrentItem();
            int lastPosition = 0;
            if (viewPager.getAdapter() != null) {
                lastPosition = viewPager.getAdapter().getCount() - 2;
            }
            if (currentPosition == 0) {
                viewPager.setCurrentItem(lastPosition, false);
            } else if (currentPosition == lastPosition + 1) {
                viewPager.setCurrentItem(1, false);
            }
        }
    }

    /**
     * ViewPager的Adapter
     */
    private class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageUrlList.size() + 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setOnClickListener(view -> {
                if (mBannerItemClickListener != null) {
                    mBannerItemClickListener.onBannerItemClick(position);
                }
            });
            ImageLoader.with(getContext())
                    .load(mImageUrlList.get(caculatePosition(position)))
                    .placeholder(R.drawable.base_img_loading)
                    .error(R.drawable.base_img_loading)
                    .into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 设置图片地址
     *
     * @param imageUrlList 图片地址
     */
    public void setImageUrlList(List<String> imageUrlList) {
        this.mImageUrlList = imageUrlList;
        if (imageUrlList.size() <= 1) {
            isOneImage = true;
        } else {
            isOneImage = false;
        }
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        if (!isOneImage) {
            // 添加指示器
            showIndicator();
        }
        BannerAdapter adapter = new BannerAdapter();
        viewPager.setAdapter(adapter);
        // 默认当前图片
        viewPager.setCurrentItem(1);
    }

    /**
     * 添加指示点
     */
    private void showIndicator() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(40, 40);
        indicatorLayout.removeAllViews();
        lp.setMargins(1, 2, 1, 2);
        ImageView imageView;
        int size = mImageUrlList.size();
        for (int i = 0; i < size; i++) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(lp);
            imageView.setImageResource(R.drawable.base_ic_select_point);
            indicatorLayout.addView(imageView);
        }
        switchIndicatorTo(0);
    }

    /**
     * 切换指示器点
     *
     * @param currentPosition 当前的位置
     */
    private void switchIndicatorTo(int currentPosition) {
        for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
            ImageView point = (ImageView) indicatorLayout.getChildAt(i);
            point.setColorFilter(Color.WHITE);
        }
        ImageView currentPoint = (ImageView) indicatorLayout.getChildAt(currentPosition);
        currentPoint.setColorFilter(null);
    }

    /**
     * 计算真实位置
     *
     * @param position 计算的位置
     * @return 计算后的真实位置
     */
    private int caculatePosition(int position) {
        int realPosition;
        if (mImageUrlList.size() > 0) {
            realPosition = (position - 1) % mImageUrlList.size();
            if (realPosition < 0) {
                realPosition += mImageUrlList.size();
            }
        } else {
            realPosition = 0;
        }
        return realPosition;
    }

    /**
     * 设置是否自动轮播
     *
     * @param autoPlay 是否自动轮播
     */
    public void setAutoPlay(boolean autoPlay) {
        isAutoPlay = autoPlay;
    }

    /**
     * Banner的Item点击事件
     *
     * @param bannerItemClickListener 点击事件
     */
    public void setBannerItemClickListener(BannerItemClickListener bannerItemClickListener) {
        this.mBannerItemClickListener = bannerItemClickListener;
    }

    public interface BannerItemClickListener {

        void onBannerItemClick(int position);
    }

    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

}


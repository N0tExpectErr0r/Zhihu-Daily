package com.n0texpecterr0r.daily.adapter;

import android.widget.ImageView;

import com.n0texpecterr0r.base.app.DailyApplication;
import com.n0texpecterr0r.base.image.ImageLoader;
import com.n0texpecterr0r.base.adapter.BaseAdapter;
import com.n0texpecterr0r.base.adapter.BaseViewHolder;
import com.n0texpecterr0r.daily.bean.StoryBean;
import com.n0texpecterr0r.zhihu_daily.R;

import java.util.List;

/**
 * 日报列表Adapter
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
public class DailyAdapter extends BaseAdapter<StoryBean> {
    public DailyAdapter(List<StoryBean> data, int itemLayoutId, boolean hasBanner) {
        super(data, itemLayoutId,hasBanner);
    }

    @Override
    public void initItemView(BaseViewHolder holder, StoryBean storyBean) {
        holder.setText(R.id.daily_item_tv_title, storyBean.getTitle());
        ImageView imageView = holder.getView(R.id.daily_item_iv_image);
        String imgUrl = storyBean.getImages().get(0);
        if (imgUrl != null) {
            ImageLoader.with(DailyApplication.getContext())
                    .load(imgUrl)
                    .placeholder(R.drawable.base_img_loading)
                    .error(R.drawable.base_img_loading)
                    .into(imageView);
        }else{
            imageView.setImageResource(R.drawable.base_img_loading);
        }
    }
}

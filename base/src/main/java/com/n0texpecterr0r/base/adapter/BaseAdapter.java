package com.n0texpecterr0r.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.n0texpecterr0r.base.R;
import com.n0texpecterr0r.base.view.BannerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用Adapter
 *
 * @author N0tExpectErr0r
 * @time 2018/11/28
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter implements View.OnClickListener {
    private static final int TYPE_BANNER = 0x221;
    private static final int TYPE_OTHER = 0x222;
    private static final int TYPE_BOTTOM = 0x223;
    private List<T> mDatas;
    private int mItemLayoutId;
    private OnItemClickListener mOnItemClickListener = null;
    private BannerView mBannerView;
    private boolean mHasBanner = false;
    private boolean mHasMore = true;

    public BaseAdapter(List<T> data, int itemLayoutId, boolean hasBanner) {
        mDatas = data;
        mItemLayoutId = itemLayoutId;
        mHasBanner = hasBanner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_BOTTOM == viewType) {
            // 底部布局
            return new BaseViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.base_foot_loading, parent, false));
        }else if(TYPE_BANNER == viewType){
            BaseViewHolder viewHolder =  new BaseViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.base_layout_banner, parent, false));
            mBannerView = viewHolder.getView(R.id.base_bv_banner);
            return viewHolder;
        } else {
            View view = LayoutInflater
                    .from(parent.getContext()).inflate(mItemLayoutId, parent, false);
            view.setOnClickListener(this);
            return new BaseViewHolder(view);
        }
    }

    public BannerView getBannerView(){
        return mBannerView;
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<T> datas){
        if(datas.size()<=0)
            mHasMore = false;
        int start = mDatas.size();
        int count = datas.size();
        mDatas.addAll(datas);
        notifyItemRangeChanged(start,count);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (getItemViewType(position) != TYPE_BOTTOM
                && getItemViewType(position) != TYPE_BANNER && !mDatas.isEmpty()) {
            int realPos = position;
            if (mHasBanner)
                realPos--;
            T data = mDatas.get(realPos);
            BaseViewHolder holder = (BaseViewHolder) viewHolder;
            holder.itemView.setTag(realPos );
            initItemView(holder, data);
        }
        // 对相应的onBindViewHolder进行处理
    }

    // 需要在继承的类中实现，初始化item布局的操作
    public abstract void initItemView(BaseViewHolder holder, T t);

    @Override
    public int getItemCount() {
        int count = mDatas.size();
        if(mHasMore && !mDatas.isEmpty())
            count++;
        if(mHasBanner)
            count++;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int realPos = position;
        if (mHasBanner) {
            realPos--;
        }
        if(mHasBanner && realPos == -1){
            return TYPE_BANNER;
        } else if (!mDatas.isEmpty() && mDatas.size() <= realPos) {
            return TYPE_BOTTOM;
        } else {
            return TYPE_OTHER;
        }
    }

    public boolean hasMore() {
        return mHasMore;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            // 注意这里使用getTag方法获取position
            int position = (int) v.getTag();
            if(position != 0) {
                mOnItemClickListener.onItemClick(v, position);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    // 点击事件接口
    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }
}

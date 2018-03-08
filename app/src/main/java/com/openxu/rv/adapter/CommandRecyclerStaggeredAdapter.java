package com.openxu.rv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * autour : openXu
 * date : 2017/9/7 19:05
 * className : CommandRecyclerAdapter
 * version : 1.0
 * description : 通用的CommandRecyclerAdapter
 */
public abstract class CommandRecyclerStaggeredAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected List<Integer> mLayoutIds;
    protected LayoutInflater mInflater;

    public CommandRecyclerStaggeredAdapter(Context context, List<Integer> layoutIds, List<T> datas) {
        mDatas = new ArrayList<>();
        this.mLayoutIds = new ArrayList<>();
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutIds.addAll(layoutIds);
        if(datas!=null)
            mDatas.addAll(datas);
    }

    /**Adapter最重要的三个方法getItemCount()、onCreateViewHolder()、onBindViewHolder()*/
    @Override
    public int getItemCount(){
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        // 瀑布流样式外部设置spanCount为2，在这列设置两个不同的item type，以区分不同的布局
        return position % 2;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType){
        ViewHolder viewHolder = ViewHolder.get(mContext, parent, mLayoutIds.get(viewType));
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        convert(holder, mDatas.get(position));
        /**为item设置点击和长点击事件*/
        holder.setOnClickListener(-1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(mDatas.get(position), position);
            }
        });
        holder.setOnLongClickListener(-1, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemLongClick(mDatas.get(position), position);
            }
        });
    }

    /**数据设置、添加、删除相关方法*/
    public void setData(List<T> datas){
        mDatas.clear();
        if(datas!=null)
            mDatas.addAll(datas);
        notifyDataSetChanged();
    }
    public void addData(int position,T t){
        if(null!=mDatas) {
            mDatas.add(position, t);
            notifyItemInserted(position);
            if (position != mDatas.size()) {
                //刷新改变位置item下方的所有Item的位置,避免索引错乱
                notifyItemRangeChanged(position, mDatas.size() - position);
            }
        }
    }
    public void addDatas(int position, List<T> datas){
        if(datas!=null) {
            mDatas.addAll(position, datas);
            notifyItemInserted(position);
            if (position != mDatas.size()) {
                notifyItemRangeChanged(position, mDatas.size() - position);
            }
        }
    }
    public void removeData(int position){
        if(null!=mDatas && mDatas.size()>position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
            if (position != mDatas.size()) {
                //刷新改变位置item下方的所有Item的位置,避免索引错乱
                notifyItemRangeChanged(position, mDatas.size() - position);
            }
        }
    }
    public void removeDatas(List<Integer> positions){
        if(null!=mDatas){
            for(int position:positions){
                if(mDatas.size()>position) {
                    mDatas.remove(position);
                    notifyItemRemoved(position);
                    if (position != mDatas.size()) {
                        //刷新改变位置item下方的所有Item的位置,避免索引错乱
                        notifyItemRangeChanged(position, mDatas.size() - position);
                    }
                }
            }
        }
    }

    /**重写此方法，将数据绑定到控件上*/
    public abstract void convert(ViewHolder holder, T t);
    /**item点击和长点击事件*/
    public abstract void onItemClick(T data, int position);
    public boolean onItemLongClick(T data, int position){
        return true;
    }
}
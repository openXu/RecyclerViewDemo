package com.openxu.rv.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * autour : openXu
 * date : 2017/9/7 19:04
 * className : ViewHolder
 * version : 1.0
 * description : 通用的ViewHolder
 *
 * ViewHolder主要作用是 引用item布局中控件对象
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;   //用于维护item中子控件的引用
    private View mConvertView;
    private Context mContext;

    public ViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**根据item布局id创建一个ViewHolder，Adapter中调用*/
    public static ViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(context, itemView, parent);
        return holder;
    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @return
     */
    private <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**为条目或者指定控件设置点击和长点击事件，在adapter中调用*/
    public ViewHolder setOnClickListener(int viewId,  View.OnClickListener listener) {
        if(viewId==-1){
            mConvertView.setOnClickListener(listener);
        }else{
            View view = getView(viewId);
            view.setOnClickListener(listener);
        }
        return this;
    }
    public ViewHolder setOnLongClickListener(int viewId,  View.OnLongClickListener listener) {
        if(viewId==-1){
            mConvertView.setOnLongClickListener(listener);
        }else{
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
        }
        return this;
    }

    /**下面的方法暴露出去，用于绑定数据，如果不够用可以扩充*/

    public ViewHolder setVisible(int viewId, int visible) {
        getView(viewId).setVisibility(visible);
        return this;
    }
    public ViewHolder setTextColor(int viewId, int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
    @SuppressLint("NewApi")
    public ViewHolder setBackgroundResource(int viewId, int id) {
        View view = getView(viewId);
        if(0==id)
            view.setBackground(null);
        else
            view.setBackgroundResource(id);
        return this;
    }
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }
    public ViewHolder setCheckBoxChecked(int viewId, boolean check) {
        CheckBox cb = getView(viewId);
        cb.setChecked(check);
        return this;
    }
    public ViewHolder setLinearLayoutBgIcon(int viewId, int  iconResourse) {
        LinearLayout ll = getView(viewId);
        ll.setBackgroundResource(iconResourse);
        return this;
    }

}
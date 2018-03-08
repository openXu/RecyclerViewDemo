package com.openxu.rv.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * autour : openXu
 * date : 2018/3/6 18:33
 * className : GridDividerDecoration
 * version : 1.0
 * description : 网格样式的item分隔样式定义
 */
public class GridDividerDecoration extends RecyclerView.ItemDecoration {

    private static int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;

    public GridDividerDecoration(Context context) {
        // 获取默认主题的属性
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    /**
     * 由于grid网格特性，需要相邻的item之间隔开，所以只需要在右下方留间隔
     * 但是特殊item需要特殊对待，比如最后一列、最后一行的情况
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        int orientation = ((GridLayoutManager)parent.getLayoutManager()).getOrientation();
        int position = parent.getChildLayoutPosition(view);
        boolean rightSpace = true;   //是否在右侧留间隔
        boolean bottomSpace = true;  //是否在下方留间隔
        if(orientation == OrientationHelper.VERTICAL){   //竖直排列的情况
            if ((position + 1) % spanCount == 0)
                rightSpace = false;   //最后一列只需要bottom方向留间隔
            else { //前面的列在right和bottom方向留间隔
            }
        }else if(orientation == OrientationHelper.HORIZONTAL){   //水平排列的情况
            if ((position + 1) % spanCount == 0)
                bottomSpace = false;  //最后一排只需要右边留间隔
            else {
                //前面的排在right和bottom方向留间隔
            }
        }
        outRect.set(0, 0, rightSpace?mDivider.getIntrinsicWidth() : 0, bottomSpace?mDivider.getIntrinsicHeight() : 0);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        // 绘制间隔，每一个item，绘制右边和下方间隔样式
        int childCount = parent.getChildCount();  //注意childCount是一屏显示的总数，而不是数据的总数
        int spanCount = ((GridLayoutManager)parent.getLayoutManager()).getSpanCount();
        int orientation = ((GridLayoutManager)parent.getLayoutManager()).getOrientation();
        boolean drawRight;
        boolean drawBottom;
        for(int i = 0; i < childCount; i++) {
            drawRight = drawBottom = true;
            if(orientation == OrientationHelper.VERTICAL){   //竖直排列的情况
                if ((i + 1) % spanCount == 0)
                    drawRight = false;   //最后一列只需要bottom方向留间隔
                else { //前面的列在right和bottom方向留间隔
                }
            }else if(orientation == OrientationHelper.HORIZONTAL){   //水平排列的情况
                if ((i + 1) % spanCount == 0)
                    drawBottom = false;  //最后一排只需要右边留间隔
                else {
                    //前面的排在right和bottom方向留间隔
                }
            }

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if(drawRight) {
                int left = child.getRight() + params.rightMargin;
                int top = child.getTop() - params.topMargin;
                int right = left + mDivider.getIntrinsicWidth();
                int bottom = child.getBottom() + params.bottomMargin + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if(drawBottom){
                int left = child.getLeft() - params.leftMargin;
                int top = child.getBottom() + params.bottomMargin;
                int right = child.getRight() + params.rightMargin;
                int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            Log.w(getClass().getSimpleName(),
                    "第"+i+"个item "+"  drawRight="+drawRight+"  drawBottom="+drawBottom);
        }
    }



}
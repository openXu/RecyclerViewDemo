package com.openxu.rv.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.openxu.rv.R;

/**
 * autour : openXu
 * date : 2018/3/5 14:50
 * className : MyDividerItemDecoration
 * version : 1.0
 * description : RecyclerView间隔样式
 *
 * 继承RecyclerView.ItemDecoration类，该类是个抽象类，主要有三个方法：
 * 1、在Item绘制之前被调用，绘制内容在item下层
 *  onDraw(Canvas c, RecyclerView parent, State state)
 * 2、在Item绘制之后被调用，绘制内容覆盖在item之上
 *  onDrawOver(Canvas c, RecyclerView parent, State state)
 * 3、设置item的偏移量，偏移的部分用于填充间隔样式，在RecyclerView的onMesure()中会调用该方法
 *  getItemOffsets(Rect outRect, View view, RecyclerView parent, State state)
 *
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    /**用于绘制间隔样式*/
    private Drawable mDivider;
    /**列表的方向，水平/竖直 */
    private int mOrientation;

    private Paint paint;
    private Bitmap bitmap;

    public MyDividerItemDecoration(Context context, int orientation) {
        // 获取默认主题的属性
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);

        paint = new Paint();
        paint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(20, 40, 60, 80);
        } else {
            outRect.set(20, 40, 60, 80);
        }
    }
    /**onDraw方法会在item绘制之前调用，这里用来绘制item的间隔，可能会被item遮挡 */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            //获取分隔线左右坐标
            final int left = parent.getPaddingLeft();   //RecyclerView的左padding值
            final int right = parent.getWidth() - parent.getPaddingRight();  //RecyclerView减去right padding值后右边坐标
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                //分割线绘制的top位置=item底端+item底部margin值+item偏移量 - 20
                final int top = child.getBottom() + params.bottomMargin +
                        Math.round(ViewCompat.getTranslationY(child))-20;
                //分割线底部 = top + 20的overdraw + 底部80偏移值
                final int bottom = top + 20 + 80;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        } else {
           //此处绘制水平方向排列时的间隔样式，省略
        }
    }
    /**onDrawOver方法会在item绘制之后调用，这层绘制将会显示在最上层 */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                //在item的头部画一只猫
                c.drawBitmap(bitmap, new Rect(0, 0,bitmap.getWidth(), bitmap.getHeight()),
                        new Rect(child.getLeft()+20, child.getTop(),
                                child.getLeft()+20+(child.getBottom()-child.getTop()),
                                child.getBottom()),paint);
            }
        } else {
            //此处绘制水平方向排列时的遮罩，省略
        }
    }
}

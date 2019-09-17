package com.jiyun.qqkandianview;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;


public class MyDragGridLayout extends GridLayout implements View.OnLongClickListener, View.OnDragListener, View.OnClickListener {
    private OnItemClickListenner mItemClickListener;

    public void setmItemClickListener(OnItemClickListenner mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public int margin = 10;
    public int colument = 4;
    private boolean mIsDragable;
    private View mDragview;
    private Rect[] mRect;


    public MyDragGridLayout(Context context) {
        super(context, null);
    }

    public MyDragGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    private void init() {
        setColumnCount(colument);//设置一行多少列

        setLayoutTransition(new LayoutTransition());
    }


    public MyDragGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    //在Activity中调用此方法传入集合
    public void setItems(List<String> list) {
        for (String str : list) {//将集合遍历
            addItems(str);//添加显示
        }
    }


    //接口回调


    //将内容添加到TextView
    public void addItems(String str) {


        TextView tv = new TextView(getContext());
        tv.setText(str);
        tv.setTextSize(20);//设置字体大小
        tv.setBackgroundResource(R.drawable.tv_bg);//设置一个小框框
        tv.setPadding(margin, margin, margin, margin);//设置内边距
        tv.setGravity(Gravity.CENTER);//设置居中


        //设置外边距
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.setMargins(margin, margin, margin, margin);//设置外边距   margin 是边距大小
        params.width = getResources().getDisplayMetrics().widthPixels / colument - margin * 2;//设置内容平分屏幕显示
        tv.setLayoutParams(params);
        addView(tv);


        //长安拖拽
        if (mIsDragable) {
            tv.setOnLongClickListener(this);
        } else {
            tv.setOnLongClickListener(null);
        }


        //拖拽
        if (mIsDragable) {
            setOnDragListener(this);
        } else {
            setOnDragListener(null);
        }


        //点击事件监听
        tv.setOnClickListener(this);
    }


    //设置能否被拖拽
    public void setIsDragAble(boolean isDragable) {
        this.mIsDragable = isDragable;
    }


    @Override
    public boolean onLongClick(View view) {
        this.mDragview = view;
        //第二个参数是拖拽的阴影
        view.startDrag(null, new DragShadowBuilder(view), null, 0);
        return false;
    }



    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_ENDED://结束
                break;

            case DragEvent.ACTION_DRAG_ENTERED://进入
                break;


            case DragEvent.ACTION_DRAG_EXITED://退出
                break;


            //拖拽的那个点是否达到某个矩形的范围内，如果达到，就进行换位
            case DragEvent.ACTION_DRAG_LOCATION://位置
                //获取拖拽到的矩形对应的索引值
                int index = getItemIndex(dragEvent);
                if (index >= 0 && mDragview != null && mDragview != getChildAt(index)) {//判断
                    removeView(mDragview);//删除
                    addView(mDragview, index);//添加
                }
                break;

            case DragEvent.ACTION_DRAG_STARTED://启动
                initRect();
                break;

            case DragEvent.ACTION_DROP://落下
                break;
        }
        return true;
    }



    private int getItemIndex(DragEvent dragEvent) {
        int index = -1;
        for (int i = 0; i < mRect.length; i++) {
            int x = (int) dragEvent.getX();
            int y = (int) dragEvent.getY();
            if (mRect[i].contains(x, y)) {
                return i;
            }
        }
        return index;
    }




    //将所有的条目都用矩形的框框起来
    private void initRect() {
        int childCount = this.getChildCount();//获取当前类的item个数
        mRect = new Rect[childCount];
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Rect rect = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            mRect[i] = rect;
        }
    }




    @Override
    public void onClick(View view) {

        if (mIsDragable) {
            //上半部分的点击事件要传递的点击的那个View
            if (mItemClickListener != null) {
                mItemClickListener.getDragableItemView(view);
            }
        } else {
            //下半部分的点击事件要传递的点击的那个View
            if (mItemClickListener != null) {
                mItemClickListener.getDisDragableItemView(view);
            }
        }
    }
}

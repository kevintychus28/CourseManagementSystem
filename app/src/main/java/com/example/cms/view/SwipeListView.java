package com.example.cms.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SwipeListView extends ListView {
    private final static String TAG = "Log日志";
    private int mScreenWidth;  // 屏幕宽度
    private int mDownX;      // 按下点的x值
    private int mDownY;      // 按下点的y值
    private int mBtnWidth;// 编辑和删除按钮的宽度
    private boolean isBtnShown = false;  // 编辑和删除按钮是否正在显示
    private boolean isOnClick = false;
    private ViewGroup mPointChild;  // 当前处理的item
    private LinearLayout.LayoutParams mLayoutParams;  // 当前处理的item的LayoutParams

    public SwipeListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 获取屏幕宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                performActionDown(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                return performActionMove(ev);
            case MotionEvent.ACTION_UP:
                return performActionUp(ev);
//        break;
        }
        return super.onTouchEvent(ev);
    }

    // 处理action_down事件
    private void performActionDown(MotionEvent ev) {
//    Log.e(TAG,"performActionDown===="+isDeleteShown);
        if (isBtnShown) {
            turnToNormal();
        }
        isOnClick = true;
        mDownX = (int) ev.getX();
        mDownY = (int) ev.getY();

        // 获取当前点的item
        int downPosition = pointToPosition(mDownX, mDownY);
        int firstPosition = getFirstVisiblePosition();
        Log.e(TAG, "performActionDown====downPosition:" + downPosition + "==firstPosition" + firstPosition);
        if (downPosition < 0) return;
        mPointChild = (ViewGroup) getChildAt(downPosition - firstPosition);

        // 获取编辑和删除按钮的宽度
        mBtnWidth = mPointChild.getChildAt(1).getLayoutParams().width + mPointChild.getChildAt(2).getLayoutParams().width;

        // 笔记文本控件的参数
        mLayoutParams = (LinearLayout.LayoutParams) mPointChild.getChildAt(0).getLayoutParams();

        // 重新设置layout_width等于屏幕宽度
        // 因为match_parent时，不管怎么滑，都不会显示删除按钮，ViewGroup就不去布局剩下的view
        mLayoutParams.width = mScreenWidth;
        mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
    }

    // 处理action_move事件
    private boolean performActionMove(MotionEvent ev) {
//    Log.e(TAG, "performActionMove====" + isDeleteShown);
        int nowX = (int) ev.getX();
        int nowY = (int) ev.getY();
        isOnClick = false;
        if (Math.abs(nowX - mDownX) > Math.abs(nowY - mDownY)) {
            // 如果向左滑动
            if (nowX < mDownX) {
                // 计算要偏移的距离
                int scroll = (nowX - mDownX) / 2;
                // 如果大于了编辑加删除按钮的宽度， 则最大为编辑加删除按钮的宽度
                if (-scroll >= mBtnWidth) {
                    scroll = -mBtnWidth;
                }
                // 重新设置leftMargin
                mLayoutParams.leftMargin = scroll;
                mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    // 处理action_up事件
    private boolean performActionUp(MotionEvent ev) {
        boolean falg = false;
        if (isOnClick && !isBtnShown) {
            falg = true;
        }
        // 偏移量大于button的一半，则显示button
        // 否则恢复默认
        if (-mLayoutParams.leftMargin >= mBtnWidth / 2) {
            mLayoutParams.leftMargin = -mBtnWidth;
            isBtnShown = true;
        } else {
            turnToNormal();
            isBtnShown = false;
        }
        mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
//    Log.e(TAG, "performActionUp====" + isDeleteShown);
        if (falg) {
            return super.onTouchEvent(ev);
        }
        return true;
    }

    /**
     * 变为正常状态
     */
    public void turnToNormal() {
        mLayoutParams.leftMargin = 0;
        mPointChild.getChildAt(0).setLayoutParams(mLayoutParams);
    }

    /**
     * 当前是否可点击
     *
     * @return 是否可点击
     */
    public boolean canClick() {
        return !isBtnShown;
    }
}
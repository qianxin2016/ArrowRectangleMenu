package com.xinxin.arrowrectanglemenu.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class PopupMenu extends PopupWindow {
    private ViewGroup mContentView;
    private OnMenuItemSelectedListener mListener;

    private int mMenuItemBgColor = 0xFFFFFFFF;
    private int mMenuItemHoverBgColor = 0xFFEDECED;

    public interface OnMenuItemSelectedListener {
        public void onMenuItemSelected(View menuItem);
    }

    public PopupMenu(ViewGroup contentView) {
        super(contentView, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);

        mContentView = contentView;
        initializeMenuItems();
        // Android API bug: if version <=5.1, must set background otherwise can not be dismissed by touch or back
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener listener) {
        mListener = listener;
    }

    public void setMenuItemBackgroundColor(int color) {
        mMenuItemBgColor = color;
    }

    public void setMenuItemHoverBackgroundColor(int color) {
        mMenuItemHoverBgColor = color;
    }

    private void initializeMenuItems() {
        for (int i = 0; i < mContentView.getChildCount(); i++) {
            View child = mContentView.getChildAt(i);
            child.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            v.setBackgroundColor(mMenuItemHoverBgColor);
                            break;
                        case MotionEvent.ACTION_UP:
                            v.setBackgroundColor(mMenuItemBgColor);
                            dismiss();
                            if (mListener != null) {
                                mListener.onMenuItemSelected(v);
                            }
                            break;
                        default:
                            break;
                    }

                    return true;
                }
            });
        }
    }

    public void show(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
    }

    public void dismiss() {
        super.dismiss();
    }

    public boolean isShowing() {
        return super.isShowing();
    }
}

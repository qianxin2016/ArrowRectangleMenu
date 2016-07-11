package com.xinxin.arrowrectanglemenu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.arrowrectanglemenu.R;

/**
 * Created by qianxin on 2016/3/23.
 */
public class ArrowRectangleView extends ViewGroup {
    // arrow's width
    private int mArrowWidth = 40;
    // arrow's height
    private int mArrowHeight= 20;
    // rectangle round corner's radius
    private int mRadius = 18;
    // background color
    private int mBackgroundColor = Color.WHITE;
    // arrow's horizontal offset relative to RIGHT side
    private int mArrowOffset = 0;
    // shadow color
    private int mShadowColor = Color.BLACK;
    // shadow thickness
    private int mShadowThickness = 0;

    public ArrowRectangleView(Context context) {
        this(context, null);
    }

    public ArrowRectangleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowRectangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ArrowRectangleView, defStyleAttr, 0);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ArrowRectangleView_arrow_width:
                    mArrowWidth = a.getDimensionPixelSize(attr, mArrowWidth);
                    break;
                case R.styleable.ArrowRectangleView_arrow_height:
                    mArrowHeight = a.getDimensionPixelSize(attr, mArrowHeight);
                    break;
                case R.styleable.ArrowRectangleView_radius:
                    mRadius = a.getDimensionPixelSize(attr, mRadius);
                    break;
                case R.styleable.ArrowRectangleView_background_color:
                    mBackgroundColor = a.getColor(attr, mBackgroundColor);
                    break;
                case R.styleable.ArrowRectangleView_arrow_offset:
                    mArrowOffset = a.getDimensionPixelSize(attr, mArrowOffset);
                    break;
                case R.styleable.ArrowRectangleView_shadow_color:
                    mShadowColor = a.getColor(attr, mShadowColor);
                    break;
                case R.styleable.ArrowRectangleView_shadow_thickness:
                    mShadowThickness = a.getDimensionPixelSize(attr, mShadowThickness);
                    break;
            }
        }

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int maxWidth = 0;
        // reserve space for the arrow and round corners
        int maxHeight = mArrowHeight + mRadius;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            if (child.getVisibility() != GONE) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                maxWidth = Math.max(maxWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                maxHeight = maxHeight + child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            }
        }

        maxWidth = maxWidth + getPaddingLeft() + getPaddingRight() + mShadowThickness;
        maxHeight = maxHeight + getPaddingTop() + getPaddingBottom() + mShadowThickness;

        setMeasuredDimension(maxWidth, maxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int topOffset = t + mArrowHeight + mRadius/2;
        int top = 0;
        int bottom = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            top = topOffset + i * child.getMeasuredHeight();
            bottom = top + child.getMeasuredHeight();
            child.layout(l, top, r - mRadius/2 - mShadowThickness, bottom);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        // disable h/w acceleration for blur mask filter
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mBackgroundColor);
        paint.setStyle(Paint.Style.FILL);

        // set Xfermode for source and shadow overlap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));

        // draw round corner rectangle
        paint.setColor(mBackgroundColor);
        canvas.drawRoundRect(new RectF(0, mArrowHeight, getMeasuredWidth() - mShadowThickness, getMeasuredHeight() - mShadowThickness), mRadius, mRadius, paint);

        // draw arrow
        Path path = new Path();
        int startPoint = getMeasuredWidth() - mArrowOffset;
        path.moveTo(startPoint, mArrowHeight);
        path.lineTo(startPoint + mArrowWidth, mArrowHeight);
        path.lineTo(startPoint + mArrowWidth / 2, 0);
        path.close();
        canvas.drawPath(path, paint);

        // draw shadow
        if (mShadowThickness > 0) {
            paint.setMaskFilter(new BlurMaskFilter(mShadowThickness, BlurMaskFilter.Blur.OUTER));
            paint.setColor(mShadowColor);
            canvas.drawRoundRect(new RectF(mShadowThickness, mArrowHeight + mShadowThickness, getMeasuredWidth() - mShadowThickness, getMeasuredHeight() - mShadowThickness), mRadius, mRadius, paint);
        }

        super.dispatchDraw(canvas);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}

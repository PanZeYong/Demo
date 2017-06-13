package com.pan.vieweventdispatchdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author : Pan
 * Date : 11/24/16
 */

public class CircleView extends View {
    private static final String TAG = CircleView.class.getSimpleName();

    private Paint mPaint;

    private int mCircleColor;
    private int mLineColor;
    private int mTextColor;
    private float mTextSize;
    private String mCount = "";

    private OnCountListener mOnCountListener;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "CircleView");
        init(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mCircleColor = a.getColor(R.styleable.CircleView_circle_color, Color.GREEN);
        mLineColor = a.getColor(R.styleable.CircleView_line_color, Color.BLUE);
        mTextColor = a.getColor(R.styleable.CircleView_text_color, Color.RED);
        mTextSize = a.getDimension(R.styleable.CircleView_text_size, 48);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, 400);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 400);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");

        Log.d(TAG, "Width : " + getWidth());
        Log.d(TAG, "Height : " + getHeight());

        Log.d(TAG, "Measure Width : " + getMeasuredWidth());
        Log.d(TAG, "Measure Height : " + getMeasuredHeight());

        Log.d(TAG, "Left : " + getLeft());
        Log.d(TAG, "Top : " + getTop());
        Log.d(TAG, "Right : " + getRight());
        Log.d(TAG, "Bottom : " + getBottom());

        mPaint.setColor(mCircleColor);

        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;

        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);

        drawLine(canvas, paddingLeft, paddingTop, radius);

        drawText(canvas, paddingLeft, radius);
    }

    private void drawLine(Canvas canvas, int paddingLeft, int paddingTop, int radius) {
        mPaint.setColor(mLineColor);
        canvas.drawLine(paddingLeft, getHeight() / 2, paddingLeft + radius * 2, getHeight() / 2, mPaint);
        canvas.drawLine(getWidth() / 2, paddingTop, getWidth() / 2, paddingTop + 2 * radius, mPaint);
    }

    private void drawText(Canvas canvas, int paddingLeft, int radius) {
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);

        float textWidth = mPaint.measureText(mCount);

        canvas.drawText(mCount, radius + paddingLeft - textWidth / 2, radius + mTextSize / 2, mPaint);
    }

    public void setCircleColor(int color) {
        this.mCircleColor = color;
    }

    public void setLineColor(int color) {
        this.mLineColor = color;
    }

    public void setCount(String count) {
        this.mCount = count;
    }

    public void setTextSize(float textSize) {
        this.mTextSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
    }

    public void setOnCountListener(OnCountListener onCountListener) {
        this.mOnCountListener = onCountListener;
    }

    public interface OnCountListener {
        void count();
    }
}

package com.example.mag_marketplace;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {

    private static final int PADDING_HOR = 10;
    private static final int PADDING_VERTICAL = 10;
    private static final int SIDE_MARGIN = 10;
    private static final int TEXT_MARGIN = 10;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int row = 0;
        int lengthX = l;
        int lengthY = t;
        for (int i = 0; i < count; i++) {

            final View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            lengthX += width + TEXT_MARGIN;
            lengthY = row * (height + TEXT_MARGIN) + TEXT_MARGIN + height + t;

            if (lengthX > r) {
                lengthX = width + TEXT_MARGIN + l;
                row++;
                lengthY = row * (height + TEXT_MARGIN) + TEXT_MARGIN + height + t;
            }
            child.layout(lengthX - width - TEXT_MARGIN, lengthY - height, lengthX - TEXT_MARGIN, lengthY);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int lengthX = 0;
        int lengthY = 0;
        int row = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            child.measure(0, 0);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            lengthX += width + TEXT_MARGIN;
            lengthY = row * (height + TEXT_MARGIN) + TEXT_MARGIN + height;
            if (lengthX > widthMeasureSpec) {
                lengthX = width + TEXT_MARGIN;
                row++;
                lengthY = row * (height + TEXT_MARGIN) + TEXT_MARGIN + height;
            }
        }
        setMeasuredDimension(resolveSize(lengthX, widthMeasureSpec), resolveSize(lengthY, heightMeasureSpec));
    }
}

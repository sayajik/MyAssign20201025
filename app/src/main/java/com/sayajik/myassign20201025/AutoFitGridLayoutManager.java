package com.sayajik.myassign20201025;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AutoFitGridLayoutManager extends GridLayoutManager {

    private int columnWidth;
    private boolean columnWidthChanged = true;
    private int orientation;

    public AutoFitGridLayoutManager(Context context, int columnWidth, int orientation) {
        super(context, 4);
        this.orientation = orientation;
        setColumnWidth(columnWidth);
    }

    public AutoFitGridLayoutManager(Context context, int columnWidth) {
        super(context, 1);
//        this.orientation = HORIZONTAL;
        setColumnWidth(columnWidth);
    }


//    @Override
//    public void setOrientation(int orientation) {
//        super.setOrientation(orientation);
//    }

    public void setColumnWidth(int newColumnWidth) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth;
            columnWidthChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        final int width = getWidth();
        final int height = getHeight();
        if (getOrientation() == RecyclerView.VERTICAL) {
            if (columnWidthChanged && columnWidth > 0) {
                int totalSpace;
                if (getOrientation() == VERTICAL) {
                    totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
                } else {
                    totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
                }
                int spanCount = Math.max(1, totalSpace / columnWidth);
                setSpanCount(4);
                columnWidthChanged = false;
            }
        } else {
            setSpanCount(1);
        }
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
        if (orientation == RecyclerView.VERTICAL) {
            return super.checkLayoutParams(lp);
        } else {
            lp.width = getWidth() / 2;
            return true;
        }
    }
}

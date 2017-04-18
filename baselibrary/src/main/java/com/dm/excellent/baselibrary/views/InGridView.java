package com.dm.excellent.baselibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by apple on 2017/4/18.
 */

public class InGridView extends GridView {
    public InGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InGridView(Context context) {
        super(context);
    }

    public InGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

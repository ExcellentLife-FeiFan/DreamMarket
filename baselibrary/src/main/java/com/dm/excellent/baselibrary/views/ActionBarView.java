package com.dm.excellent.baselibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dm.excellent.baselibrary.R;
import com.dm.excellent.baselibrary.utils.AbStrUtil;


/**
 * 动作导航条
 *
 * @author yuanc
 */
public class ActionBarView extends LinearLayout {

    private ImageView iv_right;
    private TextView tv_title_left, tv_title_middle, tv_right;
    private RelativeLayout rl_back, rl_v_right;

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActionBarView);
        boolean isDark = a.getBoolean(R.styleable.ActionBarView_is_dark, false);
        if(isDark){
            inflate(context, R.layout.actionbar_layout_dark, this);
        }else{
            inflate(context, R.layout.actionbar_layout, this);
        }
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rl_v_right = (RelativeLayout) findViewById(R.id.rl_v_right);
        tv_title_left = (TextView) findViewById(R.id.tv_title_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        tv_title_middle = (TextView) findViewById(R.id.tv_title_middle);
        tv_right = (TextView) findViewById(R.id.tv_right);

    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar(String title, OnClickListener listener) {
        tv_title_left.setText(title);
        if (null != listener) {
            rl_back.setOnClickListener(listener);
            tv_title_left.setOnClickListener(listener);
        }
    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar(String title, boolean isTitleMiddle, OnClickListener listener) {
        if (isTitleMiddle) {
            tv_title_middle.setVisibility(VISIBLE);
            tv_title_middle.setText(title);
            tv_title_left.setVisibility(GONE);
        }
        tv_title_left.setText(title);

        if (null != listener) {
            rl_back.setOnClickListener(listener);
            if (isTitleMiddle) {
                tv_title_middle.setOnClickListener(listener);
            } else {
                tv_title_left.setOnClickListener(listener);
            }
        }
    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar(String title, boolean isTitleMiddle, boolean showBack, OnClickListener listener) {
        if (!showBack) {
            rl_back.setVisibility(GONE);
        }
        if (isTitleMiddle) {
            tv_title_middle.setVisibility(VISIBLE);
            tv_title_middle.setText(title);
            tv_title_left.setVisibility(GONE);
        }
        tv_title_left.setText(title);

        if (null != listener) {
            if (showBack) {
                rl_back.setOnClickListener(listener);
            }
            if (isTitleMiddle) {
                tv_title_middle.setOnClickListener(listener);
            } else {
                tv_title_left.setOnClickListener(listener);
            }
        }

    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar(String title, boolean isTitleMiddle, boolean showBack, String rightTv,OnClickListener listener) {
        if (!showBack) {
            rl_back.setVisibility(GONE);
        }
        if (isTitleMiddle) {
            tv_title_middle.setVisibility(VISIBLE);
            tv_title_middle.setText(title);
            tv_title_left.setVisibility(GONE);
        }
        tv_title_left.setText(title);

        if (!AbStrUtil.isEmpty(rightTv)) {
            tv_right.setVisibility(VISIBLE);
            tv_right.setText(rightTv);
            tv_right.setOnClickListener(listener);
        }

        if (null != listener) {
            if (showBack) {
                rl_back.setOnClickListener(listener);
            }
            if (isTitleMiddle) {
                tv_title_middle.setOnClickListener(listener);
            } else {
                tv_title_left.setOnClickListener(listener);
            }
            if (!AbStrUtil.isEmpty(rightTv)) {
                tv_right.setOnClickListener(listener);
            }
        }

    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar(String title, boolean isTitleMiddle, boolean showBack, int rightResID, OnClickListener listener) {
        if (!showBack) {
            rl_back.setVisibility(GONE);
        }
        if (isTitleMiddle) {
            tv_title_middle.setVisibility(VISIBLE);
            tv_title_middle.setText(title);
            tv_title_left.setVisibility(GONE);
        }
        tv_title_left.setText(title);

        if (rightResID != -1) {
            rl_v_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(rightResID);
        }

        if (null != listener) {
            if (showBack) {
                rl_back.setOnClickListener(listener);
            }
            if (isTitleMiddle) {
                tv_title_middle.setOnClickListener(listener);
            } else {
                tv_title_left.setOnClickListener(listener);
            }
            if (rightResID != -1) {
                rl_v_right.setOnClickListener(listener);
            }

        }

    }

    /**
     * 初始化ActionBar
     */
    public void initActionBar(String title, boolean isTitleMiddle, boolean showBack, String rightTv, int rightResID, OnClickListener listener) {
        if (!showBack) {
            rl_back.setVisibility(GONE);
        }
        if (isTitleMiddle) {
            tv_title_middle.setVisibility(VISIBLE);
            tv_title_middle.setText(title);
            tv_title_left.setVisibility(GONE);
        }
        tv_title_left.setText(title);

        if (!AbStrUtil.isEmpty(rightTv)) {
            tv_right.setVisibility(VISIBLE);
            tv_right.setText(rightTv);
        }
        if (rightResID == -1) {
            rl_v_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(rightResID);
        }

        if (null != listener) {
            if (showBack) {
                rl_back.setOnClickListener(listener);
            }
            if (isTitleMiddle) {
                tv_title_middle.setOnClickListener(listener);
            } else {
                tv_title_left.setOnClickListener(listener);
            }
            if (!AbStrUtil.isEmpty(rightTv)) {
                tv_right.setOnClickListener(listener);
            }
            if (rightResID == -1) {
                rl_v_right.setOnClickListener(listener);
            }

        }

    }

}
package com.excellent.dmu.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dm.excellent.baselibrary.views.ActionBarView;
import com.excellent.dmu.R;
import com.excellent.dmu.base.AppManager;
import com.excellent.dmu.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.ll_et)
    LinearLayout ll_et;
    @BindView(R.id.actionBar)
    ActionBarView actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getBar().initActionBar("注册", false, true, this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                AppManager.getInstance().killActivity(this);
                break;
        }

    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
    }



    @Override
    public void startEnterViewAnimate() {
        viewsToAnimate.add(ll_et);
        viewsToAnimate.add(btnRegister);
        viewsToAnimate.add(tvNotice);
        actionBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < viewsToAnimate.size(); i++) {
                    View child = viewsToAnimate.get(i);
                    child.animate()
                            .setStartDelay(i * 100)
                            .scaleX(1)
                            .scaleY(1);

                }
            }
        }, 500);
    }
}

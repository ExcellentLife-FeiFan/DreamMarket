package com.excellent.dm.ui.activity.login;

import android.os.Bundle;
import android.view.WindowManager;

import com.excellent.dm.R;
import com.excellent.dm.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_in)
    public void onViewClicked() {
    }
}

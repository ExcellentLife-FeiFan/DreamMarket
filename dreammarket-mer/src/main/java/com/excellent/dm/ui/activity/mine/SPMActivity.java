package com.excellent.dm.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.excellent.dm.R;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;

public class SPMActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spm);
        getBar().initActionBar("超市信息", this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                AppManager.getInstance().killActivity(this);
                break;
        }

    }
}

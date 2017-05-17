package com.excellent.dm.ui.activity.operate;

import android.os.Bundle;
import android.view.View;

import com.excellent.dm.R;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;

import butterknife.ButterKnife;


public class EditGoodActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_good);
        ButterKnife.bind(this);
        getBar().initActionBar("编辑增加商品", this);
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

package com.excellent.dm.ui.activity.operate;

import android.os.Bundle;
import android.view.View;

import com.excellent.dm.R;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;
import com.excellent.dm.ui.activity.mine.SPMActivity;
import com.excellent.dm.utils.IntentUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class GoodManaActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_mana);
        ButterKnife.bind(this);
        getBar().initActionBar("商品管理", this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                AppManager.getInstance().killActivity(this);
                break;
        }

    }

    @OnClick({R.id.ll_good_category_manager, R.id.tv_new_good, R.id.tv_scan_new_good})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_good_category_manager:
                break;
            case R.id.tv_new_good:
                new IntentUtils(activity).startActivity(EditGoodActivity.class);
                break;
            case R.id.tv_scan_new_good:
                break;
        }
    }
}

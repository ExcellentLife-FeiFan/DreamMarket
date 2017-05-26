package com.excellent.dm.ui.activity.ordermana;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dm.excellent.baselibrary.utils.HideUtil;
import com.excellent.dm.R;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;
import com.excellent.dm.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchOrderActivity extends BaseActivity {

    @BindView(R.id.et_search_order_keyword)
    EditText etSearchOrderKeyword;
    @BindView(R.id.img_search_order_content_clear)
    ImageView imgSearchOrderContentClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order);
        ButterKnife.bind(this);
        HideUtil.init(this);
        CommonUtils.setEtClearListener(etSearchOrderKeyword,imgSearchOrderContentClear);
    }

    @OnClick({R.id.rl_back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                AppManager.getInstance().killActivity(this);
                break;
            case R.id.tv_search:
                break;
        }
    }
}

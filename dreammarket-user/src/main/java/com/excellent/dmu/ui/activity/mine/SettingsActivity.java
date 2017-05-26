package com.excellent.dmu.ui.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dm.excellent.baselibrary.utils.HideUtil;
import com.excellent.dmu.R;
import com.excellent.dmu.base.BaseActivity;
import com.flyco.systembar.SystemBarHelper;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        HideUtil.init(this);
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.colorPrimary));
    }
}

package com.excellent.dm.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.excellent.dm.R;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;
import com.excellent.dm.ui.activity.poi.SelectSPMAddressActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

public class SPMActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spm);
        ButterKnife.bind(this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.ll_spm_0,R.id.ll_spm_1, R.id.ll_spm_2, R.id.ll_spm_3, R.id.ll_spm_4, R.id.ll_spm_5, R.id.ll_spm_6, R.id.ll_spm_7, R.id.ll_spm_8, R.id.ll_spm_9, R.id.ll_spm_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_spm_0:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(true)
                        .start(this, PhotoPicker.REQUEST_CODE);
                break;
            case R.id.ll_spm_1:
                break;
            case R.id.ll_spm_2:
                break;
            case R.id.ll_spm_3:
                break;
            case R.id.ll_spm_4:
                break;
            case R.id.ll_spm_5:
                break;
            case R.id.ll_spm_6:
               startActivity(SelectSPMAddressActivity.class);
                break;
            case R.id.ll_spm_7:
                break;
            case R.id.ll_spm_8:
                break;
            case R.id.ll_spm_9:
                break;
            case R.id.ll_spm_10:
                break;
        }
    }
}

package com.excellent.dm.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dm.excellent.baselibrary.utils.ImageLoadUtil;
import com.excellent.dm.R;
import com.excellent.dm.base.App;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseFragment;
import com.excellent.dm.ui.activity.login.LoginActivity;
import com.excellent.dm.ui.activity.mine.CurrentAccountActivity;
import com.excellent.dm.ui.activity.mine.SPMActivity;
import com.excellent.dm.ui.activity.mine.SPMQCodeActivity;
import com.excellent.dm.ui.activity.mine.SPMStatusSettingActivity;
import com.excellent.dm.utils.CommonUtils;
import com.excellent.dm.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 2017/3/29.
 */

public class Homefragment3 extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.iv_poi_logo)
    CircleImageView ivPoiLogo;
    @BindView(R.id.tv_poi_name)
    TextView tvPoiName;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_3;
    }

    @Override
    public void initView() {
        ImageLoadUtil.setImage(App.spm.getLogoUrl(), ivPoiLogo, activity, R.drawable.ic_poi_logo_default);
        CommonUtils.setText(tvPoiName, App.spm.getName());
    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_poi_status, R.id.ll_poi_qr, R.id.rl_poi_info, R.id.ll_setting_self_in, R.id.ll_setting_restaurant_change, R.id.ll_setting_order_notice, R.id.ll_setting_printe, R.id.ll_setting_check_update, R.id.ll_setting_service, R.id.ll_setting_feedback, R.id.ll_restaurant_contact_bd, R.id.ll_setting_account, R.id.tv_setting_exit_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_poi_info:
                new IntentUtils(activity).startActivity(SPMActivity.class);
                break;
            case R.id.ll_setting_self_in:
                break;
            case R.id.ll_poi_status:
                new IntentUtils(activity).startActivity(SPMStatusSettingActivity.class);
                break;
            case R.id.ll_poi_qr:
                new IntentUtils(activity).startActivity(SPMQCodeActivity.class);
                break;
            case R.id.ll_setting_restaurant_change:
                break;
            case R.id.ll_setting_order_notice:
                break;
            case R.id.ll_setting_printe:
                break;
            case R.id.ll_setting_check_update:
                break;
            case R.id.ll_setting_service:
                break;
            case R.id.ll_setting_feedback:
                break;
            case R.id.ll_restaurant_contact_bd:
                break;
            case R.id.ll_setting_account:
                new IntentUtils(activity).startActivity(CurrentAccountActivity.class);
                break;
            case R.id.tv_setting_exit_account:
                App.spm = null;
                new IntentUtils(activity).startActivity(LoginActivity.class);
                AppManager.getInstance().killActivity(activity);
                break;
        }
    }
}

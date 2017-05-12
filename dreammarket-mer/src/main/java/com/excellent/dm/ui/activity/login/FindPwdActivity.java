package com.excellent.dm.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.excellent.dm.R;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FindPwdActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_get_mobile_captcha)
    TextView tvGetMobileCaptcha;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.tv_error_mobile)
    TextView tvErrorMobile;
    @BindView(R.id.tv_captcha)
    TextView tvCaptcha;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.tv_error_captcha)
    TextView tvErrorCaptcha;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_error_password)
    TextView tvErrorPassword;
    @BindView(R.id.tv_confirm_password)
    TextView tvConfirmPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.tv_error_confirm_password)
    TextView tvErrorConfirmPassword;
    @BindView(R.id.ll_et)
    LinearLayout llEt;
    @BindView(R.id.btn_findpwd)
    Button btnFindpwd;
    @BindView(R.id.ll_findpwd)
    LinearLayout llFindpwd;
    @BindView(R.id.ll_content)
    LinearLayout llContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pwd);
        ButterKnife.bind(this);
        getBar().initActionBar("找回密码", false, true, this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                AppManager.getInstance().killActivity(this);
                break;
        }

    }

    @OnClick({R.id.tv_get_mobile_captcha, R.id.btn_findpwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_mobile_captcha:
                break;
            case R.id.btn_findpwd:
                break;
        }
    }

    @Override
    public void startEnterViewAnimate() {
        viewsToAnimate.add(llEt);
        viewsToAnimate.add(btnFindpwd);
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

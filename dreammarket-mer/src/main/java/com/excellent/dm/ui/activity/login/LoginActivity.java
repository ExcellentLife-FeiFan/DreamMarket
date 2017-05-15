package com.excellent.dm.ui.activity.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dm.excellent.baselibrary.utils.AbStrUtil;
import com.dm.excellent.baselibrary.utils.HideUtil;
import com.excellent.dm.R;
import com.excellent.dm.base.App;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;
import com.excellent.dm.bean.UserBean;
import com.excellent.dm.net.ApiResult;
import com.excellent.dm.net.JsonCallback;
import com.excellent.dm.net.Urls;
import com.excellent.dm.ui.activity.main.MainActivity;
import com.excellent.dm.utils.CommonUtils;
import com.excellent.dm.utils.IntentUtils;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.ll_mobile)
    LinearLayout llMobile;
    @BindView(R.id.tv_title_account)
    TextView tvTitleAccount;
    @BindView(R.id.v_line_account)
    View vLineAccount;
    @BindView(R.id.ll_account_login)
    LinearLayout llAccountLogin;
    @BindView(R.id.tv_title_mobile)
    TextView tvTitleMobile;
    @BindView(R.id.v_line_mobile)
    View vLineMobile;
    @BindView(R.id.ll_mobile_login)
    LinearLayout llMobileLogin;
    @BindView(R.id.tv_getsms)
    TextView tvGetsms;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_findpwd)
    TextView tvFindpwd;
    @BindView(R.id.fl_login_type)
    FrameLayout flLoginType;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_account_pwd)
    EditText etAccountPwd;
    private LayoutTransition mTransitioner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getBar().initActionBar("登录", false, false, null);
        HideUtil.init(this);
        //构建LayoutTransition
        mTransitioner = new LayoutTransition();
        //设置给ViewGroup容器
        flLoginType.setLayoutTransition(mTransitioner);
        setupCustomAnimations();
    }


    @OnClick({R.id.ll_account_login, R.id.ll_mobile_login, R.id.tv_getsms, R.id.btn_login, R.id.tv_register, R.id.tv_findpwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_account_login:
                llAccount.setVisibility(View.VISIBLE);
                llMobile.setVisibility(View.GONE);
                CommonUtils.setBackgroundDrawable(llAccountLogin, R.color.white);
                CommonUtils.setTextColor(tvTitleAccount, R.color.colorPrimary);
                CommonUtils.setBackgroundDrawable(vLineAccount, R.color.colorPrimary);

                CommonUtils.setBackgroundDrawable(llMobileLogin, R.color.light_white);
                CommonUtils.setTextColor(tvTitleMobile, R.color.text_middle_gray);
                CommonUtils.setBackgroundDrawable(vLineMobile, R.color.divider_line);
                break;
            case R.id.ll_mobile_login:
                llAccount.setVisibility(View.GONE);
                llMobile.setVisibility(View.VISIBLE);
                CommonUtils.setBackgroundDrawable(llMobileLogin, R.color.white);
                CommonUtils.setTextColor(tvTitleMobile, R.color.colorPrimary);
                CommonUtils.setBackgroundDrawable(vLineMobile, R.color.colorPrimary);

                CommonUtils.setBackgroundDrawable(llAccountLogin, R.color.light_white);
                CommonUtils.setTextColor(tvTitleAccount, R.color.text_middle_gray);
                CommonUtils.setBackgroundDrawable(vLineAccount, R.color.divider_line);
                break;
            case R.id.tv_getsms:
                break;
            case R.id.btn_login:
                if (llAccount.getVisibility() == View.VISIBLE) {
                    String name = etAccount.getText().toString();
                    String pwd = etAccountPwd.getText().toString();
                    if (AbStrUtil.isEmpty(name)) {
                        showToast("请输入账户");
                    } else if (AbStrUtil.isEmpty(pwd)) {
                        showToast("请输入密码");
                    } else {
                        actionLogin(name, pwd);
                    }

                } else {

                }
                break;
            case R.id.tv_register:
                new IntentUtils(activity).startActivity(RegisterActivity.class);
                break;
            case R.id.tv_findpwd:
                new IntentUtils(activity).startActivity(FindPwdActivity.class);
                break;
        }
    }

    private void actionLogin(String name, String pwd) {
        showDialog();
        OkGo.get(Urls.LOGIN)
                .params("LoginName", name)
                .params("LoginPwd", pwd)
                .execute(new JsonCallback<ApiResult<UserBean>>(new TypeToken<ApiResult<UserBean>>() {
                }.getType()) {
                    @Override
                    public void onSuccess(ApiResult<UserBean> result, Call call, Response response) {
                        dismissDialog();
                        if (result.isSuccess()) {
                            App.userBean = result.getObj();
                            new IntentUtils(activity).startActivity(MainActivity.class);
                            AppManager.getInstance().killActivity(activity);
                        } else {
                            showToast(result.getMsg());
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissDialog();
                        showToast(getResources().getString(R.string.action_failure));
                    }
                });


    }


    private void setupCustomAnimations() {
        // Changing while Adding
        PropertyValuesHolder pvhLeft =
                PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop =
                PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight =
                PropertyValuesHolder.ofInt("right", 0, 1);
        PropertyValuesHolder pvhBottom =
                PropertyValuesHolder.ofInt("bottom", 0, 1);
        PropertyValuesHolder pvhScaleX =
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY).
                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
        changeOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotation(0f);
            }
        });

        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f).
                setDuration(mTransitioner.getDuration(LayoutTransition.APPEARING));
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);
        animIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // Removing
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f).
                setDuration(mTransitioner.getDuration(LayoutTransition.DISAPPEARING));
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);
        animOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });

    }
}

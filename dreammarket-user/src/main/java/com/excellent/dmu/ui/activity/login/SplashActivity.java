package com.excellent.dmu.ui.activity.login;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.dm.excellent.baselibrary.utils.PermissionsActivity;
import com.dm.excellent.baselibrary.utils.PermissionsChecker;
import com.excellent.dmu.R;
import com.excellent.dmu.base.AppManager;
import com.excellent.dmu.base.BaseActivity;
import com.excellent.dmu.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;



public class SplashActivity extends BaseActivity {

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int REQUEST_CODE = 0; // 请求码
    @BindView(R.id.tv_show)
    TextView tvShow;

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设置布局文件
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        startAnimation();


        //        ShareSDK.initSDK(this);
        clearNotification();

        //        Glide.with(this).load(R.mipmap.img_splash).into(iv);
//        ShareSDK.initSDK(this);
        initView();

    }

    private void startAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setDuration(1500);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isFirst();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        tvShow.startAnimation(animationSet);

    }

    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mPermissionsChecker = new PermissionsChecker(this);
            // 缺少权限时, 进入权限配置页面
            if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                startPermissionsActivity();
                return;
            }
        }
        init();
        startAnimation();
  /*      new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                isFirst();
            }
        }.sendEmptyMessageDelayed(1, 2000);*/
    }

    private void init() {
//        //初始化程序后台后消息推送
//        PushUtil.getInstance();
//        //初始化消息监听
//        MessageEvent.getInstance();

    }

    private void isFirst() {
        boolean isFirst = SPUtil.getInstance().getBoolean("isFirst", true);
        if (isFirst) {
            SPUtil.getInstance().putBoolean("isFirst", false);
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            AppManager.getInstance().killActivity(this);
        } else {
            startActivity(LoginActivity.class);
            AppManager.getInstance().killActivity(this);
        }
    }
 /*   private void getUser() {
        if (!isNetConnected()) {
            showToast(getResources().getString(R.string.net_wrong));
            return;
        }
        showDialog();
        OkGo.get(Urls.GetUser)
                .execute(new JsonCallback<ApiResult<UserBean>>(new TypeToken<ApiResult<UserBean>>() {
                }.getType()) {
                    @Override
                    public void onSuccess(ApiResult<UserBean> result, Call call, Response response) {
                        dismissDialog();
                        try {
                            if (result.isSuccess()) {
                                App.userBean = result.getData();
                                App.userBean.setToken(App.userBean.getToken());
                                App.loginType = App.userBean.getUserType();
                                loginIM(App.userBean.getUserName(),CommonUtils.getSig());
                                SPUtil.getInstance().putString("phone", App.userBean.getPhone());
    //                            SPUtil.getInstance().putString("pwd", App.userBean.getp);
                                startActivity(MainActivity.class);
                                AppManager.getInstance().killActivity(activity);
                            } else {
                                showToast(result.getMsg());
                                startActivity(LoginActivity.class);
                            }
                        } catch (Exception e) {
                            startActivity(LoginActivity.class);
                            AppManager.getInstance().killActivity(activity);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        dismissDialog();
                        showToast("自动登录失败");
                        startActivity(LoginActivity.class);
                        AppManager.getInstance().killActivity(activity);
                        LogUtils.e(e.getMessage());
                    }
                });


    }*/



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        } else {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    isFirst();
                }
            }.sendEmptyMessageDelayed(1, 2000);
        }
    }

    private void goToMainActivity() {
//        showToast("自动登录失败!");
        startActivity(LoginActivity.class);
        AppManager.getInstance().killActivity(this);
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }


    /**
     * 清楚所有通知栏通知
     */
    private void clearNotification() {
        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
//        MiPushClient.clearNotification(getApplicationContext());
    }
}


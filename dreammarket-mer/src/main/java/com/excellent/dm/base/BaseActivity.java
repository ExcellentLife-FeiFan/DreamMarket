package com.excellent.dm.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dm.excellent.baselibrary.utils.AbStrUtil;
import com.dm.excellent.baselibrary.utils.AbWifiUtil;
import com.dm.excellent.baselibrary.utils.LogUtils;
import com.dm.excellent.baselibrary.utils.ToastUtil;
import com.dm.excellent.baselibrary.views.ActionBarView;
import com.dm.excellent.baselibrary.views.loadding.CustomDialog;
import com.excellent.dm.R;
import com.excellent.dm.event.EmptyEvent;
import com.excellent.dm.utils.TransitionHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by XY on 2016/11/2.
 */

public class BaseActivity extends AppCompatActivity {
    protected Activity activity;
    private CustomDialog dialog;//进度条
    protected ActionBarView actionBar;
    protected final List<View> viewsToAnimate = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppManager.getInstance().addActivity(this);
        EventBus.getDefault().register(this);
        activity = this;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            setupWindowAnimations();
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
        getWindow().setReturnTransition(buildReturnTransition());
        getWindow().setEnterTransition(buildEnterTransition());
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(500);
        // This view will not be affected by enter transition animation
//        enterTransition.excludeTarget(R.id.btn1, true);
        return enterTransition;
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(500);
        return enterTransition;
    }

    private void transitionToActivity(Intent intent) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
//        i.putExtra("sample", sample);
        activity.startActivity(intent, transitionActivityOptions.toBundle());
    }
    private void transitionToActivityForResult(Intent intent,int requestCode) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
//        i.putExtra("sample", sample);
        activity.startActivityForResult(intent, requestCode,transitionActivityOptions.toBundle());
    }

    protected boolean isNetConnected() {
        return AbWifiUtil.isConnectivity(this);
    }

    protected void showToast(String txt) {
        ToastUtil.showToastShort(this, txt);
    }


    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivityIntent(intent);

    }

    protected void startActivity(Class<?> cls, String... objs) {
        Intent intent = new Intent(this, cls);
        for (int i = 0; i < objs.length; i++) {
            intent.putExtra(objs[i], objs[++i]);
        }
        startActivityIntent(intent);
    }

    protected void startActivity(Class<?> cls, String[] keys, String[] values) {
        if (keys.length == values.length) {
            Intent intent = new Intent(this, cls);
            for (int i = 0; i < keys.length; i++) {
                intent.putExtra(keys[i], values[i]);
            }
            startActivityIntent(intent);
        } else {
            LogUtils.e("keys和values的长度不一致！");
        }
    }

    protected void startActivityForResult(Class<?> cls, int requestCode, String[] keys, String[] values) {
        if (keys.length == values.length) {
            Intent intent = new Intent(this, cls);
            for (int i = 0; i < keys.length; i++) {
                intent.putExtra(keys[i], values[i]);
            }
            startActivityForResultIntent(intent, requestCode);
        } else {
            LogUtils.e("keys和values的长度不一致！");
        }
    }

    protected void startActivity(Class<?> cls, String key, String value) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, value);
        startActivityIntent(intent);
    }

    protected void startActivity(Class<?> cls, String key, Serializable value) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, value);
        startActivityIntent(intent);
    }

    protected void startActivityForResult(Class<?> cls, int requestCode, String key, String value) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, value);
        startActivityForResultIntent(intent, requestCode);
    }

    protected void startActivity(Class<?> cls, String key, Bundle data) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, data);
        startActivityIntent(intent);
    }

    protected void startActivityForResult(Class<?> cls, int requestCode, String key, Bundle data) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, data);
        startActivityForResultIntent(intent, requestCode);
    }

    protected void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResultIntent(intent, requestCode);
    }

    private void startActivityIntent(Intent intent){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            transitionToActivity(intent);
        }else{
            startActivity(intent);
        }
    }
    private void startActivityForResultIntent(Intent intent,int requestCode){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            transitionToActivityForResult(intent,requestCode);
        }else{
            startActivityForResult(intent,requestCode);
        }
    }

    // --------------------------------------------------------------------------------------
    protected ActionBarView getBar() {
        actionBar = (ActionBarView) findViewById(R.id.actionBar);
        return actionBar;
    }

    // --------------------------------------------------------------------------------------

    public void onEvent(EmptyEvent event) {

    }


    @Override
    protected void onStart() {
        super.onStart();
        startEnterViewAnimate();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    // dialog
    public CustomDialog getDialog() {
        if (dialog == null) {
            dialog = CustomDialog.instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    protected void setText(TextView tv, String txt) {
        if (AbStrUtil.isEmpty(txt)) {
            return;
        }
        tv.setText(txt);
    }
    protected void setText(EditText tv, String txt) {
        if (AbStrUtil.isEmpty(txt)) {
            return;
        }
        tv.setText(txt);
    }

    protected void setBackgroundDrawable(View v, int res) {
        v.setBackgroundDrawable(getResources().getDrawable(res));
    }

    protected void setTextColor(TextView v, int res) {
        v.setTextColor(getResources().getColor(res));
    }

    protected void setImageDrawable(ImageView v, int res) {
        v.setImageDrawable(getResources().getDrawable(res));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getInstance().killActivity(activity);
    }

    protected void startEnterViewAnimate() {
    }

}

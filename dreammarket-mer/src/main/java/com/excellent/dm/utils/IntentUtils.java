package com.excellent.dm.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.dm.excellent.baselibrary.utils.LogUtils;

import java.io.Serializable;

/**
 * Created by apple on 2017/3/16.
 */

public class IntentUtils {
    private Activity activity;

    public IntentUtils(Activity activity) {
        this.activity = activity;
    }
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivityIntent(intent);

    }

    public void startActivity(Class<?> cls, String... objs) {
        Intent intent = new Intent(activity, cls);
        for (int i = 0; i < objs.length; i++) {
            intent.putExtra(objs[i], objs[++i]);
        }
        startActivityIntent(intent);
    }

    public void startActivity(Class<?> cls, String[] keys, String[] values) {
        if (keys.length == values.length) {
            Intent intent = new Intent(activity, cls);
            for (int i = 0; i < keys.length; i++) {
                intent.putExtra(keys[i], values[i]);
            }
            startActivityIntent(intent);
        } else {
            LogUtils.e("keys和values的长度不一致！");
        }
    }

    public void startActivityForResult(Class<?> cls, int requestCode, String[] keys, String[] values) {
        if (keys.length == values.length) {
            Intent intent = new Intent(activity, cls);
            for (int i = 0; i < keys.length; i++) {
                intent.putExtra(keys[i], values[i]);
            }
            startActivityForResultIntent(intent, requestCode);
        } else {
            LogUtils.e("keys和values的长度不一致！");
        }
    }

    public void startActivity(Class<?> cls, String key, String value) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(key, value);
        startActivityIntent(intent);
    }

    public void startActivity(Class<?> cls, String key, Serializable value) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(key, value);
        startActivityIntent(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode, String key, String value) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(key, value);
        startActivityForResultIntent(intent, requestCode);
    }

    public void startActivity(Class<?> cls, String key, Bundle data) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(key, data);
        startActivityIntent(intent);
    }

    public void startActivityForResult(Class<?> cls, int requestCode, String key, Bundle data) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(key, data);
        startActivityForResultIntent(intent, requestCode);
    }

    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(activity, cls);
        startActivityForResultIntent(intent, requestCode);
    }

    private void startActivityIntent(Intent intent){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            transitionToActivity(intent);
        }else{
            activity.startActivity(intent);
        }
    }
    private void startActivityForResultIntent(Intent intent,int requestCode){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            transitionToActivityForResult(intent,requestCode);
        }else{
            activity.startActivityForResult(intent,requestCode);
        }
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
}

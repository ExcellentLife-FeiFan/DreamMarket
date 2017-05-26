package com.excellent.dmu.ui.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dm.excellent.baselibrary.utils.HideUtil;
import com.excellent.dmu.R;
import com.excellent.dmu.base.AppManager;
import com.excellent.dmu.base.BaseActivity;
import com.flyco.systembar.SystemBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends BaseActivity {

    @BindView(R.id.et)
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.colorPrimary));
        ButterKnife.bind(this);
        HideUtil.init(this);
    }

    @OnClick({R.id.rl_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                AppManager.getInstance().killActivity(this);
                break;
            case R.id.tv_right:
                break;
        }
    }

/*    private Transition initSharedElementEnterTransition() {
        final Transition sharedTransition= TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
        sharedTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
      *//*          Animator circularReveal = ViewAnimationUtils.createCircularReveal(image_bg, image_bg.getWidth() / 2, image_bg.getHeight() / 2
                        , icon_gg.getWidth()/2, Math.max(image_bg.getWidth(), image_bg.getHeight()));
                image_bg.setBackgroundColor(Color.BLACK);
                circularReveal.setDuration(600);
                circularReveal.start();*//*
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                sharedTransition.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        return sharedTransition;
    }*/
}

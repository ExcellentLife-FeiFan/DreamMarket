package com.excellent.dm.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.excellent.dm.R;
import com.excellent.dm.base.BaseActivity;
import com.excellent.dm.ui.fragment.Homefragment1;
import com.excellent.dm.ui.fragment.Homefragment2;
import com.excellent.dm.ui.fragment.Homefragment3;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.contentContainer)
    FrameLayout contentContainer;
    @BindView(R.id.myScrollingContent)
    NestedScrollView myScrollingContent;
    Homefragment1 homefragment1;
    Homefragment2 homefragment2;
    Homefragment3 homefragment3;
    Fragment currentFragment;
    private FragmentTransaction fragmentT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (null == savedInstanceState) {
            homefragment1 = new Homefragment1();
            homefragment2 = new Homefragment2();
            homefragment3 = new Homefragment3();
            currentFragment=homefragment1;
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentContainer, homefragment1)
                    .commit();
        }
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (tabId == R.id.tab_1) {
                    if(!(currentFragment instanceof Homefragment1)){
                        transaction.replace(R.id.contentContainer, homefragment1);
                    }
                    currentFragment=homefragment1;
                } else if (tabId == R.id.tab_2) {
                    if(!(currentFragment instanceof Homefragment2)){
                        transaction.replace(R.id.contentContainer, homefragment2);
                    }
                    currentFragment=homefragment2;
                } else if (tabId == R.id.tab_3) {
                    if(!(currentFragment instanceof Homefragment3)){
                        transaction.replace(R.id.contentContainer, homefragment3);
                    }
                    currentFragment=homefragment3;
                }
                transaction.commitAllowingStateLoss();
            }
        });


    }

}

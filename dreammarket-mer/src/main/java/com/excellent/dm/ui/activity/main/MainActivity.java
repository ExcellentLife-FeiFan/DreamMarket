package com.excellent.dm.ui.activity.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.excellent.dm.R;
import com.excellent.dm.base.BaseActivity;
import com.excellent.dm.ui.fragment.home.Homefragment0;
import com.excellent.dm.ui.fragment.home.Homefragment1;
import com.excellent.dm.ui.fragment.home.Homefragment2;
import com.excellent.dm.ui.fragment.home.Homefragment3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomBar;
    @BindView(R.id.contentContainer)
    FrameLayout contentContainer;
    Homefragment0 homefragment0;
    Homefragment1 homefragment1;
    Homefragment2 homefragment2;
    Homefragment3 homefragment3;
    Fragment currentFragment;
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.RIGHT);
            slideTransition.setDuration(300);
            getWindow().setReenterTransition(slideTransition);
        }
//        StatusBarCompat.compat(this, CommonUtils.getColor(this, R.color.title_bg_color));
        if (null == savedInstanceState) {
            homefragment0 = new Homefragment0();
            homefragment1 = new Homefragment1();
            homefragment2 = new Homefragment2();
            homefragment3 = new Homefragment3();
            switchFragment(homefragment0);
        }

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home_b_tab_0, R.drawable.ic_notifications_active_white_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.home_b_tab_1, R.drawable.ic_notifications_white_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.home_b_tab_2, R.drawable.ic_market_white_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.home_b_tab_3, R.drawable.ic_person_white_24dp, R.color.colorPrimary);

// Add items
        bottomBar.addItem(item1);
        bottomBar.addItem(item2);
        bottomBar.addItem(item3);
        bottomBar.addItem(item4);

        bottomBar.setBehaviorTranslationEnabled(false);
// Manage titles
        bottomBar.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Set background color
        bottomBar.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        // Change colors
        bottomBar.setAccentColor(getResources().getColor(R.color.colorPrimary));
        bottomBar.setInactiveColor(getResources().getColor(R.color.tab_unselect));
        bottomBar.setForceTint(true);
        bottomBar.setTranslucentNavigationEnabled(true);
        // Use colored navigation with circle reveal effect
        bottomBar.setColored(false);
        // Add or remove notification for each item
//        bottomBar.setNotification("1", 0);
        // OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
//                .setTextColor(ContextCompat.getColor(this, R.color.black_overlay))
//                .build();
//        bottomBar.setNotification(notification, 2);

        // Set listeners
        bottomBar.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                FragmentManager fm = getSupportFragmentManager();
                // 开启Fragment事务
                FragmentTransaction transaction = fm.beginTransaction();
                if (position == 0) {
                    switchFragment(homefragment0);
                } else if (position == 1) {
                    switchFragment(homefragment1);
                } else if (position == 2) {
                    switchFragment(homefragment2);
                } else if (position == 3) {
                    switchFragment(homefragment3);
                }
                transaction.commitAllowingStateLoss();
                return true;
            }
        });
        bottomBar.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y positi5gon
            }
        });

    }

    /**
     * 切换fragment
     */
    public void switchFragment(Fragment fragment) {
        boolean isFound = false;
        Fragment usedFragment = null;
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

        if (fragments.size() > 0) {
            for (Fragment f : fragments) {
                trans.hide(f);
                if (f.getClass().getName().equals(fragment.getClass().getName())) {
                    isFound = true;
                    usedFragment = f;
                }
            }
            if (isFound) {
                trans.show(usedFragment);
            } else {
                trans.add(R.id.contentContainer, fragment, fragment.getClass().getName());
                fragments.add(fragment);
            }
        } else {
            if (null == fragment) {
                return;
            }
            trans.add(R.id.contentContainer, fragment, fragment.getClass().getName());
            fragments.add(fragment);
        }
        trans.commitAllowingStateLoss();
        currentFragment = fragment;
    }


    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }


}

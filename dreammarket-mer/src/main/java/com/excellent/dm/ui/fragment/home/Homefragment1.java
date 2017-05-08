package com.excellent.dm.ui.fragment.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.excellent.dm.R;
import com.excellent.dm.base.BaseFragment;
import com.excellent.dm.ui.fragment.OrderCancelFragment;
import com.excellent.dm.ui.fragment.OrderCompleteFragment;
import com.excellent.dm.ui.fragment.OrderGoingFragment;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by apple on 2017/3/29.
 */

public class Homefragment1 extends BaseFragment {

    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private String[] titles = new String[]{"进行中", "已完成", "已取消"};

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_1;
    }

    @Override
    public void initView() {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(3);
        tab.setViewPager(vp, titles);
    }

    @Override
    protected void initData() {

    }




    public class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                OrderGoingFragment fragment1 = new OrderGoingFragment();
                return fragment1;
            } else if (position == 1) {
                OrderCompleteFragment fragment2 = new OrderCompleteFragment();
                return fragment2;
            } else if (position == 2) {
                OrderCancelFragment fragment3 = new OrderCancelFragment();
                return fragment3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

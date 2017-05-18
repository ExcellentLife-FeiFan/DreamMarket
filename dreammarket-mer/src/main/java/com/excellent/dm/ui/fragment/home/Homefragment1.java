package com.excellent.dm.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.excellent.dm.R;
import com.excellent.dm.base.BaseFragment;
import com.excellent.dm.ui.activity.ordermana.PreOrderActivity;
import com.excellent.dm.ui.activity.ordermana.SearchOrderActivity;
import com.excellent.dm.ui.fragment.OrderCancelFragment;
import com.excellent.dm.ui.fragment.OrderCompleteFragment;
import com.excellent.dm.ui.fragment.OrderGoingFragment;
import com.excellent.dm.utils.IntentUtils;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    Unbinder unbinder1;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.tv_calendar, R.id.ll_search, R.id.tv_pre_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_calendar:
                break;
            case R.id.ll_search:
                new IntentUtils(activity).startActivity(SearchOrderActivity.class);
                break;
            case R.id.tv_pre_order:
                new IntentUtils(activity).startActivity(PreOrderActivity.class);
                break;
        }
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

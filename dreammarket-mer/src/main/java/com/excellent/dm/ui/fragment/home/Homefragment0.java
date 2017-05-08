package com.excellent.dm.ui.fragment.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.excellent.dm.R;
import com.excellent.dm.base.BaseFragment;
import com.excellent.dm.ui.fragment.OrderNewFragment;
import com.excellent.dm.ui.fragment.OrderRefundFragment;
import com.excellent.dm.ui.fragment.OrderUrgeFragment;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by apple on 2017/3/29.
 */

public class Homefragment0 extends BaseFragment {

    @BindView(R.id.tab)
    SlidingTabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private String[] titles = new String[]{"新订单", "催单", "退款"};

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_0;
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
                OrderNewFragment fragment1 = new OrderNewFragment();
                return fragment1;
            } else if (position == 1) {
                OrderUrgeFragment fragment2 = new OrderUrgeFragment();
                return fragment2;
            } else if (position == 2) {
                OrderRefundFragment fragment3 = new OrderRefundFragment();
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

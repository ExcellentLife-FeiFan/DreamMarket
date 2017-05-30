package com.excellent.dmu.ui.fm.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.excellent.dmu.R;
import com.excellent.dmu.base.BaseFragment;
import com.excellent.dmu.ui.activity.main.HomeSearchActivity;
import com.excellent.dmu.ui.activity.main.HomeSelectAddressActivity2;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by apple on 2017/3/29.
 */

public class HomeFM1 extends BaseFragment implements PullToRefreshView.OnRefreshListener {

    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    Unbinder unbinder;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et)
    TextView et;
    @BindView(R.id.pullToRefresh)
    PullToRefreshView pullToRefresh;

    @Override
    public int getLayoutRes() {
        return R.layout.fm_home_1;
    }

    @Override
    public void initView() {
        pullToRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_address, R.id.rl_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                startActivity(HomeSelectAddressActivity2.class);
                break;
            case R.id.rl_search:
                Intent intent = new Intent(activity, HomeSearchActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, new Pair<View, String>(rlSearch, "search_box"), new Pair<View, String>(et, "search_et"));
                startActivity(intent, activityOptionsCompat.toBundle());
                break;
        }
    }

    @Override
    public void onRefresh() {
        rlSearch.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefresh.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void ondragDistanceChange(float distance, float percent, float offset) {

    }
}
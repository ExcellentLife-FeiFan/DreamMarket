package com.excellent.dm.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.dm.excellent.baselibrary.views.InGridView;
import com.excellent.dm.R;
import com.excellent.dm.base.BaseFragment;
import com.excellent.dm.bean.PoiEntryBean;
import com.excellent.dm.ui.activity.operate.GoodManaActivity;
import com.excellent.dm.ui.adapters.PoiEntryLA;
import com.excellent.dm.utils.CommonUtils;
import com.excellent.dm.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by apple on 2017/3/29.
 */

public class Homefragment2 extends BaseFragment {

    @BindView(R.id.gv)
    InGridView gv;
    Unbinder unbinder;
    PoiEntryLA poiEntryLA;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_2;
    }

    @Override
    public void initView() {
        List<PoiEntryBean> entries = new ArrayList<>();
        entries.add(new PoiEntryBean(CommonUtils.getString(activity, R.string.poi_entry_1), R.mipmap.ic_activity_mana));
        entries.add(new PoiEntryBean(CommonUtils.getString(activity, R.string.poi_entry_2), R.mipmap.ic_good_mana));
        entries.add(new PoiEntryBean(CommonUtils.getString(activity, R.string.poi_entry_3), R.mipmap.ic_user_commet));
        entries.add(new PoiEntryBean(CommonUtils.getString(activity, R.string.poi_entry_4), R.mipmap.ic_msg_center));
        entries.add(new PoiEntryBean(CommonUtils.getString(activity, R.string.poi_entry_5), R.mipmap.ic_yingyetongji));
        entries.add(new PoiEntryBean("", 0, true));
        poiEntryLA = new PoiEntryLA(entries, activity);
        gv.setAdapter(poiEntryLA);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        new IntentUtils(activity).startActivity(GoodManaActivity.class);
                        break;
                }

            }
        });
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
}

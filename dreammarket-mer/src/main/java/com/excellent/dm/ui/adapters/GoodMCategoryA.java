package com.excellent.dm.ui.adapters;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dm.excellent.baselibrary.utils.ToastUtil;
import com.dm.excellent.baselibrary.views.InListView;
import com.dm.excellent.baselibrary.views.MyExpandableLayout;
import com.excellent.dm.R;
import com.excellent.dm.bean.GMCategory1;
import com.excellent.dm.utils.CommonUtils;
import com.github.zagum.expandicon.ExpandIconView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/5/21.
 */

public class GoodMCategoryA extends BaseQuickAdapter<GMCategory1, BaseViewHolder> {


    public GoodMCategoryA(List<GMCategory1> data) {
        super(R.layout.item_gm_category_0, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, GMCategory1 item) {

        MyExpandableLayout itemv = holder.getView(R.id.expandableLayout);
        View sub = itemv.getContentRelativeLayout();
        View header = itemv.getHeaderRelativeLayout();
        InListView lv = (InListView) sub.findViewById(R.id.lv);
        final ExpandIconView expandv = (ExpandIconView) header.findViewById(R.id.expandv);
        itemv.setOnStateListener(new MyExpandableLayout.OnStateListener() {
            @Override
            public void onStateListener(boolean isOpen) {
                expandv.switchState(true);
//                ToastUtil.showToastShort(mContext,isOpen+"");
            }
        });
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        GMCategorySubLV subLV = new GMCategorySubLV(strings, mContext);
        lv.setAdapter(subLV);
    }

}

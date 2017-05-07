package com.excellent.dm.ui.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dm.excellent.baselibrary.ui.adapter.CommonListAdapter;
import com.excellent.dm.R;
import com.excellent.dm.bean.PoiEntryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by apple on 2017/4/18.
 */

public class AddressSearchLV extends CommonListAdapter<PoiEntryBean> {
    public AddressSearchLV(List<PoiEntryBean> items, Activity activity) {
        super(items, activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_address_search, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desr)
        TextView tvDesr;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

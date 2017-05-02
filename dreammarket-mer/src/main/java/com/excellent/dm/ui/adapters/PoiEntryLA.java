package com.excellent.dm.ui.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class PoiEntryLA extends CommonListAdapter<PoiEntryBean> {
    public PoiEntryLA(List<PoiEntryBean> items, Activity activity) {
        super(items, activity);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_poi_entry, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (!item.isNull) {
            viewHolder.tvTitle.setText(item.getNamme());
            viewHolder.ivIcon.setImageResource(item.getIconRes());
        } else {
            viewHolder.rlRoot.setVisibility(View.INVISIBLE);
        }
        switch (position){
            case 0:
                viewHolder.civ.setImageDrawable(activity.getResources().getDrawable(R.color.royalblue));
                break;
            case 1:
                viewHolder.civ.setImageDrawable(activity.getResources().getDrawable(R.color.darkgoldenrod));
                break;
            case 2:
                viewHolder.civ.setImageDrawable(activity.getResources().getDrawable(R.color.burlywood));
                break;
            case 3:
                viewHolder.civ.setImageDrawable(activity.getResources().getDrawable(R.color.salmon));
                break;
            case 4:
                viewHolder.civ.setImageDrawable(activity.getResources().getDrawable(R.color.teal));
                break;
            case 5:
                viewHolder.civ.setImageDrawable(activity.getResources().getDrawable(R.color.grassgreen));
                break;
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.civ)
        ImageView civ;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.rl_root)
        RelativeLayout rlRoot;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

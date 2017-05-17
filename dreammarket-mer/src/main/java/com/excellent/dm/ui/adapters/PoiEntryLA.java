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
import de.hdodenhof.circleimageview.CircleImageView;

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
        viewHolder.civ.setImageResource(getColor(position));

        return convertView;
    }

    private int getColor(int position) {
        switch (position) {
            case 0:
                return R.color.chocolate;
            case 1:
                return R.color.grassgreen;
            case 2:
                return R.color.violet;
            case 3:
                return R.color.salmon;
            case 4:
                return R.color.skyblue;
            case 5:
                return R.color.olive;
            default:
                return R.color.olive;
        }
    }

    static class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.rl_root)
        RelativeLayout rlRoot;
        @BindView(R.id.civ)
        CircleImageView civ;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

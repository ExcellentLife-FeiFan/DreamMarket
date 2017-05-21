package com.excellent.dm.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2017/5/21.
 */

public class GMCategory1 implements Serializable, MultiItemEntity {


    private List<GMCategory2> subs;

    @Override
    public int getItemType() {
        return 0;
    }

    public List<GMCategory2> getSubs() {
        return subs;
    }

    public void setSubs(List<GMCategory2> subs) {
        this.subs = subs;
    }
}

package com.excellent.dm.bean;

import java.io.Serializable;

/**
 * Created by apple on 2017/4/18.
 */

public class PoiEntryBean implements Serializable {
    public String namme;
    public int iconRes;
    public boolean isNull=false;

    public PoiEntryBean(String namme, int iconRes) {
        this.namme = namme;
        this.iconRes = iconRes;
    }
    public PoiEntryBean(String namme, int iconRes,boolean isNull) {
        this.namme = namme;
        this.iconRes = iconRes;
        this.isNull = isNull;
    }

    public String getNamme() {
        return namme;
    }

    public void setNamme(String namme) {
        this.namme = namme;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}

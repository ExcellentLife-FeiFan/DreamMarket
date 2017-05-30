package com.excellent.dmu.event;

import com.amap.api.location.AMapLocation;
import com.baidu.location.BDLocation;

/**
 * Created by XY on 2017/5/9.
 */

public class AMapLocationUpdateEvent {
    AMapLocation aMapLocation;

    public AMapLocationUpdateEvent(AMapLocation aMapLocation) {
        this.aMapLocation = aMapLocation;
    }
}

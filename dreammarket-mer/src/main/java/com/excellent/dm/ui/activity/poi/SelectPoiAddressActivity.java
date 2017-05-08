package com.excellent.dm.ui.activity.poi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.dm.excellent.baselibrary.utils.LogUtils;
import com.excellent.dm.R;
import com.excellent.dm.base.AppManager;
import com.excellent.dm.base.BaseActivity;
import com.excellent.dm.ui.adapters.AddressSearchLV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectPoiAddressActivity extends BaseActivity implements OnGetPoiSearchResultListener, OnGetSuggestionResultListener,OnGetGeoCoderResultListener {

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ll_map)
    LinearLayout llMap;
    @BindView(R.id.ll_search)
    ListView llSearch;
    private BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位
    private PoiSearch mPoiSearch = null;
    private SuggestionSearch mSuggestionSearch;
    LatLng center;
    private GeoCoder mSearch;
    AddressSearchLV  mNearAddAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_poi_address);
        ButterKnife.bind(this);
        mNearAddAdapter = new AddressSearchLV(new ArrayList<PoiInfo>(),this);
        lv.setAdapter(mNearAddAdapter);
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);
        mBaiduMap = map.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, null));
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                      int arg3) {
                if (cs.length() <= 0) {
                    llMap.setVisibility(View.VISIBLE);
                    llSearch.setVisibility(View.GONE);
                    return;
                }
                llMap.setVisibility(View.GONE);
                llSearch.setVisibility(View.VISIBLE);
                /**
                 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
                 */
                mSuggestionSearch
                        .requestSuggestion((new SuggestionSearchOption())
                                .keyword(cs.toString()).city(etSearch.getText().toString()));
            }
        });
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                center = mBaiduMap.getMapStatus().target;
                searchNearby(center);
            }
        });

    }

    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        AppManager.getInstance().killActivity(this);
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        LogUtils.e("");
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        LogUtils.e("");
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        LogUtils.e("");
    }

    @Override
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        LogUtils.e("");
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        LogUtils.e("");

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        mNearAddAdapter.clearItems();
        LogUtils.e("");
        List<PoiInfo> pois = new ArrayList<>();
        PoiInfo n = new PoiInfo();
        n.address = reverseGeoCodeResult.getAddress();
        n.location=reverseGeoCodeResult.getLocation();
        n.name=reverseGeoCodeResult.getSematicDescription();
        pois.add(n);
        pois.addAll(reverseGeoCodeResult.getPoiList());
        mNearAddAdapter.addItems(pois,false);
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || map == null) {
                return;
            }
//            center=location.

            double mCurrentLat = location.getLatitude();
            double mCurrentLon = location.getLongitude();
            float mCurrentAccracy = location.getRadius();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                center = ll;
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                searchNearby(center);
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

    }

    @Override
    protected void onPause() {
        map.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        map.onResume();
        super.onResume();
/*        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);*/
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        map.onDestroy();
        super.onDestroy();
    }

    public void searchNearby(LatLng location) {
        if(null==location){
            showToast("未知出错");
            return;
        }
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(center));

/*        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption()
                .keyword("方家")
                .sortType(PoiSortType.distance_from_near_to_far)
                .location(location)
                .pageCapacity(20)
                .radius(100).pageNum(1);
        mPoiSearch.searchNearby(nearbySearchOption);*/
    }

}

package com.excellent.dmu.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.dm.excellent.baselibrary.utils.AbStrUtil;
import com.dm.excellent.baselibrary.utils.HideUtil;
import com.dm.excellent.baselibrary.utils.LogUtils;
import com.excellent.dmu.R;
import com.excellent.dmu.base.BaseActivity;
import com.excellent.dmu.ui.adapter.AddressSearchLV;
import com.excellent.dmu.ui.adapter.AddressSearchLV2;
import com.flyco.systembar.SystemBarHelper;
import com.zaaach.citypicker.CityPickerActivity;
import com.zaaach.citypicker.model.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSelectAddressActivity extends BaseActivity implements OnGetPoiSearchResultListener, OnGetSuggestionResultListener, OnGetGeoCoderResultListener {

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_title_left)
    TextView tvCity;
    @BindView(R.id.lv_nearly)
    ListView lv;
    @BindView(R.id.lv)
    ListView lvSearch;
    @BindView(R.id.ll_map)
    LinearLayout llMap;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.animation_view)
    LottieAnimationView animation_view;
    private BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位
    private PoiSearch mPoiSearch = null;
    private SuggestionSearch mSuggestionSearch;
    LatLng center;
    private GeoCoder mSearch;
    AddressSearchLV mSearchAddAdapter;
    AddressSearchLV2 mNearAddAdapter;
    private String cityCurrent = "北京";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_select_address);
        HideUtil.init(this);
        SystemBarHelper.tintStatusBar(this, getResources().getColor(R.color.colorPrimary));
        ButterKnife.bind(this);
        mNearAddAdapter = new AddressSearchLV2(new ArrayList<PoiInfo>(), this);
        lv.setAdapter(mNearAddAdapter);
        mSearchAddAdapter = new AddressSearchLV(new ArrayList<PoiInfo>(), this);
        lvSearch.setAdapter(mSearchAddAdapter);
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
                MyLocationConfiguration.LocationMode.NORMAL, true, BitmapDescriptorFactory
                .fromResource(R.color.transparent)));
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(60000);
        option.setIsNeedAddress(true);
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
                mSearchAddAdapter.clearItems();
                if (cs.length() <= 0) {
                    llMap.setVisibility(View.VISIBLE);
                    rlSearch.setVisibility(View.GONE);
                    return;
                }
                llMap.setVisibility(View.GONE);
                rlSearch.setVisibility(View.VISIBLE);
                searchCity(cs.toString());
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


    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        LogUtils.e("");
        rl_progress.setVisibility(View.GONE);
        animation_view.pauseAnimation();
        mSearchAddAdapter.addItems(poiResult.getAllPoi(), true);

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
//        mSearchAddAdapter.addItems(suggestionResult.getAllSuggestions(), true);
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(geoCodeResult.getLocation()).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        mNearAddAdapter.clearItems();
        LogUtils.e("");
        List<PoiInfo> pois = new ArrayList<>();
        PoiInfo n = new PoiInfo();
        n.address = reverseGeoCodeResult.getAddress();
        n.location = reverseGeoCodeResult.getLocation();
        n.name = reverseGeoCodeResult.getSematicDescription();
        pois.add(n);
        pois.addAll(reverseGeoCodeResult.getPoiList());
        mNearAddAdapter.addItems(pois, false);
    }

    @OnClick({R.id.rl_back, R.id.tv_title_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                this.onBackPressed();
                break;
            case R.id.tv_title_left:
                startActivityForResult(new Intent(this, CityActivity.class), REQUEST_CODE_PICK_CITY);
                break;
        }
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
            if (null != location.getAddress()) {
                cityCurrent = location.getAddress().city;
            }
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
        if (null == location) {
            showToast("未知出错");
            return;
        }
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(center));
    }

    public void searchCity(String keyword) {
        if(AbStrUtil.isEmpty(cityCurrent)){
            return;
        }
        rl_progress.setVisibility(View.VISIBLE);
        animation_view.playAnimation();
        PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption().pageCapacity(20).pageNum(1)
                .keyword(keyword).city(cityCurrent).isReturnAddr(true);
        mPoiSearch.searchInCity(poiCitySearchOption);
    }

    public void searchDetail(String keyword) {
        mSearch.geocode(new GeoCodeOption().address(keyword).city(keyword));
    }

    private static final int REQUEST_CODE_PICK_CITY = 233;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);   //this
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                City city = (City) data.getSerializableExtra(CityPickerActivity.KEY_PICKED_CITY);
                tvCity.setText(city.getAreaName());
                cityCurrent = city.getAreaName();
                searchDetail(cityCurrent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (rlSearch.getVisibility() == View.VISIBLE) {
            rl_progress.setVisibility(View.GONE);
            animation_view.pauseAnimation();
            rlSearch.setVisibility(View.GONE);
            llMap.setVisibility(View.VISIBLE);
            etSearch.setText("");
        } else {
            super.onBackPressed();
        }
    }
}

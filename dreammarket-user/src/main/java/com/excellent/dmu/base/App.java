package com.excellent.dmu.base;

import android.app.Application;
import android.content.Context;


import com.excellent.dmu.bean.UserBean;
import com.excellent.dmu.utils.SPUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

import butterknife.BuildConfig;
import butterknife.ButterKnife;

import static com.excellent.dmu.base.G.CONNECT_TIME_OUT;


/**
 * Created by XY on 2016/11/2.
 */

public class App extends Application {

    public static Context context;
    public static UserBean userBean;


    @Override
    public void onCreate() {
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);//设置ButterKnife调试模式
        context = this;
        initPrefs();
        initDirs();//初始化APP文件夹
        initOkGo();//初始化OkGo网络请求工具
    }

    private void initOkGo() {
        OkGo.init(this);
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(CONNECT_TIME_OUT)  //全局的连接超时时间
                    .setReadTimeOut(CONNECT_TIME_OUT)     //全局的读取超时时间
                    .setWriteTimeOut(CONNECT_TIME_OUT)    //全局的写入超时时间
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //如果不想让框架管理cookie,以下不需要
                    .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore());         //cookie持久化存储，如果cookie不过期，则一直有效
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SPUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

//    public static void initDataBase(Context context) {
//        liteOrm = LiteOrm.newSingleInstance(context, CommonUtils.getUserCachePath() + "userdata.db");
//        liteOrm.setDebugged(true); // open the log
//    }


    private void initDirs() {
   /*     File path1 = new File(G.STORAGEPATH);
        if (!path1.exists()) {
            path1.mkdirs();
        }
        File path2 = new File(CommonUtils.getUserCachePath());
        if (!path2.exists()) {
            path2.mkdirs();
        }*/
    }

//
//    public static void initProvinceDatas(Context context) {
//
//        AssetManager asset = context.getAssets();
//        try {
//            InputStream input = asset.open("province_data.xml");
//            // 创建一个解析xml的工厂对象
//            SAXParserFactory spf = SAXParserFactory.newInstance();
//            // 解析xml
//            SAXParser parser = spf.newSAXParser();
//            XmlParserHandler handler = new XmlParserHandler();
//            parser.parse(input, handler);
//            input.close();
//            // 获取解析出来的数据
//            provinceList = handler.getDataList();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//    }
//
//    public static void initAreaList(Context context) {
//        if (null == provinceList) {
//            initProvinceDatas(context);
//        }
//        if (cityList.size() < 1 || districtList.size() < 1) {
//            for (int i = 0; i < provinceList.size(); i++) {
//                cityList.add(provinceList.get(i).getCityList());
//                List<List<DistrictModel>> diss = new ArrayList<>();
//                for (int i1 = 0; i1 < provinceList.get(i).getCityList().size(); i1++) {
//                    diss.add(provinceList.get(i).getCityList().get(i1).getDistrictList());
//                }
//                districtList.add(diss);
//            }
//        }
//    }

}

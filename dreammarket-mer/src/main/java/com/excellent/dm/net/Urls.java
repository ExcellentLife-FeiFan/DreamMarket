package com.excellent.dm.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XY on 2016/11/3.
 */

public class Urls {

    public final static String BASE_URL = "http://api.zooheng.com:8888/";
    public final static String BASE_SRC_URL = "http://api.zooheng.com:8888/";

    public static String AddPATH(String url) {
        Pattern pattern = Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return url;
        } else {
            return BASE_SRC_URL + url;
        }
    }

    /**
     * 基础上传文件接口
     */
    public final static String UpFiles_P = BASE_URL + "API_CS/UpFiles";

    /**
     * 登录
     *
     * @param String LoginName  用户名
     * @param String LoginPwd  密码
     */
    public final static String LOGIN = BASE_URL + "API_CS/Login";


    /**
     * 修改超市信息
     * <p>
     * SupermarketCode    超市编号
     * Confines    配送范围(米)
     * Name    超市名称
     * Notice    公告
     * Contacts    联系人
     * Phone    电话
     * BusinessBeginTime    营业开始时间
     * BusinessEndTime    营业结束时间
     */
    public final static String ModifySupermarketInfo_P = BASE_URL + "API_CS/ModifySupermarketInfo";

    /**
     * 修改超市头像
     *
     * @param String SupermarketCode
     * @param String LogoUrl  文件地址(上传文件接口返回的文件路径)
     */
    public final static String ModifySupermarketLogo = BASE_URL + "API_CS/ModifySupermarketLogo";


    /**
     * 修改超市环境照片
     *
     * @param String SupermarketCode
     * @param String HJUrl  文件地址(上传文件接口返回的文件路径)
     */
    public final static String ModifySupermarketHJ = BASE_URL + "API_CS/ModifySupermarketHJ";

    /**
     * 上传营业执照
     *
     * @param String SupermarketCode
     * @param String YYZZUrl  文件地址(上传文件接口返回的文件路径)
     */
    public final static String ModifySupermarketYYZZ = BASE_URL + "API_CS/ModifySupermarketYYZZ";
}

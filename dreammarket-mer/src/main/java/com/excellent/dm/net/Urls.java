package com.excellent.dm.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XY on 2016/11/3.
 */

public class Urls {
//    public final static String BASE_URL = "http://jiekou.yitianxinda.com:81/";

        public final static String BASE_URL = "http://192.168.10.235:8080/";
//    public final static String BASE_URL = "http://192.168.10.204:8888/";
    public final static String BASE_SRC_URL = "http://192.168.10.235:8080/";
//    public final static String BASE_SRC_URL = "http://192.168.10.204:8888/";
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
     * 普通
     *
     * @param String phone  用户名
     * @param String pwd  密码
     */
    public final static String LOGIN = BASE_URL + "api/login";


}

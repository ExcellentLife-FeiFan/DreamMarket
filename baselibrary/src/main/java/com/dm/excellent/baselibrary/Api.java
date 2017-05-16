package com.dm.excellent.baselibrary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 2017/5/16.
 */

public class Api {
    public final static String BASE_URL = "http://api.zooheng.com:8888/";
    public final static String BASE_SRC_URL = "http://api.zooheng.com:8888";

    public static String AddPATH(String url) {
        Pattern pattern = Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return url;
        } else {
            return BASE_SRC_URL + url;
        }
    }
}

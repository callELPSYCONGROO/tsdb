package com.bici.tsdb.common.util;

import com.alibaba.fastjson.JSON;

/**
 * JsonUtil
 * @author: Overload
 * @date: 2018/4/18 18:09
 * @version: 1.0
 */
public class JsonUtil {

    private JsonUtil(){}

    public static <T> T json2Obj(String json, Class<T> tClass) {
        return JSON.parseObject(json, tClass);
    }

    public static String obj2Json(Object obj) {
        return JSON.toJSONString(obj);
    }
}

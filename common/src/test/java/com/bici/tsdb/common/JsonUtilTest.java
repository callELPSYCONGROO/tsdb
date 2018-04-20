package com.bici.tsdb.common;

import com.bici.tsdb.common.entity.PointDTO;
import com.bici.tsdb.common.entity.PointObj;
import com.bici.tsdb.common.util.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JsonUtilTest
 * @author: Overload
 * @date: 2018/4/18 18:15
 * @version: 1.0
 */
public class JsonUtilTest {

    @Test
    public void obj2JsonTest() {
        PointDTO o = new PointDTO();
        o.setDatabase("ces");
        o.setRetentionPolicy("dfs21");
        List<PointObj> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PointObj p = new PointObj();
            p.setTime(System.nanoTime());
            p.setMeasurement("nim");
            Map<String, Object> f = new HashMap<>();
            f.put("value", 432.32F);
            p.setFields(f);
            Map<String, String> t = new HashMap<>();
            t.put("mm", "fds");
            t.put("mm22", "fds");
            p.setTags(t);
            list.add(p);
        }
        o.setPointObjs(list);
        System.out.println(JsonUtil.obj2Json(o));
    }

    @Test
    public void json2ObjTest() {
        String j = "{\"database\":\"ceshi\",\"pointObjs\":[{\"fields\":{\"value\":1.01},\"measurement\":\"cpu\",\"tags\":{\"host\":\"serverA\",\"region\":\"us_west2\"},\"time\":35716023747604}]}";
        System.out.println(JsonUtil.json2Obj(j, PointDTO.class));
    }
}

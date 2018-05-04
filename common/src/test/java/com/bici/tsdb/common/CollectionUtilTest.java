package com.bici.tsdb.common;

import com.bici.tsdb.common.util.CollectionUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CollectionUtilTest
 * @author: Overload
 * @date: 2018/5/4 11:48
 * @version: 1.0
 */
public class CollectionUtilTest {

    @Test
    public void subListTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        System.out.println(CollectionUtil.subList(list, 4));
    }
}

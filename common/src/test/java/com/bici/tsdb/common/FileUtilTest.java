package com.bici.tsdb.common;

import com.bici.tsdb.common.util.FileUtil;
import org.junit.Test;

/**
 * FileUtilTest
 * @author: Overload
 * @date: 2018/5/3 10:46
 * @version: 1.0
 */
public class FileUtilTest {

    @Test
    public void t1() {
        FileUtil.write(1, 0);
        System.out.println(FileUtil.read(1));
    }
}

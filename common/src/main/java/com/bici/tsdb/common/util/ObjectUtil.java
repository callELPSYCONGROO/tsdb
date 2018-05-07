package com.bici.tsdb.common.util;

import java.io.*;

/**
 * ObjectUtil
 * @author: Overload
 * @date: 2018/5/7 11:13
 * @version: 1.0
 */
public class ObjectUtil {

    private ObjectUtil() {}

    /**
     * 深度复制对象
     * @param obj 要复制的对象，必须实现序列化接口
     * @return 复制结果
     * @throws Exception 发生异常
     */
    public static <T> T copyObj(T obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(baos);
        oo.writeObject(obj);
        return (T) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
    }

    public static  byte[] copyObj2Bytes(Object obj) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(baos);
        oo.writeObject(obj);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        byte[] bytes = new byte[bais.available()];
        new ObjectInputStream(bais).readFully(bytes);
        return bytes;
    }

    public static <T> T bytes2Obj(byte[] bytes, Class<T> tClass) throws Exception {
        return (T) new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
    }
}

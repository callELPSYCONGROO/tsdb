package com.bici.tsdb.common.constant;

import com.bici.tsdb.common.entity.Node;
import com.bici.tsdb.common.entity.NodeCount;
import com.bici.tsdb.common.entity.NodeMean;
import com.bici.tsdb.common.entity.NodeSum;

/**
 * NodeValueEnum数据聚合方式枚举类
 * @author: Overload
 * @date: 2018/5/16 15:46
 * @version: 1.0
 */
public enum NodeValueEnum {

    NO("", Node.class),
    SUM("sum", NodeSum.class),
    COUNT("count", NodeCount.class),
    MEAN("mean", NodeMean.class),
    MAX("max", NodeSum.class),
    MIN("mix", NodeSum.class);

    private String name;

    private Class nodeObj;

    NodeValueEnum(String name, Class nodeObj) {
        this.name = name;
        this.nodeObj = nodeObj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getNodeObj() {
        return nodeObj;
    }

    public void setNodeObj(Class nodeObj) {
        this.nodeObj = nodeObj;
    }

    /**
     * 根据字符串匹配对应的枚举类型
     * @param type 字符串
     * @return 枚举类型
     */
    public static NodeValueEnum getNodeValueEnum(String type) {
        for (NodeValueEnum nodeValueEnum : NodeValueEnum.values()) {
            if (nodeValueEnum.getName().equals(type)) {
                return nodeValueEnum;
            }
        }
        return null;
    }

    public static Class getNodeObj(String type) {
        return isNodeValueEnum(type) ? getNodeValueEnum(type).getNodeObj() : NO.getNodeObj();
    }

    /**
     * 是否为枚举类型
     */
    public static boolean isNodeValueEnum(String type) {
        for (NodeValueEnum nodeValueEnum : NodeValueEnum.values()) {
            if (nodeValueEnum.getName().equals(type)) {
                return true;
            }
        }
        return false;
    }
}

package com.bici.tsdb.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * CollectionUtil
 * @author: Overload
 * @date: 2018/5/4 11:47
 * @version: 1.0
 */
public class CollectionUtil {

    private CollectionUtil(){}

    public static <T> List<List<T>> subList(List<T> list, int blockSize) {
        List<List<T>> lists = new ArrayList<>();
        if (list != null && blockSize > 0) {
            int listSize = list.size();
            if(listSize<=blockSize){
                lists.add(list);
                return lists;
            }
            int batchSize = listSize / blockSize;
            int remain = listSize % blockSize;
            for (int i = 0; i < batchSize; i++) {
                int fromIndex = i * blockSize;
                int toIndex = fromIndex + blockSize;
                lists.add(list.subList(fromIndex, toIndex));
            }
            if(remain>0){
                lists.add(list.subList(listSize-remain, listSize));
            }
        }
        return lists;
    }
}

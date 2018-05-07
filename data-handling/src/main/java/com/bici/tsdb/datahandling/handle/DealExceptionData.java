package com.bici.tsdb.datahandling.handle;

import com.bici.tsdb.common.constant.CommonConstants;
import com.bici.tsdb.common.service.InfluxDBRepo;
import com.bici.tsdb.datahandling.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * DealExceptionData
 * 处理发送异常的数据
 * @author: Overload
 * @date: 2018/5/7 10:15
 * @version: 1.0
 */
@Component
public class DealExceptionData {

    @Autowired
    private InfluxDBRepo influxDBRepo;

    @Resource(name = "redisDao")
    private RedisDao redisDao;

    /**
     * 每个过程间隔5秒
     */
    @Scheduled(fixedDelay = 5000L)
    public void doScheduling() {
        System.out.println("开始处理redis中的错误数据--------------》");
        long llen = redisDao.llen(CommonConstants.REDIS_CACHE_DATA_PREFIX);
        System.out.println("数据长度：" + llen);
        if (llen <= 0) {// 列表没有数据，则返回
            System.out.println("----------------》没有数据，处理结束");
            return;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < llen; i++) {// 只处理当前获取到的长度的数据，新添加的数据留给下个周期
            String json = redisDao.pop(CommonConstants.REDIS_CACHE_DATA_PREFIX);
            try {
                influxDBRepo.insertPoints(json);// 插入数据库
            } catch (Exception e) {
                list.add(json);
            }
        }
        if (!list.isEmpty()) {
            String[] strings = new String[list.size()];
            redisDao.push(CommonConstants.REDIS_CACHE_DATA_PREFIX, list.toArray(strings));// 插入异常则重新缓存
        }
        System.out.println("-----------------》处理完成");
    }
}

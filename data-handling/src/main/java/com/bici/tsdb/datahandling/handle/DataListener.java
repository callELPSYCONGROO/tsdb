package com.bici.tsdb.datahandling.handle;

import com.alibaba.fastjson.JSON;
import com.bici.tsdb.common.constant.CommonConstant;
import com.bici.tsdb.common.entity.Payload;
import com.bici.tsdb.datahandling.service.InfluxdbWriteService;
import com.bici.tsdb.datahandling.service.RedisDao;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.Resource;
import java.util.List;

/**
 * DataListener数据监听器
 * @author: Overload
 * @date: 2018/4/17 13:46
 * @version: 1.0
 */
public class DataListener {

    @Resource(name = "redisDao")
    private RedisDao redisDao;

    @Resource(name = "influxdbWriteService")
    private InfluxdbWriteService influxdbWriteService;

    /**
     * 批量处理kafka信息，匹配dc.tsd.*对应的消息
     */
    @KafkaListener(topicPattern = CommonConstant.KAFKA_TOPIC_PREFIX + "*", containerFactory = "kafkaListenerContainerFactory")
    public void processMessage(List<ConsumerRecord> consumerRecordList) {
        System.out.println("开始处理消息-------------------》");
        for (ConsumerRecord consumerRecord : consumerRecordList) {
            try {
                Payload payload = JSON.parseObject(consumerRecord.value().toString(), Payload.class);
                payload.replaceString();
                System.out.println("对象转换完成：" + payload.toString());
                influxdbWriteService.write(payload);
                System.out.println("写入时序数据库成功！");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("失败！" + e.getMessage());
                redisDao.push(CommonConstant.REDIS_LIST_NAME, consumerRecord.value().toString());
            }
        }
        System.out.println("------------------------》消息处理完成\n");
    }
}

package com.bici.tsdb.datahandling.handle;

import com.alibaba.fastjson.JSON;
import com.bici.tsdb.common.entity.DataMessage;
import com.bici.tsdb.common.entity.PointDTO;
import com.bici.tsdb.common.entity.PointObj;
import com.bici.tsdb.common.exception.InfluxBusinessException;
import com.bici.tsdb.common.service.InfluxDBRepo;
import com.bici.tsdb.common.util.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataListener数据监听器
 * @author: Overload
 * @date: 2018/4/17 13:46
 * @version: 1.0
 */
public class DataListener {

    @Autowired
    private InfluxDBRepo influxDBRepo;

    /**
     * 监听对应topic，使用containerFactory配置
     * @param list 数据
     */
    @KafkaListener(topics = "${kafka.consumer.topic.cpu}", containerFactory = "kafkaListenerContainerFactory")
    public void processMessage(List<ConsumerRecord> list) {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setDatabase("ceshi");
        List<PointObj> pointObjs = new ArrayList<>();
        for (ConsumerRecord record : list) {
            this.handling(record, pointObjs);
        }
        pointDTO.setPointObjs(pointObjs);
        String data = JsonUtil.obj2Json(pointDTO);
        try {
            influxDBRepo.insertPoints(data);
        } catch (InfluxBusinessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理数据方法
     */
    private void handling(ConsumerRecord record, List<PointObj> pointObjs) {
        System.out.println("my-group-master get record ------> " + record.toString());
        DataMessage dataMessage = JSON.parseObject(record.value().toString(), DataMessage.class);
        PointObj pointObj = new PointObj();
        pointObj.setMeasurement("cpu");
        pointObj.setTime(System.nanoTime());
        Map<String, String> tags = new HashMap<>();
        tags.put("host", dataMessage.getH());
        tags.put("region", dataMessage.getR());
        pointObj.setTags(tags);
        Map<String, Object> fields = new HashMap<>();
        fields.put("value", dataMessage.getF());
        pointObj.setFields(fields);
        pointObjs.add(pointObj);
    }

    @KafkaListener(topics = "${kafka.consumer.topic.gpu}", containerFactory = "kafkaListenerContainerFactory")
    public void processMessage1(List<ConsumerRecord> list) {
        for (ConsumerRecord record : list) {
            System.out.println("my-group-slave get record ------> " + record.toString());
        }
    }
}

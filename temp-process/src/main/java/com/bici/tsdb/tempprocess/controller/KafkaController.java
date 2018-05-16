package com.bici.tsdb.tempprocess.controller;

import com.alibaba.fastjson.JSON;
import com.bici.tsdb.common.constant.CommonConstant;
import com.bici.tsdb.common.entity.Payload;
import com.bici.tsdb.common.entity.SensorData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * KafkaController
 * @author: bici
 * @date: 2018/4/17 11:09
 * @version: 1.0
 */
@RestController
public class KafkaController {

    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/send")
    public String send() {
        Payload payload = new Payload();
        payload.setFactoryNo("100000bici");
        payload.setDeviceNo("SSTC500-004097");
        List<SensorData> sensorDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1099L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SensorData sensorData = new SensorData();
            sensorData.setDataType("0x07");
            sensorData.setTime(System.currentTimeMillis());
            sensorData.setValue(new Random().nextFloat() * 9 + 1f);
            sensorDataList.add(sensorData);
        }
        payload.setDatas(sensorDataList);
        String json = JSON.toJSONString(payload);
        try {
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(CommonConstant.KAFKA_TOPIC_PREFIX + payload.getFactoryNo(), json);
            listenableFuture.addCallback(
                    sendResult -> System.out.println("成功发送消息" + json + "到topic：" + CommonConstant.KAFKA_TOPIC_PREFIX + payload.getFactoryNo())
                    ,
                    throwable -> System.out.println("发生错误！")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败！";
        }
        return "发送成功！";
    }
}

package com.bici.tsdb.tempprocess.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public String send(String t, String h, String r, Float f) {
        AtomicBoolean status = new AtomicBoolean(false);
        String json = "{\"h\":\"" + h + "\",\"r\":\"" + r + "\",\"f\":\"" + f + "\"}";
        try {
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(t, json);
            listenableFuture.addCallback(
                    sendResult -> {System.out.println("成功发送消息" + json + "到topic：" + t);
                        status.set(true);}
                    ,
                    throwable -> {System.out.println("发生错误！");throwable.printStackTrace();
                        status.set(false);}
            );
            if (status.get()) {
                return "发送失败！";
            }
        } catch (Exception e) {
            return "发送失败！";
        }
        return "发送成功！";
    }
}

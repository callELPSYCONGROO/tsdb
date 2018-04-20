package com.bici.tsdb.tempprocess.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaConfig
 * @author: bici
 * @date: 2018/4/17 11:10
 * @version: 1.0
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.producer.retries-config}")
    private Integer retriesConfig;

    @Value("${kafka.producer.batch-size-config}")
    private Integer batchSizeConfig;


//    @Value("${kafka.producer.request-timeout}")
//    private Integer requestTimeout;

    @Value("${kafka.producer.buffer-memory}")
    private Integer bufferMemory;

    @Value("${kafka.producer.linger}")
    private Integer linger;

    private Map<String, Object> kafkaConfigMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        map.put(ProducerConfig.RETRIES_CONFIG, retriesConfig);
        map.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSizeConfig);
        map.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        map.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        map.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
        return map;
    }

    private ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaConfigMap());
    }

    @Bean(name = "kafkaTemplate")
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

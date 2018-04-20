package com.bici.tsdb.datahandling.config;

import com.bici.tsdb.datahandling.handle.DataListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * KaakaConsumerConfig配置文件
 * @author: Overload
 * @date: 2018/4/17 11:47
 * @version: 1.0
 */
@Configuration
@EnableKafka
public class KaakaConsumerConfig {
    /** kafka服务器地址 */
    @Value("${kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    /** 消费者群组 */
    @Value("${kafka.consumer.group-id}")
    private String groupId;

    /** 失败自动提交 */
    @Value("${kafka.consumer.enable-auto-commit}")
    private Boolean enableAutoCommit;

    /** 自动提交延迟时间 */
    @Value("${kafka.consumer.auto-commit-interval}")
    private Integer autoCommitInterval;

    /** session超时 */
    @Value("${kafka.consumer.session-timeout}")
    private Integer sessionTimeout;

    /** 数据偏移方式 */
    @Value("${kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    /** 批量处理数量 */
    @Value("${kafka.consumer.max-poll-records}")
    private String maxPollRecords;

    /**
     * 配置属性
     */
    private Map<String, Object> consumerConfigMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        map.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        map.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        map.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return map;
    }

    /**
     * 消费者工厂
     */
    private ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigMap());
    }

    /**
     * 监听器工厂
     */
    @Bean(name = "kafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.consumerFactory());
        factory.setConcurrency(4);
        factory.setBatchListener(true);// 设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.getContainerProperties().setPollTimeout(500);
        return factory;
    }

    /**
     * 自定义监听类
     */
    @Bean
    public DataListener dataListener() {
        return new DataListener();
    }
}

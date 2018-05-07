package com.bici.tsdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class DataHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataHandlingApplication.class, args);
    }
}

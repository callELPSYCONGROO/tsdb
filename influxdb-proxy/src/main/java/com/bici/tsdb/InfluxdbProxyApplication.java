package com.bici.tsdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class InfluxdbProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfluxdbProxyApplication.class, args);
    }
}

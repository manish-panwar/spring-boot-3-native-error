package com.example.demo.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.kafka.KafkaProperties;

@Configuration
public class PropertyConfigurer {

    @Bean
    @ConfigurationProperties("event.consumer")
    KafkaProperties kafkaEventConsumerProperties() {
        return new KafkaProperties();
    }
}

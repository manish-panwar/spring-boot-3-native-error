package com.example.demo.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {

    /**
     * Autowired {@link KafkaTemplate}
     */
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Autowired
    public KafkaConsumer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(
        topics = "snapshot.v2",
        containerFactory = "eventConsumerKafkaListenerFactory",
        groupId = "snapshot.v2")
    public void consumeDeviceStateChangeEvents(List<ConsumerRecord<String, byte[]>> records, Acknowledgment ack) {
        System.out.println(records);
        ack.acknowledge();
    }
}

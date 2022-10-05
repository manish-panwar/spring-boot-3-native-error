package com.example.demo.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class KafkaProperties {

    private String bootstrapServers;

    private String groupId;

    private String offsetConfig;

    private int maxPollRecords;

    private int deliveryTimeoutMS;
}

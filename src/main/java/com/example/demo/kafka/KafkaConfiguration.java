package com.example.demo.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.UniformStickyPartitioner;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import lombok.extern.slf4j.Slf4j;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConfiguration {

    @Bean
    public ConsumerFactory<String, byte[]> eventProtocolConsumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getOffsetConfig());
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaProperties.getMaxPollRecords());
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new ByteArrayDeserializer());
    }

    @Bean(name = "eventConsumerKafkaListenerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, byte[]> eventProtocolKafkaListenerFactory(
        KafkaProperties kafkaProperties) {

        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setConsumerFactory(eventProtocolConsumerFactory(kafkaProperties));
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ProducerFactory<String, byte[]> producerFactory(KafkaProperties kafkaProperties) {
        var config = getProducerConfig(kafkaProperties);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean(name = "eventProducerKafkaFactory")
    public KafkaTemplate<String, byte[]> kafkaTemplate(KafkaProperties kafkaProperties) {
        return new KafkaTemplate<>(producerFactory(kafkaProperties));
    }

    private Map<String, Object> getProducerConfig(KafkaProperties kafkaProperties) {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, "all");
        config.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, UniformStickyPartitioner.class);
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, kafkaProperties.getDeliveryTimeoutMS());
        return config;
    }
}

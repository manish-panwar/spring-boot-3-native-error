package com.example.demo.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class CacheConfiguration {

    @Bean
    ReactiveRedisTemplate<String, ?> reactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        var keySerializer = RedisSerializer.string();
        var valueSerializer = new JdkSerializationRedisSerializer();
        var serializationContext =
            RedisSerializationContext.<String, Object>newSerializationContext()
                .key(keySerializer)
                .value(valueSerializer)
                .hashKey(keySerializer)
                .hashValue(valueSerializer)
                .build();
        return new ReactiveRedisTemplate(connectionFactory, serializationContext);
    }
}

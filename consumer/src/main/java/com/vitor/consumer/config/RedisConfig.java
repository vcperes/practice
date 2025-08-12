package com.vitor.consumer.config;

import com.vitor.consumer.document.ClassCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.vitor.consumer.document.repository.redis")
public class RedisConfig {

    @Bean
    public RedisTemplate<Long, ClassCode> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Long, ClassCode> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}

package com.vitor.consumer.config;

import com.vitor.consumer.classcode.ClassCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories(basePackages = "com.vitor.consumer.document.repository.redis")
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ClassCode> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ClassCode> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(ClassCode.class));
        return template;
    }
}

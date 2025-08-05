package com.vitor.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@Configuration
public class Config {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Value("${topics.document.topic}")
    private String documentTopic;

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        Map<String, Object> properties = kafkaProperties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactory<>(properties);
    }

}

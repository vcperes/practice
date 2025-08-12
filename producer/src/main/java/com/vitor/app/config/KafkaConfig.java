package com.vitor.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Value("${topics.document.topic}")
    private String documentTopic;

    @Bean
    public ProducerFactory<String, Map<String, String>> producerFactory(){
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, Map<String, String>> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic documentTopicBuilder(){
        return TopicBuilder
                .name(documentTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}

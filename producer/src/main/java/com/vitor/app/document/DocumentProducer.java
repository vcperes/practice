package com.vitor.app.document;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentProducer {

    private final Environment env;
    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;
    private static final String DOCUMENT_TOPIC = "topics.document.topic";

    public void sendMessage(Map<String, String> document) {
        log.info("Producing code string as message for Kafka");
        kafkaTemplate.send(env.getProperty(DOCUMENT_TOPIC), document);
    }
}

package com.vitor.app.document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String DOCUMENT_TOPIC = "topics.document.topic";
    private final ObjectMapper objectMapper;

    public void sendMessage(Map<String, String> document) throws JsonProcessingException {
        log.info("Producing code string as message for Kafka");
        String codeAsString = objectMapper.writeValueAsString(document);
        kafkaTemplate.send(env.getProperty(DOCUMENT_TOPIC), codeAsString);
    }
}

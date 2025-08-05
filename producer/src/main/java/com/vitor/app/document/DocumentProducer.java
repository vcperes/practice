package com.vitor.app.document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentProducer {

    @Value("${topics.document.topic}")
    private String documentTopic;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Map<String, String> document) throws JsonProcessingException {
        log.info("Writing document as String for Kafka");
        String codeMapString = objectMapper.writeValueAsString(document);
        log.info("Producing code string as message for Kafka");
        kafkaTemplate.send(documentTopic, codeMapString);
    }
}

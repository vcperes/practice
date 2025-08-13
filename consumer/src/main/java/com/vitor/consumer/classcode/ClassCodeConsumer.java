package com.vitor.consumer.classcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassCodeConsumer {

    private final ClassCodeService classCodeService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${topics.document.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) throws JsonProcessingException {
        log.info("Consuming message (code) from producer");
        Map<String, String> map = objectMapper.readValue(message, Map.class);
        log.info("Value parsed from String to Map");
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                ClassCode classCode = classCodeService.parseCode(entry);
                log.info("ClassCode {} parsed as entity", classCode.getClassName());

                classCodeService.save(classCode);
                log.info("ClassCode {} saved at mongo", classCode.getClassName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.vitor.consumer.document;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentConsumer {

    private final DocumentService documentService;

    @KafkaListener(topics = "${topics.document.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Map<String, String> message){
        log.info("Consuming message (code) from producer");
        try {
            for (Map.Entry<String, String> entry : message.entrySet()) {
                ClassCode classCode = documentService.parseCode(entry);
                log.info("ClassCode {} parsed as entity", classCode.getClassName());

                documentService.save(classCode);
                log.info("ClassCode {} saved at mongo", classCode.getClassName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

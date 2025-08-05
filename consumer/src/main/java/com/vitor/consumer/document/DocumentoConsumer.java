package com.vitor.consumer.document;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DocumentoConsumer{

    @KafkaListener(topics = "${topics.document.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message){
        System.out.println("Message received! " + message);
    }
}

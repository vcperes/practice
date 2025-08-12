package com.vitor.consumer.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@AllArgsConstructor
@RedisHash(timeToLive = 300)
@Document(collection = "classcodes")
@Getter
public class ClassCode {
    @Id
    private String className;
    private List<String> methods;
}

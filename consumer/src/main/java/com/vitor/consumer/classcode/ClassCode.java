package com.vitor.consumer.classcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@RedisHash(timeToLive = 300)
@Document(collection = "classcodes")
@Getter
public class ClassCode implements Serializable {
    @Id
    private String className;
    private List<String> methods;
}

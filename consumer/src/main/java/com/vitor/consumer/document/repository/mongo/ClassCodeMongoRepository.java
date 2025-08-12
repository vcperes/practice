package com.vitor.consumer.document.repository.mongo;

import com.vitor.consumer.document.ClassCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassCodeMongoRepository extends MongoRepository<ClassCode, String> {
}

package com.vitor.consumer.classcode.repository.mongo;

import com.vitor.consumer.classcode.ClassCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassCodeMongoRepository extends MongoRepository<ClassCode, String> {
}

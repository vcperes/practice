package com.vitor.consumer.classcode.repository.redis;

import com.vitor.consumer.classcode.ClassCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassCodeRedisRepository extends CrudRepository<ClassCode, String> {
}
